package com.codinggyd.utils;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

import com.codinggyd.json.CustomObjectMapper;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class CommonUtils {
	
	private static CustomObjectMapper om;
	private static final String PATTERN = "yyyy-MM-dd HH:mm:ss";

	static {
//		om = new CustomObjectMapper();
//	    // ==========自定义日期格式========
////		om.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);//取消默认的timestamps形式
//		
//		om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//		om.setDateFormat(new SimpleDateFormat(PATTERN));
//		// 该特性决定parser是否允许JSON整数以多个0开始(比如，如果000001赋值给json某变量)
//		om.configure(JsonParser.Feature.ALLOW_NUMERIC_LEADING_ZEROS, true);
		
		
		om = new CustomObjectMapper() ;
		om.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);//取消默认的timestamps形式
		om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		SimpleDateFormat sft=new SimpleDateFormat(PATTERN);
		om.setDateFormat(sft);
		om.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		// 该特性决定parser是否允许JSON整数以多个0开始(比如，如果000001赋值给json某变量)
		om.configure(JsonParser.Feature.ALLOW_NUMERIC_LEADING_ZEROS, true);
	}
	
	public  static ObjectMapper getMappingInstance() {
		return om;
	}
	
	public static JavaType getCollectionType(Class<?> collectionClass, JavaType javaType) {
		return om.getTypeFactory().constructCollectionLikeType(collectionClass, javaType);
	}
	
}
