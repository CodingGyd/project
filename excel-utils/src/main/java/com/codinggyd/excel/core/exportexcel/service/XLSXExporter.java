package com.codinggyd.excel.core.exportexcel.service;

import java.io.OutputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

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
	public <T> Workbook export(Class<?> clazz, List<T> data) throws ExcelException{
		
		if (null == data || null == clazz) {
			throw new ExcelException("数据或配置类对象不能为空");
		}
		
		Workbook workbook = null;
		try {
 			//1.初始化解析规则变量
			super.parseConfig(clazz);
			//2.开始写入excel
			workbook = new SXSSFWorkbook();
			Sheet sheet = super.createSheet(workbook);
			//2.1 创建标题行
			super.createTitleRow(sheet);
			//2.2 创建内容行
			super.createContentRow(sheet, data);
  		} catch (Exception e) {
			throw new ExcelException(e.getMessage());
 		}
 		return workbook;
	}

	@Override
	public <T> void export(Class<?> clazz, List<T> data, OutputStream outputStream) throws ExcelException{
		Workbook workbook = null;
		try {
			workbook = this.export(clazz, data);
			workbook.write(outputStream);
		} catch (Exception e) {
			throw new ExcelException(e.getMessage());
		} finally {
			try {
				
				if (null != outputStream) {
					outputStream.close();
				}
				
				if (null != workbook) {
					if (workbook instanceof SXSSFWorkbook) {
						SXSSFWorkbook wb = (SXSSFWorkbook) workbook;
						wb.dispose();
					}
					workbook.close();
				}
				
			} catch (Exception e){
				throw new ExcelException(e.getMessage());
			}
		}
		
	}
}
