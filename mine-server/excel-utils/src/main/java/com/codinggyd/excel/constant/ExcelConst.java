package com.codinggyd.excel.constant;

/**
 * <pre>
 * 类名:  ExcelConst.java
 * 包名:  com.codinggyd.excel.constant
 * 描述:  Excel相关常量集合
 * 
 * 作者:  guoyd
 * 日期:  2017年11月22日
 *
 * Copyright @ 2017 Corpration Name
 * </pre>
 */
public interface ExcelConst {
	
	/////////////////////////////////////////Excel格式属性/////////////////////////////////////////////////////

	/**
	 * Excel格式-xls
	 */
	String EXCEL_FORMAT_XLS = ".xls";
	
	/**
	 * Excel格式-xlsx
	 */
	String EXCEL_FORMAT_XLSX = ".xlsx";
	
	/**
	 * xls格式的单个sheet最多可以有65535行记录
	 */
	int EXCEL_XLS_MAX_ROW_NUM = 65500;
	
	/////////////////////////////////////////Excel-SHEET属性默认配置/////////////////////////////////////////////////////
	
	/**
	 * SHEET默认名称
	 */
	String EXCEL_DEFAULT_SHEET_NAME = "缺省名称";
	
	/**
	 * Excel的sheet默认个数
	 */
	int EXCEL_SHEET_DEFAULT_NUM = 1;
	
	/**
	 * Excel的sheet中标题默认所在行
	 */
	int EXCEL_SHEET_DEFAULT_TITLE_START_INDEX = 1;
	
	/**
	 * Excel的sheet中内容默认起始行
	 */
	int EXCEL_SHEET_DEFAULT_CONTENT_START_INDEX = 2;
	
	////////////////////////////////////////Excel-字段属性默认配置//////////////////////////////////////////////////////////
	/**
	 * Excel字段默认解析值
	 */
	String EXCEL_FIELD_DEFAULT_NAME = "";
	
	/**
	 * Excel字段列默认索引
	 */
	int EXCEL_FIELD_DEFAULT_INDEX = 0;
}
