package com.codinggyd.excel.core.parsexcel.service;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.codinggyd.excel.annotation.ExcelFieldConfig;
import com.codinggyd.excel.annotation.ExcelFieldRule;
import com.codinggyd.excel.exception.ExcelException;

/**
 * <pre>
 * 类名:  CommonReader.java
 * 包名:  com.codinggyd.excel.core.service
 * 描述:  XLSReader类和XLSXReader类中公用的方法抽取
 * 
 * 作者:  guoyd
 * 日期:  2017年11月26日
 *
 * Copyright @ 2017 Corpration Name
 * </pre>
 */
public abstract class CommonReader {
	
	public String checkRepaceRules(ExcelFieldConfig fieldConfig,String originFieldContent) throws ExcelException{
		
		if (StringUtils.isEmpty(originFieldContent)) {
			return originFieldContent;
		}
		
		originFieldContent = originFieldContent.trim();
		
		if (StringUtils.isEmpty(originFieldContent)) {
			return originFieldContent;
		}
		
		//1.获取配置的匹配替换规则
		String newContent = null;
		if (null != fieldConfig) {
			Map<String,String> map = new LinkedHashMap<>();
			ExcelFieldRule[] replaceRules = fieldConfig.replaces(); 
 			if (null != replaceRules && replaceRules.length > 0) {
				
				for (ExcelFieldRule rule : replaceRules) {
					if (StringUtils.isNotEmpty(rule.content())) {
						
						if (map.containsKey(rule.content())) {
							throw new ExcelException("字段["+fieldConfig.name()+"]的替换规则配置有重复,请检查配置后重新解析!");
						}
						
						map.put(rule.content(), rule.replace());
					}
				}
				
			}
 			
 			newContent = map.get(originFieldContent);
 			
		}
		
		return StringUtils.isNotEmpty(newContent) ? newContent : originFieldContent;
	}
}
