package com.codinggyd.excel.exception;
 
/**
 * <pre>
 * 类名:  ExcelException.java
 * 包名:  com.codinggyd.excel.exception
 * 描述:  自定义excel解析异常对象
 * 
 * 作者:  guoyd
 * 日期:  2017年11月26日
 *
 * Copyright @ 2017 Corpration Name
 * </pre>
 */
public class ExcelException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4707341539426233362L;
	
	public ExcelException(){
		super();
	}
	
	public ExcelException(String msg) {
		super(msg);
	}

}
