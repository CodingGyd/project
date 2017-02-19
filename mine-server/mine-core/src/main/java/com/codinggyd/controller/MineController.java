package com.codinggyd.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codinggyd.bean.MineServiceBean;
import com.codinggyd.core.MineServiceContext;
import com.fasterxml.jackson.databind.JsonNode;

@RestController  
@RequestMapping(value="/api/func/", produces = MediaType.APPLICATION_JSON_VALUE)
public class MineController extends BaseController{
	
//	@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST }, consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST })
	public void run(HttpServletRequest request, HttpServletResponse response){
		OutputStream outputStream = null;
		String contentJson = "{\"ServiceId\":\"TEST_LEARN_SERVICE\",\"params\":[1]}";
		try {
			invoke(contentJson);
			//解析参数
			
			//执行业务方法
			//封装返回结果
			outputStream = response.getOutputStream();
			outputStream.write("sss".getBytes());
			outputStream.flush();
			outputStream.close();
		} catch (Exception e) {
			logger.error("{}",e);
		} finally {
			try {
				if (null != outputStream) {
					outputStream.close();
				} 
			} catch (IOException e) {
					logger.error("{}",e);
			}
		}

	}
	
	/**
	 * 解析json请求参数，动态执行业务方法
	 * @param requestJson
	 * @throws Exception
	 */
	private String invoke(String requestJson) throws Exception{
		JsonNode jsonNode = objectMapper.readTree(requestJson);
		String serviceId = jsonNode.get("ServiceId").asText();
		JsonNode params = jsonNode.get("params");
		
		int paramsSize = params.size();
		MineServiceBean mineServiceBean = MineServiceContext.getMineServiceBean(serviceId);
		Method method = mineServiceBean.getMethod();
		Class<?>[] paramsType = method.getParameterTypes();
		
		if (paramsType.length != paramsSize) {
			logger.error("参数个数不一致!");
		    
		}
		
		Object[] args = new Object[paramsType.length];
		for (int i=0;i<paramsType.length;i++) {
			Class<?> cs = paramsType[i];
			String className = cs.getSimpleName();
			JsonNode node = params.get(i);
			if ("List".equals(className)) {
				List<?> data = objectMapper.readValue(node.toString(), List.class);
				args[i] = data;
			} else if("Integer".equals(className)){
				args[i] = Integer.parseInt(node.asText());
			}  
		}
		Object result = method.invoke(mineServiceBean.getService(), args);
		logger.error(result.toString());
		return null;
	}
}
	
