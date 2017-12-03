package com.codinggyd.excel.core;

import java.io.OutputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

import com.codinggyd.excel.constant.ExcelConst;
import com.codinggyd.excel.core.exportexcel.inter.IExcelExporter;
import com.codinggyd.excel.core.exportexcel.service.XLSExporter;
import com.codinggyd.excel.core.exportexcel.service.XLSXExporter;
import com.codinggyd.excel.exception.ExcelException;

/**
 * 
 * <pre>
 * 类名:  ExcelExporterUtils.java
 * 包名:  com.codinggyd.excel.core
 * 描述:  Excel生成工具类 兼容XLS和XLSX
 * 
 * 作者:  guoyd
 * 日期:  2017年12月3日
 *
 * Copyright @ 2017 Corpration Name
 * </pre>
 */
public class ExcelExporterUtils {
	 
	/**
	 * excel通用生成接口
	 * @param <T> 泛型参数, 运行时指定
	 * @param clazz excel行记录结构对象
	 * @param data 待写入excel的数据集
	 * @return 生成的excel对象
	 */
	public static <T> Workbook export(String format,Class<?> clazz,List<T> data) throws ExcelException{
		return getExcelExporter(format).export(clazz, data);
		
	}
	
	/**
	 * excel通用生成接口
	 * @param <T> 泛型参数, 运行时指定
	 * @param clazz excel行记录结构对象
	 * @param data 待写入excel的数据集
	 * @param outputStream excel写入流
	 */
	public static <T> void export(String format,Class<?> clazz,List<T> data,OutputStream outputStream) throws ExcelException {
		getExcelExporter(format).export(clazz, data, outputStream);
	}
	
	
	/**
	 * 根据Excel格式获取Excel生成实例
	 * @param format excel格式
	 * @return Excel生成实例
	 * @throws ExcelException
	 */
	private static IExcelExporter getExcelExporter(String format) throws ExcelException {
		
		IExcelExporter excelExporter = null;
		if (ExcelConst.EXCEL_FORMAT_XLS.equals(format)) {
			excelExporter = new XLSExporter();
		} else if (ExcelConst.EXCEL_FORMAT_XLSX.equals(format)) {
			excelExporter = new XLSXExporter();
		} else {
			throw new ExcelException("格式错误,非xls或xlsx格式");
		}
	 
		return excelExporter;
 	}
}
