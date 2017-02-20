package com.codinggyd.exception;

/**
 * 
 * @Title:  ServiceException
 * @Package: com.codinggyd.exception
 * @Description: 
 *
 * @author: guoyd
 * @Date: 2017年2月20日下午3:39:01
 *
 * Copyright @ 2017 Corpration Name
 */
public class ServiceException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5424245588822061060L;
	/**错误代码*/
	private String code;
	
	public ServiceException(String msg){
		super(msg);
	}
	public ServiceException(String msg,String code){
		super(msg);
		this.code = code;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
}
