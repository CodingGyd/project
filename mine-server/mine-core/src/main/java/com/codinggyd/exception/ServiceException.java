package com.codinggyd.exception;

public class ServiceException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5072698805993399920L;

	private String errorCode;
	/**
	 * 
	 * @param errorCode
	 * @param message
	 */
	public ServiceException(String message) {
		super(message);
	}
	/**
	 * 
	 * @param errorCode
	 * @param message
	 */
	public ServiceException(String errorCode, String message) {
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
