package com.codinggyd.bean;

import java.io.Serializable;
import java.lang.reflect.Method;

/**
 * 
 * @Title:  MineServiceBean
 * @Package: com.codinggyd.func
 * @Description: Service业务类对象
 *
 * @author: guoyd
 * @Date: 2017年2月18日下午7:18:22
 *
 * Copyright @ 2017 Corpration Name
 */
public class MineServiceBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7849867860279077516L;

	/**
	 * Service业务类唯一标识
	 */
	String serviceId;
	/**
	 * service业务类对象
	 */
	Object service;
	/**
	 * service执行方法对象
	 */
	Method method;

	public Object getService() {
		return service;
	}

	public void setService(Object service) {
		this.service = service;
	}

	public Method getMethod() {
		return method;
	}

	public void setMethod(Method method) {
		this.method = method;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

}
