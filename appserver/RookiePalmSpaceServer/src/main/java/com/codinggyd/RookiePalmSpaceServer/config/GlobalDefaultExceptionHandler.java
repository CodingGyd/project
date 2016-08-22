package com.codinggyd.RookiePalmSpaceServer.config;
  

import org.json.JSONObject;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codinggyd.RookiePalmSpaceServer.bean.ResponseFlag;

/**
 * 
 * @author guoyading
 * 对异常进行默认处理
 * create at 2016年8月21日 下午4:54:56
 */
@ControllerAdvice

public class GlobalDefaultExceptionHandler {
	
	@ExceptionHandler(value=Exception.class)
	@ResponseBody
	public String defaultExceptionHandler(){
		ResponseFlag responseFlag = new ResponseFlag();
		responseFlag.status ="failure";
		responseFlag.msg ="系统异常";
		return JSONObject.wrap(responseFlag).toString();
	}
}
