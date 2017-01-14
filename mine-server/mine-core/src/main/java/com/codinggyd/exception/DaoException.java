package com.codinggyd.exception;
/**
 * @Title DaoException.java
 * @Package: com.gildata.app.core.base.exception
 * @Description: TODO (这里用一句话描述这个类的作用) 
 *
 * @Author: huangwk   
 * @Date: 2015年8月11日 上午11:03:08
 *
 * Copyright @ 2015 Corpration Name
 * 
 */
public class DaoException extends Exception {
	/** 
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */
	private static final long serialVersionUID = 5386054980944130960L;
	private String errorCode;

	/**
	 * 
	 * @param errorCode
	 * @param message
	 */
	public DaoException(String errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}

	/**
	 * 
	 * @return
	 * @Author huangwk
	 * @Date 2015年8月11日 上午8:51:06
	 */
	public String getErrorCode() {
		return this.errorCode;
	}
}
