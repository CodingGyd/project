package com.codinggyd.exception;

public abstract class BaseException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6217999490423196523L;
	private String errorCode;

	/**
	 * 
	 * @param errorCode
	 * @param message
	 */
	public BaseException(String errorCode, String message) {
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
