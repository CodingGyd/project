package com.codinggyd.excel.core.parsexcel.service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.codinggyd.excel.annotation.ExcelFieldConfig;
import com.codinggyd.excel.annotation.ExcelFieldRule;
import com.codinggyd.excel.annotation.ExcelSheetConfig;
import com.codinggyd.excel.constant.ExcelConst;
import com.codinggyd.excel.exception.ExcelException;

/**
 * <pre>
 * 类名:  CommonParser.java
 * 包名:  com.codinggyd.excel.core.parsexcel.service
 * 描述:  XLSReader类和XLSXReader类中公用的方法抽取
 * 
 * 作者:  guoyd
 * 日期:  2017年11月26日
 *
 * Copyright @ 2017 Corpration Name
 * </pre>
 */
public abstract class CommonParser {
	//excel导入Sheet解析规则配置描述
	protected ExcelSheetConfig sheetConfig;
	//excel导入字段解析规则配置
	protected Map<String, Field> fieldsMapByName;//key：字段名称，value：字段对象
	protected List<ExcelFieldConfig> fieldConfigs;//字段规则集合
	
	public <T> void parseConfig(Class<T> clazz) {
		
		// 1.excel导入Sheet解析规则配置描述
		sheetConfig = clazz.getAnnotation(ExcelSheetConfig.class);
		if (null == sheetConfig) {
			throw new ExcelException("未配置sheet解析规则,无法继续解析");
		}

		if (!ExcelConst.EXCEL_FORMAT_XLS.equals(sheetConfig.excelSuffix())) {
			throw new ExcelException("excel格式非xls,无法继续解析");
		}

		// 2.excel导入字段解析规则配置描述
		Field[] fields = clazz.getDeclaredFields();
		if (null == fields || fields.length == 0) {
			throw new ExcelException("未配置字段列映射规则,无法继续解析");
		}

		fieldsMapByName = new LinkedHashMap<String, Field>();
		fieldConfigs = new ArrayList<ExcelFieldConfig>();
		for (Field field : fields) {

			ExcelFieldConfig config = field.getAnnotation(ExcelFieldConfig.class);

			if (null == config) {
				// throw new
				// ExcelException("字段["+field.getName()+"]未配置字段列映射规则,无法继续解析");
				continue;
			}
			fieldsMapByName.put(config.name(), field);
			fieldConfigs.add(config);
		}
	}
	
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
