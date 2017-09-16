package com.codinggyd.utils;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CommonUtils {
	
	private static ObjectMapper om;
	static {
		om = new ObjectMapper();
	}
	
	public  static ObjectMapper getMappingInstance() {
		return om;
	}
	
	public static JavaType getCollectionType(Class<?> collectionClass, JavaType javaType) {
		return om.getTypeFactory().constructCollectionLikeType(collectionClass, javaType);
	}
	
}
