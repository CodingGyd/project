package com.codinggyd.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
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
	@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST },  consumes = "application/json;charset=UTF-8",produces = MediaType.APPLICATION_JSON_VALUE)
	public String run(HttpServletRequest request, HttpServletResponse response,@RequestBody String requestBean) throws Exception{
 		String result = MineServiceExecuter.invoke(requestBean);
 		System.out.println("服务器的数据长度:"+result.length());
 		return result;
 		//这里有问题，客户端打印的数据长度不一致,有待解决@@
//		response(request, response, result);
	}
	 
}
	
