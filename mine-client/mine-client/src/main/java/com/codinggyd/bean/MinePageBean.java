package com.codinggyd.bean;

import java.util.List;

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
