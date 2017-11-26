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
 * 描述:  excel导入字段解析规则配置
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
	 * @return 字段名称
 	 */
	String name() default ExcelConst.EXCEL_FIELD_DEFAULT_NAME;
	
	/**
	 * @return 字段所在列,从0开始
 	 */
	int index() default ExcelConst.EXCEL_FIELD_DEFAULT_INDEX;
	
	/**
	 * @return 字段转换的JAVA数据类型
 	 */
	int javaType() default JavaFieldType.TYPE_STRING;
	
	/**
	 * @return 当字段类型为日期类型时,指定日期格式
 	 */
	String dateFormat() default "yyyy/MM/dd";
}
