package com.codinggyd.util;

import java.util.List;

import javax.servlet.ServletOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
 

/**
 * 
 * @Title:  BaseExportUtil.java
 * @Package: com.codinggyd.util
 * @Description: 抽象导出类
 *
 * @author: guoyd
 * @Date: 2017年3月26日下午1:07:00
 *
 * Copyright @ 2017 Corpration Name
 */
public abstract class BaseExportUtil<T> {

	protected static final String SHEET_NAME_DEFAULT = "导出数据";//默认导出sheet名称
	protected static final Integer SHEET_MAX_ROW_SIZE = 65000;//excel2003版的xls格式只允许一个sheet最多有65536条记录
	protected static final Integer SHEET_DATA_START_INDEX = 1;//excel数据生成的起始行
	
	/**
	 * 设置单元格信息
	 * @param row 所在行对象
	 * @param index 所在列索引
	 * @param style 单元格样式
	 * @param value 单元格值
	 */
	protected static void createCell(Row row, int index, CellStyle style, String value){
		Cell cellOrgNo = row.createCell(index);//机构号
		cellOrgNo.setCellStyle(style);
		cellOrgNo.setCellValue(value);
	}
	
	/**
	 * 
	 * @param outputStream
	 * @param titles
	 * @param data
	 * @throws Exception
	 */
	public abstract void export(ServletOutputStream outputStream,List<String> titles,List<T> data)  throws Exception ;
}
