package com.codinggyd.util;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;


public class CustomExportUtil extends BaseExportUtil<Map<String,Object>>{

	 
	protected void exportSheet(Workbook wb,String sheetName,ServletOutputStream outputStream,List<String> titles,List<Map<String,Object>> data) throws IOException{
	
		if(CollectionUtils.isNotEmpty(data) && data.size() <= SHEET_MAX_ROW_SIZE){
			
			createSheet(wb, sheetName, titles,data);
			
		} else if (CollectionUtils.isNotEmpty(data) && data.size() > SHEET_MAX_ROW_SIZE) {
			
			//超过单个sheet所能允许的行数，分多个sheet
			int sheetCount = (data.size() + SHEET_MAX_ROW_SIZE - 1) / SHEET_MAX_ROW_SIZE;
   
			for (int i = 0 ; i <sheetCount-1; i++) {
				
				List<Map<String,Object>> subData = data.subList(i*SHEET_MAX_ROW_SIZE, (i+1)*SHEET_MAX_ROW_SIZE);
				createSheet(wb, sheetName+"_"+i, titles,subData);
		
			}
			
			List<Map<String,Object>> subData = data.subList((sheetCount-1)*SHEET_MAX_ROW_SIZE, data.size());
			createSheet(wb, sheetName+"_"+(sheetCount-1), titles,subData);
			
		} else {
			
			//导出空的sheet
			createSheet(wb, sheetName,titles, null);
			
		}

	}
	
	protected void createSheet(Workbook wb,String sheetName,List<String> titles,List<Map<String,Object>> data){
		
		Sheet sheet = wb.createSheet(sheetName);
		sheet.setDefaultColumnWidth(20);
		
		setSheetTitle(wb,sheet,titles);
		
		if (CollectionUtils.isNotEmpty(data)) {

			Row r;
			
			CellStyle style = wb.createCellStyle();
			  
			Font font = wb.createFont();
			font.setFontHeightInPoints((short) 11);
			style.setFont(font);
			
			String title;
			Object value;
			for(int i = 0 ;i < data.size() ; i++){
				
				Map<String,Object> map = data.get(i);
				r = sheet.createRow(SHEET_DATA_START_INDEX+i+1);
				
				for(int j = 0; j < titles.size(); j++) {
					title = titles.get(j);
					value = map.get(title);
					
					if (null != value) {
						
						createCell(r, j, style, value.toString());
					
					}
	
				}
				
			}
		}
		
	}
	 
	protected  void setSheetTitle(Workbook wb,Sheet sheet,List<String> titles){
		
		Row rowTitle = sheet.createRow(SHEET_DATA_START_INDEX);
		
		CellStyle style = wb.createCellStyle();
		 
		Font font = wb.createFont();
		font.setFontHeightInPoints((short) 11);
		style.setFont(font);
 
		for (int i = 0; i < titles.size(); i++) {
			createCell(rowTitle, i, style, titles.get(i));
		}
 
	}

	@Override
	public void export(ServletOutputStream outputStream,List<String> titles,List<Map<String,Object>> data) throws Exception{
		
		Workbook wb = new HSSFWorkbook();
		 
		exportSheet(wb,SHEET_NAME_DEFAULT,outputStream,titles,data);
		
		wb.write(outputStream);
		outputStream.close();
	}
}
