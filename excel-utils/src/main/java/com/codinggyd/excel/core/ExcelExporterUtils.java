package com.codinggyd.excel.core;

import java.io.OutputStream;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
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
	 * excel通用生成接口,多种数据集类型sheet
 	 * @param sheetDatas 生成多个不同数据集类型sheet时,每个sheet的数据集配置对象
	 * @return 生成的excel对象
	 */
	@SuppressWarnings("rawtypes")
	public static Workbook exportBatch(List<SheetData> sheetDatas,String format) throws ExcelException{
 		return getExcelExporter(format).exportBatch(sheetDatas);
	}
	
	/**
	 * excel通用生成接口,单种数据集类型sheet
	 * @param <T> 泛型参数, 运行时指定
	 * @param clazz excel行记录结构对象
	 * @param data 待写入excel的数据集
	 * @param format excel格式
	 * @return 生成的excel对象
	 */
	public static <T> Workbook export(Class<T> clazz,List<T> data,String format) throws ExcelException{
		SheetData<T> sheetData = new SheetData<T>(clazz, data, format);
		return getExcelExporter(format).export(sheetData);
	}
	
	/**
	 * excel通用生成接口,多种数据集类型sheet
	 * @param <T> 泛型参数, 运行时指定
	 * @param sheetDatas 生成多个不同数据集类型sheet时,每个sheet的数据集配置对象
	 * @return 生成的excel对象
	 */
	@SuppressWarnings("rawtypes")
	public static void exportBatch(List<SheetData> sheetDatas,String format,OutputStream outputStream) throws ExcelException{
 		 getExcelExporter(format).exportBatch(sheetDatas,outputStream);
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
		getExcelExporter(format).export(sheetData, outputStream);
	}
	
	
	/**
	 * 根据Excel格式获取Excel生成实例
	 * @param format excel格式
	 * @return Excel生成实例
	 * @throws ExcelException
	 */
	private static <T> IExcelExporter getExcelExporter(String format) throws ExcelException {
		
		if (StringUtils.isEmpty(format) ) {
			throw new ExcelException("格式有误!");
		}
		
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
