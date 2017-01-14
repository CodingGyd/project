package com.codinggyd.bean;

import java.io.Serializable;
/**
 * 
 * 
 * @Title:  UserInfo.java
 * @Package: com.codinggyd.bean
 * @Description: 
 *
 * @author: guoyd
 * @Date: 2017年1月13日下午11:38:26
 *
 * Copyright @ 2017 Corpration Name
 */
public class UserInfo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
 
	/**
	 * 用户登陆密码
	 */
    public String password;
	/**
	 * 电话号码
	 */
    public String phone;
	 
    public Integer id;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
    
}
