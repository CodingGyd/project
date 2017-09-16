package com.codinggyd.bean.requ;

import java.io.Serializable;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * 
 * 
 * @Title:  MineRequestBean.java
 * @Package: com.codinggyd.bean.requ
 * @Description: 请求实体对象
 *
 * @author: guoyd
 * @Date: 2017年9月15日 下午10:08:32
 *
 * Copyright @ 2017 Corpration Name
 */
public class MineRequestBean implements Serializable{
 
	/**
	 * 
	 */
	private static final long serialVersionUID = -4571449604373950509L;

	private String serviceId;//接口标识
	
	private JsonNode[] params;;//接口参数集合
	

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public JsonNode[] getParams() {
		return params;
	}

	public void setParams(JsonNode[] params) {
		this.params = params;
	}

}

