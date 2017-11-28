package com.codinggyd.excel.core.exportexcel.service;

import java.io.OutputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

import com.codinggyd.excel.core.exportexcel.inter.IExcelExport;
import com.codinggyd.excel.exception.ExcelException;

/**
 * 
 * <pre>
 * 类名:  XLSExporter.java
 * 包名:  com.codinggyd.excel.core.exportexcel.service
 * 描述:  xls格式的excel通用导出类
 * 
 * 作者:  guoyd
 * 日期:  2017年11月28日
 *
 * Copyright @ 2017 Corpration Name
 * </pre>
 */
public class XLSExporter extends CommonExporter implements IExcelExport{

	
	@Override
	public <T> Workbook export(Class<?> clazz, List<T> data) throws ExcelException{

		//1.获取解析规则
		super.parseConfig(clazz);
		
		//2.判断需要生成多少个sheet
 		return null;
	}

	@Override
	public <T> void export(Class<?> clazz, List<T> data, OutputStream outputStream) throws ExcelException{
		
		//1.获取解析规则
		super.parseConfig(clazz);
		
	}

}
