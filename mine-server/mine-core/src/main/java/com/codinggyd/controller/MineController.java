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
//	@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST }, consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST },  consumes = "application/json;charset=UTF-8",produces = MediaType.APPLICATION_JSON_VALUE)
	public String run(HttpServletRequest request, HttpServletResponse response,@RequestBody String requestBean) throws Exception{
 		String result = MineServiceExecuter.invoke(requestBean);
 		System.out.println("服务器的数据长度:"+result.length());
 		//解决错误:已拦截跨源请求：同源策略禁止读取位于 http://127.0.0.1:8080/mine-client/data/utilfunction 的远程资源。（原因：CORS 头缺少 'Access-Control-Allow-Origin'）。
 		response.addHeader("Access-Control-Allow-Origin", "*");
 		return result;
 		//这里有问题，客户端打印的数据长度不一致,有待解决@@
//		response(request, response, result);
	}
	
}
	
