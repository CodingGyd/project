package com.codinggyd.controller;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * 
 * @Title:  BaseController
 * @Package: com.codinggyd.controller
 * @Description: 
 *
 * @author: guoyd
 * @Date: 2017年2月20日下午3:28:28
 *
 * Copyright @ 2017 Corpration Name
 */
public abstract class BaseController {
	final Logger logger = LoggerFactory.getLogger(getClass());
	
	ObjectMapper objectMapper = new ObjectMapper();
	
	/**
	 * 返回数据给客户端
	 * @param request
	 * @param response
	 * @param result
	 */
	public void response(HttpServletRequest request, HttpServletResponse response, String result) {
		OutputStream out = null;
		try {
	 		//解决错误:已拦截跨源请求：同源策略禁止读取位于 http://127.0.0.1:8080/mine-client/data/utilfunction 的远程资源。（原因：CORS 头缺少 'Access-Control-Allow-Origin'）。
			response.setStatus(200);
			response.setContentLength(result.length());
			response.setContentType("application/json; charset=UTF-8");
			
			out = response.getOutputStream();
			out.write(result.getBytes());
			out.flush();
			out.close();
		} catch (Exception e) {
			logger.error("响应出错{}",e);
		} finally {
			try {
				if (null != out) {
					out.close();
				}
			} catch (IOException e) {
				logger.error("响应出错{}",e);
 			}
		}
	}
	 
	@ExceptionHandler({ Exception.class })
	public void exception(Exception e, HttpServletRequest request, HttpServletResponse response) {
		logger.error("系统异常,{}",e);
		response(request,response,"系统异常");
	}
}
	
