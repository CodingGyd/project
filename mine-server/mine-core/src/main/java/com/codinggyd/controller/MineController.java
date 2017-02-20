package com.codinggyd.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codinggyd.core.MineServiceExecuter;

/**
 * 
 * @Title:  MineController
 * @Package: com.codinggyd.controller
 * @Description: 应用程序请求访问入口
 *
 * @author: guoyd
 * @Date: 2017年2月20日下午3:25:25
 *
 * Copyright @ 2017 Corpration Name
 */
@RestController  
@RequestMapping(value="/api/func/", produces = MediaType.APPLICATION_JSON_VALUE)
public class MineController extends BaseController{
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @throws Exception 
	 */
//	@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST }, consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST },  produces = MediaType.APPLICATION_JSON_VALUE)
	public void run(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String contentJson = "{1\"ServiceId\":\"TEST_LEARN_SERVICE\",\"Params\":[[1,2]]";
		String result = MineServiceExecuter.invoke(contentJson);
		response(request, response, result);
	}
	
}
	
