package com.codinggyd.excel.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.codinggyd.excel.constant.ExcelConst;

/**
 * 
 * 类名:  ExcelFieldTitleConfig.java
 * 包名:  com.hundsun.gildata.faiscms.exceltools.annotation
 * 描述:  excel字段标题配置
 * 
 * 作者:  guoyd
 * 日期:  2018年2月9日
 *
 * Copyright @ 2017 Corpration Name
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
public @interface ExcelFieldTitleConfig {
	
	/**
	 * @return 标题名称
 	 */
	String name() default ExcelConst.EXCEL_FIELD_DEFAULT_NAME;
	
	/**
	 * @return 标题单元格的颜色填充方式,默认不填充
	 * @see org.apache.poi.ss.usermodel.FillPatternType
	 */
	int fillPatternTypeCode() default 0;
	
	/**
	 * @return 标题单元格的填充颜色 ,仅当fillPatternTypeCode参数设置允许填充后才有效
	 * @see org.apache.poi.ss.usermodel.IndexedColors
	 */
	int fillPatternColor() default 22;
 
}
