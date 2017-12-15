package com.codinggyd.excel.core.exportexcel.service;

import java.io.OutputStream;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import com.codinggyd.excel.core.exportexcel.bean.SheetData;
import com.codinggyd.excel.core.exportexcel.inter.IExcelExporter;
import com.codinggyd.excel.exception.ExcelException;

/**
 * 
 * <pre>
 * 类名:  XLSXExporter.java
 * 包名:  com.codinggyd.excel.core.exportexcel.service
 * 描述:  xlsx格式的excel通用导出类
 * 
 * 作者:  guoyd
 * 日期:  2017年11月28日
 *
 * Copyright @ 2017 Corpration Name
 * </pre>
 */
public class XLSXExporter extends CommonExporter implements IExcelExporter{


	@Override
	public <T> Workbook export(SheetData<T> sheetData) throws ExcelException{
		
		if ( null == sheetData) {
			throw new ExcelException("配置对象不能为空");
		}
		
		Workbook workbook = null;
		try {
			//1.创建excel对象
			workbook = new SXSSFWorkbook();
			super.initSheet(workbook, sheetData, null);
  		} catch (Exception e) {
			throw new ExcelException(e.getMessage());
 		}
 		return workbook;
	}

	@Override
	public <T> void export(SheetData<T> sheetData, OutputStream outputStream) throws ExcelException{
		Workbook workbook = null;
		try {
			workbook = this.export(sheetData);
			workbook.write(outputStream);
		} catch (Exception e) {
			throw new ExcelException(e.getMessage());
		} finally {
			try {
				
				if (null != outputStream) {
					outputStream.close();
					outputStream = null;
				}
				
				if (null != workbook) {
					if (workbook instanceof SXSSFWorkbook) {
						SXSSFWorkbook wb = (SXSSFWorkbook) workbook;
						wb.dispose();
					}
					workbook.close();
					workbook = null;
				}
				
			} catch (Exception e){
				throw new ExcelException(e.getMessage());
			}
		}
		
	}
}
