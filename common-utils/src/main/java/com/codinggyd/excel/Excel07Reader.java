package com.codinggyd.excel;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
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

import com.codinggyd.excel.CustomXSSFSheetXMLHandler.SheetContentsHandler;
 
/**
 * 
 * @Title:  Excel07Reader.java
 * @Package: com.codinggyd.excel
 * @Description: xlsx格式的excel解析工具类
 *
 * @author: guoyd
 * @Date: 2016年11月16日 下午19:01:34
 *
 * Copyright @ 2016 Corpration Name
 */
public class Excel07Reader implements ExcelReader{

	private Map<Integer,List<String>> dataMap = new LinkedHashMap<Integer,List<String>>();//excel多行数据<行号,数据集>
	private List<String> rowdataList = new ArrayList<String>();//存放一行数据
	
	/**
	 * 解析好的行数据,一行中每一列字段的实际含义与具体业务相关
	 * Map<excel中的实际行号,行数据集>
	 * @return  dataMap 
	 */
	public Map<Integer,List<String>>  getDataMap() {
		return dataMap;
	}

	private class SheetToCSV implements SheetContentsHandler {
		private boolean firstCellOfRow = false;
		private int currentRow = -1;
		private int currentCol = -1;

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
	         for (int i=currentCol; i<minColumns; i++) {
	             rowdataList.add("");
	         }
			if (rowdataList != null && rowdataList.size() > 0) {
				dataMap.put(rowNum+1, rowdataList);//存放一行数据，key是在excel中的实际行号，值范围1到n
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

//			try {
//				rowdataList.add(Double.parseDouble(formattedValue)+"");
//			} catch (Exception e) {
				rowdataList.add(formattedValue);
//			}
		}

		@Override
		public void headerFooter(String text, boolean isHeader, String tagName) {
			// Skip, no headers or footers in CSV
		}
	}

	private final OPCPackage xlsxPackage;
	 /**
	  * 模板 要求的字段列个数
	  */
	 private final int minColumns;
 
	public Excel07Reader(InputStream fileSource,String filename, int minColumns) throws Exception {

	 	OPCPackage p = null;
		try {
			p = OPCPackage.open(fileSource);
		} catch (Exception e) {
			throw new Exception(e);
		}
		
		this.xlsxPackage = p;
		this.minColumns = minColumns;
	}
	public Excel07Reader(String path,String filename, int minColumns) {
	 	OPCPackage p = null;
		try {
			
			InputStream fileSource = new FileInputStream(path);
			p = OPCPackage.open(fileSource);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		this.xlsxPackage = p;
		this.minColumns = minColumns;
	}
	public void processSheet(StylesTable styles, ReadOnlySharedStringsTable strings, SheetContentsHandler sheetHandler,
			InputStream sheetInputStream) throws IOException, ParserConfigurationException, SAXException {
		DataFormatter formatter = new DataFormatter();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/mm/dd");
		formatter.setDefaultNumberFormat(dateFormat);
		InputSource sheetSource = new InputSource(sheetInputStream);
		try {
			XMLReader sheetParser = SAXHelper.newXMLReader();
			ContentHandler handler = new CustomXSSFSheetXMLHandler(styles, null, strings, sheetHandler, formatter, false);
			sheetParser.setContentHandler(handler);
			sheetParser.parse(sheetSource);
		} catch (ParserConfigurationException e) {
			throw new RuntimeException("SAX parser appears to be broken - " + e.getMessage());
		}
	}

	/**
	 * Initiates the processing of the XLS workbook file to CSV.
	 *
	 * @throws IOException
	 * @throws OpenXML4JException
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 */
	@Override
	public Map<Integer,List<String>> process() throws IOException, OpenXML4JException, ParserConfigurationException, SAXException {
		ReadOnlySharedStringsTable strings = new ReadOnlySharedStringsTable(this.xlsxPackage);
		XSSFReader xssfReader = new XSSFReader(this.xlsxPackage);
		StylesTable styles = xssfReader.getStylesTable();
		XSSFReader.SheetIterator iter = (XSSFReader.SheetIterator) xssfReader.getSheetsData();
		// 默认解析第一个sheet,若遍历全部sheet 使用while(iter.hasNext)
		if (iter.hasNext()) {
			InputStream stream = iter.next();
			processSheet(styles, strings, new SheetToCSV(), stream);
			stream.close();
		}
		xlsxPackage.close();
		return dataMap;
	}
}
