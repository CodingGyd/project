package com.codinggyd.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.codinggyd.bean.requ.MineRequestBean;
import com.codinggyd.constant.SYSSecurityConstant;
import com.codinggyd.log.Log;
import com.codinggyd.util.FuncHttpUtils;
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
	 
	/**
	 * 返回数据给客户端
	 * @param request
	 * @param response
	 * @param result
	 */
	public void response(HttpServletRequest request, HttpServletResponse response, String result,MineRequestBean mineRequestBean) {

		try {
			FuncHttpUtils.response(request, response, result.getBytes("UTF-8"),
					SYSSecurityConstant.HTTP_POST_AES_KEY.getBytes());
		} catch (Exception e) {
			Log.error(logger, e, "序列化失败,请求参数:[{}]", mineRequestBean.getServiceId());
		}
 
	}
	 
	@ExceptionHandler({ Exception.class })
	public void exception(Exception e, HttpServletRequest request, HttpServletResponse response) {
		logger.error("系统异常,{}",e);
		response(request,response,"系统异常",new MineRequestBean());
	}
}
	
