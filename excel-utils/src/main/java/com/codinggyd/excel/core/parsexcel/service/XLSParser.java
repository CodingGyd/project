package com.codinggyd.excel.core.parsexcel.service;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.poi.hssf.eventusermodel.EventWorkbookBuilder.SheetRecordCollectingListener;
import org.apache.poi.hssf.eventusermodel.HSSFEventFactory;
import org.apache.poi.hssf.eventusermodel.HSSFListener;
import org.apache.poi.hssf.eventusermodel.HSSFRequest;
import org.apache.poi.hssf.eventusermodel.MissingRecordAwareHSSFListener;
import org.apache.poi.hssf.eventusermodel.dummyrecord.LastCellOfRowDummyRecord;
import org.apache.poi.hssf.eventusermodel.dummyrecord.MissingCellDummyRecord;
import org.apache.poi.hssf.model.HSSFFormulaParser;
import org.apache.poi.hssf.record.BOFRecord;
import org.apache.poi.hssf.record.BlankRecord;
import org.apache.poi.hssf.record.BoolErrRecord;
import org.apache.poi.hssf.record.BoundSheetRecord;
import org.apache.poi.hssf.record.FormulaRecord;
import org.apache.poi.hssf.record.LabelRecord;
import org.apache.poi.hssf.record.LabelSSTRecord;
import org.apache.poi.hssf.record.NoteRecord;
import org.apache.poi.hssf.record.NumberRecord;
import org.apache.poi.hssf.record.RKRecord;
import org.apache.poi.hssf.record.Record;
import org.apache.poi.hssf.record.SSTRecord;
import org.apache.poi.hssf.record.StringRecord;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.codinggyd.excel.annotation.ExcelFieldConfig;
import com.codinggyd.excel.constant.ExcelConst;
import com.codinggyd.excel.constant.JavaFieldType;
import com.codinggyd.excel.core.parsexcel.CustomFormatTrackingHSSFListener;
import com.codinggyd.excel.core.parsexcel.bean.ResultList;
import com.codinggyd.excel.core.parsexcel.inter.IExcelParser;
import com.codinggyd.excel.core.parsexcel.inter.IExcelRowHandler;
import com.codinggyd.excel.exception.ExcelException;

/**
 * <pre>
 * 类名:  XLSReader.java
 * 包名:  com.codinggyd.excel.core.parsexcel.service
 * 描述:  xls格式的excel通用解析类
 * 
 * 作者:  guoyd
 * 日期:  2017年11月26日
 *
 * Copyright @ 2017 Corpration Name
 * </pre>
 */
public class XLSParser extends CommonParser implements IExcelParser, HSSFListener {
	private int minColumns = 0;

	private int lastRowNumber;
	private int lastColumnNumber;
	private Map<Integer, List<String>> dataMap = new LinkedHashMap<Integer, List<String>>();// excel多行数据<行号,数据集>
	private List<String> rowdataList = new ArrayList<String>();// 存放一行数据,字符串类型
	/** Should we output the formula, or the value it has? */
	private boolean outputFormulaValues = true;

	/** For parsing Formulas */
	private SheetRecordCollectingListener workbookBuildingListener;
	private HSSFWorkbook stubWorkbook;

	// Records we pick up as we process
	private SSTRecord sstRecord;
	private CustomFormatTrackingHSSFListener formatListener;

	/** So we known which sheet we're on */
	private int sheetIndex = -1;

	private BoundSheetRecord[] orderedBSRs;
	private List<BoundSheetRecord> boundSheetRecords = new ArrayList<BoundSheetRecord>();

	// For handling formulas with string results
	private int nextRow;
	private int nextColumn;
	private boolean outputNextStringRecord;
	// 自定义行级别解析处理回调接口
	private IExcelRowHandler customRowHandler;

	@Override
	public <T> ResultList<T> parse(InputStream is, final Class<T> clazz) throws ExcelException {
		
		//1.获取解析规则
		super.parseConfig(clazz);

		if (!ExcelConst.EXCEL_FORMAT_XLS.equals(sheetConfig.excelSuffix())) {
			throw new ExcelException("excel格式非xls,无法继续解析");
		}

		//2.开始解析
		final int contentStartIndex = sheetConfig.contentRowStartIndex();
		final ResultList<T> result = new ResultList<T>();
		final StringBuilder msgBuilder = new StringBuilder();
		final Integer[] errors = new Integer[1];
		errors[0] = 0;
		this.parse(is, new IExcelRowHandler() {

			@Override
			public void handler(int sheetIndex, int rowIndex, List<String> row) throws ExcelException {

				if (CollectionUtils.isEmpty(row)) {
					// 空行跳过
					return;
				}

				if (rowIndex < contentStartIndex) {
					// 非内容行区域
					return;
				}

				Field curField = null;

				T obj = null;
				try {
					obj = clazz.newInstance();
				} catch (Exception e) {
					throw new ExcelException(e.getMessage());
				}
				try {
					Set<ExcelFieldConfig> fieldConfigs = fieldConfigAndFieldMap.keySet();
					for (ExcelFieldConfig fieldConfig : fieldConfigs) {

						// 1.该字段是否是主键
						boolean isPrimaryKey = fieldConfig.isPrimaryKey();

						// 2.字段映射名称
						String fieldName = fieldConfig.titleConfig().name();
						curField = fieldConfigAndFieldMap.get(fieldConfig);
						if (null == curField) {
							throw new ExcelException("未找到字段[" + fieldName + "]的描述信息");
						}
						curField.setAccessible(true);

						// 3.字段映射位置
						int fieldIndex = fieldConfig.index();
						 
						// 4.字段映射java数据类型
						int javaType = fieldConfig.javaType();

						// 5.获取Excel中列原始值
						String originFieldContent = null;
						if (row.size() >= fieldIndex + 1) {
							originFieldContent = row.get(fieldIndex);
						}
						// 6.判断主键是否为空
						if (StringUtils.isEmpty(originFieldContent) && isPrimaryKey) {
							msgBuilder.append("sheet[" + sheetIndex + "],行[" + rowIndex + "],列[" + (fieldIndex + 1)
									+ "],错误信息[主键不允许为空值]\r\n");
							errors[0]++;
							return;
						}
						
						//7.解析内容匹配替换规则
						originFieldContent = checkRepaceRules(fieldConfig, originFieldContent);
						
						// 8.进行java数据类型匹配转换
						if (StringUtils.isNotEmpty(originFieldContent)) {
							
							originFieldContent = originFieldContent.trim();//去除内容两边可能存在的空格
							
							switch (javaType) {

							case JavaFieldType.TYPE_STRING:
								curField.set(obj, originFieldContent);
								break;
							case JavaFieldType.TYPE_DOUBLE:
								if (NumberUtils.isNumber(originFieldContent)) {
									try {
										curField.set(obj, Double.parseDouble(originFieldContent));
									} catch (NumberFormatException e) {
										msgBuilder.append("sheet[" + sheetIndex + "],行[" + rowIndex + "],列["
												+ (fieldIndex + 1) + "],错误信息[字段值类型有误,非双精度浮点数值类型]\r\n");
										errors[0]++;
									}
								} else {
									msgBuilder.append("sheet[" + sheetIndex + "],行[" + rowIndex + "],列["
											+ (fieldIndex + 1) + "],错误信息[字段值类型有误,非双精度浮点数值类型]\r\n");
									errors[0]++;
								}
								break;
							case JavaFieldType.TYPE_FLOAT:
								if (NumberUtils.isNumber(originFieldContent)) {
									try {
										curField.set(obj, Float.parseFloat(originFieldContent));
									} catch (NumberFormatException e) {
										msgBuilder.append("sheet[" + sheetIndex + "],行[" + rowIndex + "],列["
												+ (fieldIndex + 1) + "],错误信息[字段值类型有误,非单精度浮点数值类型]\r\n");
										errors[0]++;
									}
								} else {
									msgBuilder.append("sheet[" + sheetIndex + "],行[" + rowIndex + "],列["
											+ (fieldIndex + 1) + "],错误信息[字段值类型有误,非单精度浮点数值类型]\r\n");
									errors[0]++;
								}
								break;
							case JavaFieldType.TYPE_BIGDECIMAL:
								if (NumberUtils.isNumber(originFieldContent)) {
									try {
										curField.set(obj, new BigDecimal(originFieldContent));
									} catch (NumberFormatException e) {
										msgBuilder.append("sheet[" + sheetIndex + "],行[" + rowIndex + "],列["
												+ (fieldIndex + 1) + "],错误信息[字段值类型有误,非高精度数值类型]\r\n");
										errors[0]++;
									}
								} else {
									msgBuilder.append("sheet[" + sheetIndex + "],行[" + rowIndex + "],列["
											+ (fieldIndex + 1) + "],错误信息[字段值类型有误,非高精度数值类型]\r\n");
									errors[0]++;
								}
							case JavaFieldType.TYPE_DATE:
								try {
									curField.set(obj,
											DateUtils.parseDate(originFieldContent, fieldConfig.dateFormat()));
								} catch (Exception e) {
									msgBuilder.append("sheet[" + sheetIndex + "],行[" + rowIndex + "],列["
											+ (fieldIndex + 1) + "],错误信息[非约定日期格式" + fieldConfig.dateFormat() + "]\r\n");
									errors[0]++;
								}
								break;
							case JavaFieldType.TYPE_TIME:
								try {
									curField.set(obj,
											DateUtils.parseDate(originFieldContent, fieldConfig.dateFormat()));
								} catch (Exception e) {
									msgBuilder.append("sheet[" + sheetIndex + "],行[" + rowIndex + "],列["
											+ (fieldIndex + 1) + "],错误信息[非约定日期格式" + fieldConfig.dateFormat() + "]\r\n");
									errors[0]++;
								}
								break;
							case JavaFieldType.TYPE_INT:
								if (NumberUtils.isNumber(originFieldContent)) {
									try {
										curField.set(obj, Integer.parseInt(originFieldContent));
									} catch (NumberFormatException e) {
										msgBuilder.append("sheet[" + sheetIndex + "],行[" + rowIndex + "],列["
												+ (fieldIndex + 1) + "],错误信息[字段值类型有误,非整数类型]\r\n");
										errors[0]++;
									}
								} else {
									msgBuilder.append("sheet[" + sheetIndex + "],行[" + rowIndex + "],列["
											+ (fieldIndex + 1) + "],错误信息[字段值类型有误,非整数类型]\r\n");
									errors[0]++;
								}
								break;
							case JavaFieldType.TYPE_LONG:
								if (NumberUtils.isNumber(originFieldContent)) {
									try {
										curField.set(obj, Long.parseLong(originFieldContent));
									} catch (NumberFormatException e) {
										msgBuilder.append("sheet[" + sheetIndex + "],行[" + rowIndex + "],列["
												+ (fieldIndex + 1) + "],错误信息[字段值类型有误,非长整数类型]\r\n");
										errors[0]++;
									}
								} else {
									msgBuilder.append("sheet[" + sheetIndex + "],行[" + rowIndex + "],列["
											+ (fieldIndex + 1) + "],错误信息[字段值类型有误,非长整数类型]\r\n");
									errors[0]++;
								}
								break;
							default:
								curField.set(obj, originFieldContent);
								break;
							}
						}

					}

					result.add(obj);
				} catch (Exception e) {
					throw new ExcelException(e.getMessage());
				}
			}
		});
		result.setMsg(msgBuilder.toString());
		result.setErrors(errors[0]);
		return result;
	}

	@Override
	public void parse(InputStream is, IExcelRowHandler rowHandler) throws ExcelException {
		this.customRowHandler = rowHandler;
		this.parse(is, -1, 1);
	}

	@Override
	public Map<Integer, List<String>> parse(InputStream is, Integer minColumns, Integer sheetNums)
			throws ExcelException {
		try {

			if (null == minColumns) {
				minColumns = -1;// 默认值
			}
			this.minColumns = minColumns;

			this.dataMap = new LinkedHashMap<Integer, List<String>>();

			MissingRecordAwareHSSFListener listener = new MissingRecordAwareHSSFListener(this);
			formatListener = new CustomFormatTrackingHSSFListener(listener);

			HSSFEventFactory factory = new HSSFEventFactory();
			HSSFRequest request = new HSSFRequest();

			if (outputFormulaValues) {
				request.addListenerForAllRecords(formatListener);
			} else {
				workbookBuildingListener = new SheetRecordCollectingListener(formatListener);
				request.addListenerForAllRecords(workbookBuildingListener);
			}
			POIFSFileSystem fs = new POIFSFileSystem(is);
			factory.processWorkbookEvents(request, fs);
			fs.close();
		} catch (Exception e) {
			throw new ExcelException(e.getMessage());
		}
		return dataMap;
	}

	/**
	 * Main HSSFListener method, processes events, and outputs the CSV as the
	 * file is processed.
	 */
	@Override
	public void processRecord(Record record) {
		int thisRow = -1;
		int thisColumn = -1;
		String thisStr = null;

		switch (record.getSid()) {
		case BoundSheetRecord.sid:
			boundSheetRecords.add((BoundSheetRecord) record);
			break;
		case BOFRecord.sid:
			BOFRecord br = (BOFRecord) record;
			if (br.getType() == BOFRecord.TYPE_WORKSHEET) {
				// Create sub workbook if required
				if (workbookBuildingListener != null && stubWorkbook == null) {
					stubWorkbook = workbookBuildingListener.getStubHSSFWorkbook();
				}

				// Output the worksheet name
				// Works by ordering the BSRs by the location of
				// their BOFRecords, and then knowing that we
				// process BOFRecords in byte offset order
				sheetIndex++;
				if (orderedBSRs == null) {
					orderedBSRs = BoundSheetRecord.orderByBofPosition(boundSheetRecords);
				}
				// output.println();
				System.out.println(orderedBSRs[sheetIndex].getSheetname() + " [" + (sheetIndex + 1) + "]:");
				// output.println(orderedBSRs[sheetIndex].getSheetname() + " ["
				// + (sheetIndex + 1) + "]:");
			}
			break;

		case SSTRecord.sid:
			sstRecord = (SSTRecord) record;
			break;

		case BlankRecord.sid:
			BlankRecord brec = (BlankRecord) record;

			thisRow = brec.getRow();
			thisColumn = brec.getColumn();
			thisStr = "";
			break;
		case BoolErrRecord.sid:
			BoolErrRecord berec = (BoolErrRecord) record;

			thisRow = berec.getRow();
			thisColumn = berec.getColumn();
			thisStr = "";
			break;

		case FormulaRecord.sid:
			FormulaRecord frec = (FormulaRecord) record;

			thisRow = frec.getRow();
			thisColumn = frec.getColumn();

			if (outputFormulaValues) {
				if (Double.isNaN(frec.getValue())) {
					// Formula result is a string
					// This is stored in the next record
					outputNextStringRecord = true;
					nextRow = frec.getRow();
					nextColumn = frec.getColumn();
				} else {
					thisStr = formatListener.formatNumberDateCell(frec);

				}
			} else {
				// thisStr = '"' +
				// HSSFFormulaParser.toFormulaString(stubWorkbook,
				// frec.getParsedExpression()) + '"';
				thisStr = HSSFFormulaParser.toFormulaString(stubWorkbook, frec.getParsedExpression());

			}
			break;
		case StringRecord.sid:
			if (outputNextStringRecord) {
				// String for formula
				StringRecord srec = (StringRecord) record;
				thisStr = srec.getString();
				thisRow = nextRow;
				thisColumn = nextColumn;
				outputNextStringRecord = false;
			}
			break;

		case LabelRecord.sid:
			LabelRecord lrec = (LabelRecord) record;

			thisRow = lrec.getRow();
			thisColumn = lrec.getColumn();
			// thisStr = '"' + lrec.getValue() + '"';
			thisStr = lrec.getValue();
			break;
		case LabelSSTRecord.sid:
			LabelSSTRecord lsrec = (LabelSSTRecord) record;

			thisRow = lsrec.getRow();
			thisColumn = lsrec.getColumn();
			if (sstRecord == null) {
				// thisStr = '"' + "(No SST Record, can't identify string)" +
				// '"';
				thisStr = "(No SST Record, can't identify string)";

			} else {
				// thisStr = '"' +
				// sstRecord.getString(lsrec.getSSTIndex()).toString() + '"';
				thisStr = sstRecord.getString(lsrec.getSSTIndex()).toString();

			}
			break;
		case NoteRecord.sid:
			NoteRecord nrec = (NoteRecord) record;

			thisRow = nrec.getRow();
			thisColumn = nrec.getColumn();
			// TODO: Find object to match nrec.getShapeId()
			// thisStr = '"' + "(TODO)" + '"';
			thisStr = "(TODO)";

			break;
		case NumberRecord.sid:
			NumberRecord numrec = (NumberRecord) record;

			thisRow = numrec.getRow();
			thisColumn = numrec.getColumn();

			// Format
			thisStr = formatListener.formatNumberDateCell(numrec);
			break;
		case RKRecord.sid:
			RKRecord rkrec = (RKRecord) record;

			thisRow = rkrec.getRow();
			thisColumn = rkrec.getColumn();
			// thisStr = '"' + "(TODO)" + '"';
			thisStr = "(TODO)";
			break;
		default:
			break;
		}

		// Handle new row
		if (thisRow != -1 && thisRow != lastRowNumber) {
			lastColumnNumber = -1;
		}

		// Handle missing column
		if (record instanceof MissingCellDummyRecord) {
			MissingCellDummyRecord mc = (MissingCellDummyRecord) record;
			thisRow = mc.getRow();
			thisColumn = mc.getColumn();
			thisStr = "";
		}

		// If we got something to print out, do so
		if (thisStr != null) {
			rowdataList.add(thisStr);

			// output.print(thisStr);
		}

		// Update column and row count
		if (thisRow > -1)
			lastRowNumber = thisRow;
		if (thisColumn > -1)
			lastColumnNumber = thisColumn;

		// Handle end of row
		if (record instanceof LastCellOfRowDummyRecord) {
			// Print out any missing commas if needed
			if (minColumns > 0) {
				// Columns are 0 based
				if (lastColumnNumber == -1) {
					lastColumnNumber = 0;
				}
				for (int i = lastColumnNumber; i < (minColumns); i++) {
					// output.print(',');
					rowdataList.add("");
				}
			}

			dataMap.put(lastRowNumber + 1, rowdataList);

			// 自定义行级别解析回调接口
			if (null != customRowHandler) {
				try {
					customRowHandler.handler(sheetIndex + 1, lastRowNumber + 1, rowdataList);
				} catch (ExcelException e) {
					e.printStackTrace();
 				}
			}

			rowdataList = new ArrayList<String>();
			// We're onto a new row
			lastColumnNumber = -1;

			// End the row
		}
	}

}
