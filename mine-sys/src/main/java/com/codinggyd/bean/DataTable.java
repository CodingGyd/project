package com.codinggyd.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * 
 * @Title:  DataTable.java
 * @Package: com.codinggyd.bean
 * @Description: 列表表格对象
 *
 * @author: guoyd
 * @Date: 2017年10月29日 下午3:22:01
 *
 * Copyright @ 2017 Corpration Name
 */
public class DataTable<T> implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -537655470838582074L;

	private Integer total;
	
	private List<T> rows;

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}
}
