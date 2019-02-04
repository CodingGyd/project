package com.codinggyd.excel;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
 
/**
 * 
 * 
 * @Title:  Excel03Reader.java
 * @Package: com.codinggyd.excel
 * @Description: xls格式的excel解析工具类
 *
 * @author: guoyd
 * @Date: 2016年11月17日 上午10:01:34
 *
 * Copyright @ 2016 Corpration Name
 */
public class Excel03Reader implements HSSFListener, ExcelReader {
	private int minColumns;
	private POIFSFileSystem fs;
//	private PrintStream output;

	private int lastRowNumber;
	private int lastColumnNumber;
	private Map<Integer,List<String>> dataMap = new LinkedHashMap<Integer,List<String>>();//excel多行数据<行号,数据集>
	private List<String> rowdataList = new ArrayList<String>();//存放一行数据
	/** Should we output the formula, or the value it has? */
	private boolean outputFormulaValues = true;

	/** For parsing Formulas */
	private SheetRecordCollectingListener workbookBuildingListener;
	private HSSFWorkbook stubWorkbook;

	// Records we pick up as we process
	private SSTRecord sstRecord;
	private CustomFormatTrackingHSSFListener formatListener;

	/** So we known which sheet we're on */
	@SuppressWarnings("unused")
	private int sheetIndex = -1;
	private BoundSheetRecord[] orderedBSRs;
	private List<BoundSheetRecord> boundSheetRecords = new ArrayList<BoundSheetRecord>();

	// For handling formulas with string results
	private int nextRow;
	private int nextColumn;
	private boolean outputNextStringRecord;

	
	public Map<Integer, List<String>> getDataMap() {
		return dataMap;
	}

	/**
	 * Creates a new XLS -> CSV converter
	 * 
	 * @param fs
	 *            The POIFSFileSystem to process
	 * @param output
	 *            The PrintStream to output the CSV to
	 * @param minColumns
	 *            The minimum number of columns to output, or -1 for no minimum
	 */
	public Excel03Reader(POIFSFileSystem fs, int minColumns) {
		this.fs = fs;
		this.minColumns = minColumns;
	}

	/**
	 * Creates a new XLS -> CSV converter
	 * 
	 * @param filename
	 *            The file to process
	 * @param minColumns
	 *            The minimum number of columns to output, or -1 for no minimum
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public Excel03Reader(String filename, int minColumns) throws IOException, FileNotFoundException {
		this(new POIFSFileSystem(new FileInputStream(filename)),  minColumns);
	}
	
	public Excel03Reader(InputStream is,String filename, int minColumns) throws IOException, FileNotFoundException {
		this(new POIFSFileSystem(is), minColumns);
	}

	/**
	 * Initiates the processing of the XLS file to CSV
	 */
	@Override
	public Map<Integer,List<String>> process() throws IOException {
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

		factory.processWorkbookEvents(request, fs);
		fs.close();
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
//				output.println();
//				output.println(orderedBSRs[sheetIndex].getSheetname() + " [" + (sheetIndex + 1) + "]:");
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
//				thisStr = '"' + HSSFFormulaParser.toFormulaString(stubWorkbook, frec.getParsedExpression()) + '"';
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
//			thisStr = '"' + lrec.getValue() + '"';
			thisStr = lrec.getValue();
			break;
		case LabelSSTRecord.sid:
			LabelSSTRecord lsrec = (LabelSSTRecord) record;

			thisRow = lsrec.getRow();
			thisColumn = lsrec.getColumn();
			if (sstRecord == null) {
//				thisStr = '"' + "(No SST Record, can't identify string)" + '"';
				thisStr = "(No SST Record, can't identify string)";

			} else {
//				thisStr = '"' + sstRecord.getString(lsrec.getSSTIndex()).toString() + '"';
				thisStr = sstRecord.getString(lsrec.getSSTIndex()).toString();

			}
			break;
		case NoteRecord.sid:
			NoteRecord nrec = (NoteRecord) record;

			thisRow = nrec.getRow();
			thisColumn = nrec.getColumn();
			// TODO: Find object to match nrec.getShapeId()
//			thisStr = '"' + "(TODO)" + '"';
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
//			thisStr = '"' + "(TODO)" + '"';
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
			if (thisColumn > 0) {
//				rowdataList.add("");
//				output.print(',');
			}
			if("".equals(thisStr)){
				rowdataList.add("");//空数据占位,让每一行的每一列的位置保持一致
			}else{
				rowdataList.add(thisStr);
			}
//			output.print(thisStr);
		}

		// Update column and row count
		if (thisRow > -1) {
			lastRowNumber = thisRow;
		}
		if (thisColumn > -1) {
			lastColumnNumber = thisColumn;
		}
		// Handle end of row
		if (record instanceof LastCellOfRowDummyRecord) {
			// Print out any missing commas if needed
			if (minColumns > 0) {
				// Columns are 0 based
				if (lastColumnNumber == -1) {
					lastColumnNumber = 0;
				}
				for (int i = lastColumnNumber; i < (minColumns); i++) {
//					output.print(',');
					rowdataList.add("");
				}
			}
	 
			dataMap.put(lastRowNumber+1, rowdataList);
			rowdataList = new ArrayList<String>();
			// We're onto a new row
			lastColumnNumber = -1;

			// End the row
//			output.println();
		}
	}
}