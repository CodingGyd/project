package com.codinggyd.bean;

import java.util.List;

/**
 * 
 * 
 * @Title: MinePageBean.java
 * @Package: com.codinggyd.bean
 * @Description: 列表分页对象
 * 
 * @Author: guoyd
 * @Date: 2019年2月21日 下午5:42:48
 *
 * Copyright @ 2019 Corpration Name
 */
public class MinePageBean<E> {
   
    
    private Paginator paginator;
    
    private List<E> data;
    
    public MinePageBean() {}

    
	public MinePageBean(Paginator paginator, List<E> data) {
		super();
		this.paginator = paginator;
		this.data = data;
	}


	public Paginator getPaginator() {
		return paginator;
	}

	public void setPaginator(Paginator paginator) {
		this.paginator = paginator;
	}

	public List<E> getData() {
		return data;
	}

	public void setData(List<E> data) {
		this.data = data;
	}
    
}
