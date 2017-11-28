package com.codinggyd.excel.core.exportexcel.inter;

import java.io.OutputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

import com.codinggyd.excel.exception.ExcelException;

/**
 * <pre>
 * 类名:  IExcelExport.java
 * 包名:  com.codinggyd.excel.core.exportexcel.inter
 * 描述:  Excel通用导出接口
 * 
 * 作者:  guoyd
 * 日期:  2017年11月28日
 *
 * Copyright @ 2017 Corpration Name
 * </pre>
 */
public interface IExcelExport {
	
	/**
	 * excel通用生成接口
	 * @param clazz excel行记录结构对象
	 * @param data 待写入excel的数据集
	 * @return 生成的excel对象
	 */
	public <T> Workbook export(Class<?> clazz,List<T> data) throws ExcelException;
	
	/**
	 * excel通用生成接口
	 * @param clazz excel行记录结构对象
	 * @param data 待写入excel的数据集
	 * @param outputStream excel写入流
	 */
	public <T> void export(Class<?> clazz,List<T> data,OutputStream outputStream) throws ExcelException;
	
}
