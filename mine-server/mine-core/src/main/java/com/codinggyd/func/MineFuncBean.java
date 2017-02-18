package com.codinggyd.func;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @Title:  MineFuncBean
 * @Package: com.codinggyd.func
 * @Description: service执行结果集对象
 *
 * @author: guoyd
 * @Date: 2017年2月18日下午7:27:38
 *
 * Copyright @ 2017 Corpration Name
 */
public class MineFuncBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3241043193111761150L;
	/**
	 * 所属业务方法标识
	 */
	private String fundId;
	/**
	 * 字段集合
	 */
	private List<MineField> fields;
	/**
	 * 字段方法集合
	 */
	private List<Method> methods;

	{
		fields = new ArrayList<>();
		methods = new ArrayList<>();
	}
	
	public String getFundId() {
		return fundId;
	}
	public void setFundId(String fundId) {
		this.fundId = fundId;
	}
	
	public void addField(MineField field){
		fields.add(field);
	}
	
	public void addMethod(Method method){
		methods.add(method);
	}
	@Override
	public String toString() {
		return "MineFuncBean [fundId=" + fundId + ", fields=" + fields + ", methods=" + methods + "]";
	}
	
}
