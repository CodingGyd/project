package com.codinggyd.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codinggyd.core.MineServiceContext;
import com.codinggyd.func.MineServiceBean;
import com.fasterxml.jackson.databind.JsonNode;

@RestController  
@RequestMapping(value="/api/func/", produces = MediaType.APPLICATION_JSON_VALUE)
public class MineController extends BaseController{
	
//	@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST }, consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST })
	public void run(HttpServletRequest request, HttpServletResponse response){
		OutputStream outputStream = null;
		String contentJson = "{\"ServiceId\":\"TEST_LEARN_SERVICE\",\"params\":[[1,2,3],\"3019\",\"1\",\"pc\",\"66AF37A25437C3A3B04CFEC09E281171\"]}";
		try {
			JsonNode jsonNode = objectMapper.readTree(contentJson);
			String serviceId = jsonNode.get("ServiceId").asText();
			JsonNode params = jsonNode.get("params");
			JsonNode jsonNode1 = params.get(0);
		
			MineServiceBean mineServiceBean = MineServiceContext.getMineServiceBean(serviceId);
			Method method = mineServiceBean.getMethod();
			Object object = method.invoke(mineServiceBean.getService());
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
}
	
