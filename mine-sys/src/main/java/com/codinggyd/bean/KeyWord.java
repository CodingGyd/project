package com.codinggyd.bean;

import java.io.Serializable;

/**
 * 
 * @Title:  KeyWord.java
 * @Package: com.codinggyd.bean
 * @Description: 关键词
 *
 * @author: guoyd
 * @Date: 2017年11月5日 上午11:23:08
 *
 * Copyright @ 2017 Corpration Name
 */
public class KeyWord implements Serializable {
 
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8434294788857218961L;
	private Integer id;
	private String name;
	private String updatetime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}
	
}
