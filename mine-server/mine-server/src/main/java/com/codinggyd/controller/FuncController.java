package com.codinggyd.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codinggyd.bean.UserInfo;
import com.codinggyd.log.Log;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author guoyading
 *
 * create at 2016年8月21日 下午5:25:55
 */
@RestController
@RequestMapping("/api/func/")
public class FuncController {
	private static final Logger logger = Log.get();
	
	@RequestMapping("login")
	public String login(@RequestParam("username") String username,
			@RequestParam("password") String password,
			HttpServletRequest request) throws Exception{
		UserInfo info = new UserInfo();
		info.setPhone("15974154924");
		info.setPassword("123");
		ObjectMapper objectMapper = new ObjectMapper();
		String data = objectMapper.writeValueAsString(info);
		//这里用来解决跨域问题
		String callback = request.getParameter("callback");//用来解决跨域访问问题，jquery方式提交生成的callback,需要包在json数据的最外层,再返回给客户端
		String result =  callback+"(" + data + ")";
		logger.debug(result);
		return result;
	}
}
