package com.codinggyd.excel.core;

import java.io.OutputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

import com.codinggyd.excel.constant.ExcelConst;
import com.codinggyd.excel.core.exportexcel.bean.SheetData;
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
	 * @param format excel格式
	 * @return 生成的excel对象
	 */
	public static <T> Workbook export(Class<T> clazz,List<T> data,String format) throws ExcelException{
		SheetData<T> sheetData = new SheetData<T>(clazz, data, format);
		return getExcelExporter(sheetData).export(sheetData);
		
	}
	
	/**
	 * excel通用生成接口
	 * @param <T> 泛型参数, 运行时指定
	 * @param clazz excel行记录结构对象
	 * @param data 待写入excel的数据集
	 * @param format excel格式
	 * @param outputStream excel写入流
	 */
	public static <T> void export(Class<T> clazz,List<T> data,String format,OutputStream outputStream) throws ExcelException {
		SheetData<T> sheetData = new SheetData<T>(clazz, data, format);
		getExcelExporter(sheetData).export(sheetData, outputStream);
	}
	
	
	/**
	 * 根据Excel格式获取Excel生成实例
	 * @param format excel格式
	 * @return Excel生成实例
	 * @throws ExcelException
	 */
	private static <T> IExcelExporter getExcelExporter(SheetData<T> sheetData) throws ExcelException {
		
		if (null == sheetData ) {
			throw new ExcelException("配置有误!");
		}
		
		IExcelExporter excelExporter = null;
		if (ExcelConst.EXCEL_FORMAT_XLS.equals(sheetData.getFormat())) {
			excelExporter = new XLSExporter();
		} else if (ExcelConst.EXCEL_FORMAT_XLSX.equals(sheetData.getFormat())) {
			excelExporter = new XLSXExporter();
		} else {
			throw new ExcelException("格式错误,非xls或xlsx格式");
		}
	 
		return excelExporter;
 	}
}
