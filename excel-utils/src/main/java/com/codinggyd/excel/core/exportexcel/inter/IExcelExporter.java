package com.codinggyd.excel.core.exportexcel.inter;

import java.io.OutputStream;

import org.apache.poi.ss.usermodel.Workbook;

import com.codinggyd.excel.core.exportexcel.bean.SheetData;
import com.codinggyd.excel.exception.ExcelException;

/**
 * <pre>
 * 类名:  IExcelExporter.java
 * 包名:  com.codinggyd.excel.core.exportexcel.inter
 * 描述:  Excel通用导出接口
 * 
 * 作者:  guoyd
 * 日期:  2017年11月28日
 *
 * Copyright @ 2017 Corpration Name
 * </pre>
 */
public interface IExcelExporter {
	
	/**
	 * excel通用生成接口
	 * @param <T> 泛型参数, 运行时指定
	 * @param sheetData excel的工作簿生成信息
	 * @return 生成的excel对象
	 */
	<T> Workbook export(SheetData<T> sheetData) throws ExcelException;
	
	/**
	 * excel通用生成接口
	 * @param <T> 泛型参数, 运行时指定
 	 * @param sheetData excel的工作簿生成信息
	 * @param outputStream excel写入流
	 */
	<T> void export(SheetData<T> sheetData,OutputStream outputStream) throws ExcelException;
	
}
