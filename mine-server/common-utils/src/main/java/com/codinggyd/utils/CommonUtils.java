package com.codinggyd.utils;

import java.text.SimpleDateFormat;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class CommonUtils {
	
	private static ObjectMapper om;
	private static final String PATTERN = "yyyy-MM-dd HH:mm:ss.SSS";
	static {
		om = new ObjectMapper();
	    // ==========自定义日期格式========
		om.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);//取消默认的timestamps形式
		om.setDateFormat(new SimpleDateFormat(PATTERN));
	}
	
	public  static ObjectMapper getMappingInstance() {
		return om;
	}
	
	public static JavaType getCollectionType(Class<?> collectionClass, JavaType javaType) {
		return om.getTypeFactory().constructCollectionLikeType(collectionClass, javaType);
	}
	
}
