package com.codinggyd.util;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @Title:  MineFieldTypeTool
 * @Package: com.codinggyd.util
 * @Description: 字段类型工具类
 *
 * @author: guoyd
 * @Date: 2017年2月18日下午8:22:28
 *
 * Copyright @ 2017 Corpration Name
 */
public class MineFieldTypeTool {
	private static final Map<Class<?>,Integer> fieldTypesMap;
	static final Logger logger = LoggerFactory.getLogger(MineFieldTypeTool.class);

	static {
		
		fieldTypesMap = new HashMap<>();
		
		fieldTypesMap.put(byte.class, 1);
		fieldTypesMap.put(Byte.class, 1);
		fieldTypesMap.put(int.class, 1);
		fieldTypesMap.put(Integer.class, 1);
		
		fieldTypesMap.put(long.class, 2);
		fieldTypesMap.put(Long.class, 2);
		
		fieldTypesMap.put(float.class, 3);
		fieldTypesMap.put(Float.class, 3);

		fieldTypesMap.put(double.class, 4);
		fieldTypesMap.put(Double.class, 4);
		
		fieldTypesMap.put(BigDecimal.class, 5);
		
		fieldTypesMap.put(String.class, 6);
		fieldTypesMap.put(Date.class, 6);
		fieldTypesMap.put(Serializable.class, 6);
		
		fieldTypesMap.put(Time.class, 7);
		
		fieldTypesMap.put(byte[].class, 8);

	}
	
	public static int getFieldType(Class<?> colType) {
		if (fieldTypesMap.containsKey(colType)) {
			return fieldTypesMap.get(colType);
		}else{
			logger.error("找不到此类型{}"+colType);
			return 6;
		}
	}
}
