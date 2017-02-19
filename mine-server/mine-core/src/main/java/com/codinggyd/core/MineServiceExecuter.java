package com.codinggyd.core;

import java.lang.reflect.Method;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codinggyd.bean.MineServiceBean;
import com.codinggyd.util.DateUtils;
import com.codinggyd.util.MineFieldTypeUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @Title:  MineServiceExecuter
 * @Package: com.codinggyd.core
 * @Description: 解析请求参数,动态执行service业务类方法,封装结果集
 *
 * @author: guoyd
 * @Date: 2017年2月19日下午1:59:09
 *
 * Copyright @ 2017 Corpration Name
 */
public abstract class MineServiceExecuter {
	final static Logger logger = LoggerFactory.getLogger(MineServiceExecuter.class);

	static ObjectMapper objectMapper = new ObjectMapper();

	/**
	 * 解析json请求参数，动态执行业务方法
	 * @param requestJson
	 * @throws Exception
	 */
	public static String invoke(String requestJson) throws Exception{
		try {
			JsonNode jsonNode = objectMapper.readTree(requestJson);
			String serviceId = jsonNode.get("ServiceId").asText();
			JsonNode params = jsonNode.get("Params");
			
			MineServiceBean mineServiceBean = MineServiceContext.getMineServiceBean(serviceId);
			Method method = mineServiceBean.getMethod();
			Class<?>[] paramsType = method.getParameterTypes();
		 
			int paramsSize = params.size();
			if (paramsType.length != paramsSize) {
				logger.error("9999参数个数不一致!");
			    return null;
			}
			//解析请求参数,与业务方法参数类型进行一一转换
			Object[] args = new Object[paramsType.length];
			for (int i = 0;i < paramsType.length;i++) {
				Class<?> cs = paramsType[i];
				Integer code = MineFieldTypeUtils.getFieldType(cs);
				String className = cs.getSimpleName();
				JsonNode node = params.get(i);
				String nodeValue = node.asText();
				if ("List".equals(className)) {
					List<?> data = objectMapper.readValue(nodeValue, List.class);
					args[i] = data;
				} else if ("Integer".equals(className) || "int".equals(className)){
					args[i] = Integer.parseInt(nodeValue);
				}  else if ("String".equals(className) || "string".equals(className)) {
					args[i] = nodeValue;
				} else if ("Double".equals(className) || "double".equals(className)) {
					args[i] = Double.parseDouble(nodeValue);
				} else if ("Float".equals(className) || "float".equals(className)) {
					args[i] = Float.parseFloat(nodeValue);
				} else if ("Date".equals(className)) {
					args[i] = DateUtils.formatDate(nodeValue);
				}
			}
			Object result = method.invoke(mineServiceBean.getService(), args);
			String jsonResult = wrapResult(result);
			return jsonResult;
		} catch(Exception e) {
			logger.error("9999参数类型转换异常,{}",e);
			return null;
		}
	}
	
	/**
	 * 将业务方法执行的结果集封装为json字符串
	 * @param result
	 * @return
	 */
	private static String wrapResult(Object result){
		
		return null;
	}
	
}
