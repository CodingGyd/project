package com.codinggyd.bean;

import java.io.Serializable;

/**
 * 
 * @Title:  MineField
 * @Package: com.codinggyd.func
 * @Description: 字段信息类
 *
 * @author: guoyd
 * @Date: 2017年2月18日下午8:18:29
 *
 * Copyright @ 2017 Corpration Name
 */
public class MineFieldBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 38088784561520929L;

	/**
	 * 字段类型
	 */
	private Integer type;
	/**
	 * 字段名称
	 */
	private String name;
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "MineField [type=" + type + ", name=" + name + "]";
	}
	
}
