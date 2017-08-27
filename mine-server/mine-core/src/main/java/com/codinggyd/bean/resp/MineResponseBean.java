package com.codinggyd.bean.resp;

import java.io.Serializable;

/**
 * 
 * @Title:  MineResponseBean
 * @Package: com.codinggyd.bean.resp
 * @Description:  响应信息实体类
 *
 * @author: guoyd
 * @Date: 2017年2月20日上午10:33:26
 *
 * Copyright @ 2017 Corpration Name
 */
public class MineResponseBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -229538218516416628L;
	/**
	 * 响应码
	 */
	private String code;
	/**
	 * 数据
	 */
	private Object data;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
}
