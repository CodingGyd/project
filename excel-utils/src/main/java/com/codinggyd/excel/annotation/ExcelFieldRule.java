package com.codinggyd.excel.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <pre>
 * 类名:  ExcelFieldRule.java
 * 包名:  com.codinggyd.excel.annotation
 * 描述:  excel字段解析内容替换规则配置
 * 
 * 作者:  guoyd
 * 日期:  2017年11月26日
 *
 * Copyright @ 2017 Corpration Name
 * </pre>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
public @interface ExcelFieldRule {
	
	/**
	 * @return 要替换的原始内容
	 */
	String content() ;
	
	/**
	 * @return 替换值
	 */
	String replace() ;
	
}
