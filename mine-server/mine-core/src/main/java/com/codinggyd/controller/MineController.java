package com.codinggyd.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codinggyd.bean.requ.MineRequestBean;
import com.codinggyd.constant.SYSSecurityConstant;
import com.codinggyd.core.MineServiceExecuter;
import com.codinggyd.utils.AESUtils;
import com.codinggyd.utils.CommonUtils;
import com.codinggyd.utils.ZipUtils;

 
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
	@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST },  consumes = "application/json",produces = MediaType.APPLICATION_JSON_VALUE)
	public void run(HttpServletRequest request, HttpServletResponse response,@RequestBody byte[] data) throws Exception{
		

		data = AESUtils.decrypt(data, SYSSecurityConstant.HTTP_POST_AES_KEY, 16);
		data = ZipUtils.gunzip(data);
		String requestJson = new String(data);
		MineRequestBean mineRequestBean = CommonUtils.getMappingInstance().readValue(requestJson, MineRequestBean.class);
		logger.info("POST 参数:{}",requestJson);
 		String result = MineServiceExecuter.invoke(mineRequestBean);
		response(request, response, result,mineRequestBean);
	}
	 
}
	
