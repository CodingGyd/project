package com.codinggyd.excel.core.parsexcel.bean;

import java.util.ArrayList;
import java.util.Collection;

/**
 * <pre>
 * 类名:  ResultList.java
 * 包名:  com.codinggyd.excel.core.bean
 * 描述:  Excel解析结果集对象
 * 
 * 作者:  guoyd
 * 日期:  2017年11月26日
 *
 * Copyright @ 2017 Corpration Name
 * </pre>
 */
public class ResultList<E> extends ArrayList<E> {
     
  
	private static final long serialVersionUID = -1945185029512343354L;
	
	/**
	 * Excel导入校验报告
	 */
	private String msg;
	
	/**
	 * Excel 导入解析出错行数
	 */
	private Integer errors;
	
    public ResultList() {}
    
	public ResultList(Collection<? extends E> c) {
		super(c);
	}

	
	public ResultList(Collection<? extends E> c,String msg) {
        super(c);
        this.msg = msg;
    }

    public ResultList(String p) {
        this.msg = p;
    }

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Integer getErrors() {
		return errors;
	}

	public void setErrors(Integer errors) {
		this.errors = errors;
	}
	
}
