package com.codinggyd.excel.core.exportexcel.service;

import java.io.OutputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import com.codinggyd.excel.core.exportexcel.inter.IExcelExport;
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
public class XLSXExporter extends CommonExporter implements IExcelExport{


	@Override
	public <T> Workbook export(Class<?> clazz, List<T> data) throws ExcelException{

		//1.初始化解析规则变量
		super.parseConfig(clazz);

		//2.开始写入excel
		Workbook workbook = new SXSSFWorkbook();
		Sheet sheet = createSheet(workbook);
		//2.1 创建标题行
		createTitleRow(sheet);
		//2.2 创建内容行
		
 		return workbook;
	}

	@Override
	public <T> void export(Class<?> clazz, List<T> data, OutputStream outputStream) throws ExcelException{
		
		//1.获取解析规则
		super.parseConfig(clazz);
		
	}
}
