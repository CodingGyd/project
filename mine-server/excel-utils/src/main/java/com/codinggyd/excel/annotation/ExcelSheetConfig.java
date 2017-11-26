package com.codinggyd.excel.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.codinggyd.excel.constant.ExcelConst;

/**
 * <pre>
 * 类名:  ExcelSheetConfig.java
 * 包名:  com.codinggyd.excel.annotation
 * 描述:  excel导入Sheet解析规则性配置
 * 
 * 作者:  guoyd
 * 日期:  2017年11月26日
 *
 * Copyright @ 2017 Corpration Name
 * </pre>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
public @interface ExcelSheetConfig {
	
	/**
	 * @return 标题所在行
 	 */
	int titleRowStartIndex() default ExcelConst.EXCEL_SHEET_DEFAULT_TITLE_START_INDEX;
	
	/**
	 * @return 内容起始行
	 */
	int contentRowStartIndex() default ExcelConst.EXCEL_SHEET_DEFAULT_CONTENT_START_INDEX;
	
	/**
	 * @return sheet页数
	 */
	int sheetTotalCount() default ExcelConst.EXCEL_SHEET_DEFAULT_NUM;
	
	/**
	 * @see com.codinggyd.excel.constant.ExcelConst#EXCEL_FORMAT_XLS
	 * @return excel文件格式
 	 */
	String excelSuffix() default ExcelConst.EXCEL_FORMAT_XLSX;
}
