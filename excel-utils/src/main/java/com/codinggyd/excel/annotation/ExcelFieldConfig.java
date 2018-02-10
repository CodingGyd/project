package com.codinggyd.excel.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.codinggyd.excel.constant.ExcelConst;
import com.codinggyd.excel.constant.JavaFieldType;

/**
 * <pre>
 * 类名:  ExcelFieldConfig.java
 * 包名:  com.codinggyd.excel.annotation
 * 描述:  excel字段解析规则配置
 * 
 * 作者:  guoyd
 * 日期:  2017年11月22日
 *
 * Copyright @ 2017 Corpration Name
 * </pre>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
public @interface ExcelFieldConfig {
	
	/**
	 * @return 字段是否为主键,excel解析某行时若主键字段值为空,则跳过该行
	 */
	boolean isPrimaryKey() default false;
	
	/**
	 * @return 字段所在列,从0开始
 	 */
	int index() default ExcelConst.EXCEL_FIELD_DEFAULT_INDEX;
	
	/**
	 * @return 字段对应JAVA数据类型
 	 */
	int javaType() default JavaFieldType.TYPE_STRING;
	
	/**
	 * @return 当字段类型为日期类型时,指定日期格式
 	 */
	String dateFormat() default "yyyy/MM/dd";
	
	/**
	 * @return 内容匹配替换规则,即匹配到某种字符串时替换为指定值,支持配置N条匹配规则.
	 */
	ExcelFieldRule[] replaces() default { @ExcelFieldRule(content = "", replace = "") };
	
	/**
	 * @return excel中生成单元格的宽度
	 */
	int width() default 20*256;
	
	/**
	 * @return excel中单元格的填充方式,默认不填充
	 * @see org.apache.poi.ss.usermodel.FillPatternType
	 */
	int fillPatternTypeCode() default 0;
	
	/**
	 * @return excel中单元格的填充颜色 ,仅当fillPatternTypeCode参数设置允许填充后才有效
	 * @see org.apache.poi.ss.usermodel.IndexedColors
	 */
	int fillPatternColor() default 22;
	
	/**
	 * @return 内容单元格是否重用当前列标题单元格的样式
	 */
	boolean useTitleStyle() default false;
	
	/**
	 * @return 设置字段的标题
	 */
	ExcelFieldTitleConfig titleConfig() ;
}
