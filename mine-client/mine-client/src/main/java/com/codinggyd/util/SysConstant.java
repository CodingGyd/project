package com.codinggyd.util;

 
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 
 * 
 * @Title:  SysConstant.java
 * @Package: com.codinggyd.util
 * @Description: 系统常量
 *
 * @author: guoyd
 * @Date: 2017年8月27日 下午1:03:32
 *
 * Copyright @ 2017 Corpration Name
 */
@Component
 public class SysConstant {

	public static final String RESPONSE_CODE_SUCCESS = "0000";
	
	public static final String ARTICLE_CONST_LB = "100";//文章分类类别代码
	
 	public static String SERVER_URL;
	
	@Value("${sys.server.url}")  
    public void setSERVER_URL(String serverUrl) {  
		SERVER_URL = serverUrl;  
    }
	
}
