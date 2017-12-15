package com.codinggyd.excel.core.exportexcel.bean;

import java.util.List;

/**
 * 
 * <pre>
 * 类名:  SheetData.java
 * 包名:  com.codinggyd.excel.core.exportexcel.bean
 * 描述:  生成一个sheet需要的数据封装实体
 * 
 * 作者:  guoyd
 * 日期:  2017年12月15日
 *
 * Copyright @ 2017 Corpration Name
 * </pre>
 */
public class SheetData<E> {
	
	/**
	 * sheet数据行描述对象
	 */
	private Class<E> clazz;
	/**
	 * sheet数据
	 */
	private List<E> data;
	/**
	 * sheet文件格式
	 */
	private String format;
	public Class<E> getClazz() {
		return clazz;
	}
	public void setClazz(Class<E> clazz) {
		this.clazz = clazz;
	}
	public List<E> getData() {
		return data;
	}
	public void setData(List<E> data) {
		this.data = data;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public SheetData(Class<E> clazz, List<E> data, String format) {
		super();
		this.clazz = clazz;
		this.data = data;
		this.format = format;
	}
	
	
}
