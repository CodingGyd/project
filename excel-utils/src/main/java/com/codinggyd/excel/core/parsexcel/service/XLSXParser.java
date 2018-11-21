package com.codinggyd.excel.core.parsexcel.service;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.util.SAXHelper;
import org.apache.poi.xssf.eventusermodel.ReadOnlySharedStringsTable;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.model.StylesTable;
import org.apache.poi.xssf.usermodel.XSSFComment;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import com.codinggyd.excel.annotation.ExcelFieldConfig;
import com.codinggyd.excel.constant.ExcelConst;
import com.codinggyd.excel.constant.JavaFieldType;
import com.codinggyd.excel.core.parsexcel.CustomXSSFSheetXMLHandler;
import com.codinggyd.excel.core.parsexcel.CustomXSSFSheetXMLHandler.SheetContentsHandler;
import com.codinggyd.excel.core.parsexcel.bean.ResultList;
import com.codinggyd.excel.core.parsexcel.inter.IExcelParser;
import com.codinggyd.excel.core.parsexcel.inter.IExcelRowHandler;
import com.codinggyd.excel.exception.ExcelException;
 
/**
 * <pre>
 * 类名:  XLSXParser.java
 * 包名:  com.codinggyd.excel.core.parsexcel.service
 * 描述:  xlsx格式的excel通用解析类
 * 
 * 作者:  guoyd
 * 日期:  2017年11月25日
 *
 * Copyright @ 2017 Corpration Name
 * </pre>
 */
public class XLSXParser extends CommonParser implements IExcelParser {

	private Map<Integer, List<String>> dataMap = new LinkedHashMap<Integer, List<String>>();// excel多行数据<行号,数据集>
	private List<String> rowdataList;
	private int minColumns;
	// 自定义行级别解析处理回调接口
	private IExcelRowHandler rowHandler;

	@Override
	public <T> ResultList<T> parse(InputStream is, final Class<T> clazz) throws ExcelException {
		//1.获取解析规则
		super.parseConfig(clazz);
		
		
		if (!ExcelConst.EXCEL_FORMAT_XLSX.equals(sheetConfig.excelSuffix())) {
			throw new ExcelException("excel格式非xlsx,无法继续解析");
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

//						// 3.字段映射位置
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
							
							originFieldContent = originFieldContent.trim();
							
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
							case JavaFieldType.TYPE_ARRAY_STRING:
								//数组是用来生成可选范围的，不解析
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
		this.rowHandler = rowHandler;
		this.parse(is, -1, 1);

	}

	@Override
	public Map<Integer, List<String>> parse(InputStream is, Integer minColumns, Integer sheetNums)
			throws ExcelException {

		try {
			if (null == minColumns) {
				minColumns = 1;
			}
			this.minColumns = minColumns;

			this.dataMap = new LinkedHashMap<Integer, List<String>>();

			OPCPackage xlsxPackage = OPCPackage.open(is);

			ReadOnlySharedStringsTable strings = new ReadOnlySharedStringsTable(xlsxPackage);
			XSSFReader xssfReader = new XSSFReader(xlsxPackage);
			StylesTable styles = xssfReader.getStylesTable();
			XSSFReader.SheetIterator iter = (XSSFReader.SheetIterator) xssfReader.getSheetsData();
			// 默认解析第一个sheet,若遍历全部sheet 使用while(iter.hasNext)
			int curSheetNum = 0;
			while (iter.hasNext() && curSheetNum <= sheetNums) {
				curSheetNum++;
				InputStream stream = iter.next();
				processSheet(styles, strings, new SheetToCSV(curSheetNum), stream);
				stream.close();
			}
			xlsxPackage.close();
		} catch (Exception e) {
			throw new ExcelException(e.getMessage());
		}
		return dataMap;
	}

	public void processSheet(StylesTable styles, ReadOnlySharedStringsTable strings, SheetContentsHandler sheetHandler,
			InputStream sheetInputStream) throws IOException, ParserConfigurationException, SAXException {
		DataFormatter formatter = new DataFormatter();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/mm/dd");
		formatter.setDefaultNumberFormat(dateFormat);
		InputSource sheetSource = new InputSource(sheetInputStream);
		try {
			XMLReader sheetParser = SAXHelper.newXMLReader();
			ContentHandler handler = new CustomXSSFSheetXMLHandler(styles, null, strings, sheetHandler, formatter,
					false);
			sheetParser.setContentHandler(handler);
			sheetParser.parse(sheetSource);
		} catch (ParserConfigurationException e) {
			throw new RuntimeException("SAX parser appears to be broken - " + e.getMessage());
		}
	}

	private class SheetToCSV implements SheetContentsHandler {
		private boolean firstCellOfRow = false;
		private int currentRow = -1;
		private int currentCol = -1;
		private int sheetIndex = -1;

		public SheetToCSV(int sheetIndex) {
			this.sheetIndex = sheetIndex;
		}

		private void outputMissingRows(int number) {

		}

		@Override
		public void startRow(int rowNum) {
			outputMissingRows(rowNum - currentRow - 1);
			firstCellOfRow = true;
			currentRow = rowNum;
			currentCol = -1;

			rowdataList = new ArrayList<String>();

		}

		@Override
		public void endRow(int rowNum) {
			// 确保保存到集合的每一行的列数相同
			for (int i = currentCol; i < minColumns; i++) {
				rowdataList.add("");
			}
			dataMap.put(rowNum + 1, rowdataList);// 存放一行数据，key是在excel中的实际行号，值范围1到n
			if (null != rowHandler) {
				try {
					rowHandler.handler(sheetIndex, rowNum + 1, rowdataList);
				} catch (ExcelException e) {
 					e.printStackTrace();
				}
			}
		}

		@Override
		public void cell(String cellReference, String formattedValue, XSSFComment comment) {

			if (firstCellOfRow) {

				firstCellOfRow = false;
			}

			// gracefully handle missing CellRef here in a similar way as
			// XSSFCell does
			if (cellReference == null) {
				cellReference = new CellAddress(currentRow, currentCol).formatAsString();
			}

			// Did we miss any cells?
			int thisCol = (new CellReference(cellReference)).getCol();
			int missedCols = thisCol - currentCol - 1;
			for (int i = 0; i < missedCols; i++) {
				rowdataList.add("");
			}
			currentCol = thisCol;

			// try {
			// rowdataList.add(Double.parseDouble(formattedValue)+"");
			// } catch (Exception e) {
			rowdataList.add(formattedValue);
			// }
		}

		@Override
		public void headerFooter(String text, boolean isHeader, String tagName) {
			// Skip, no headers or footers in CSV
		}
	}

}
