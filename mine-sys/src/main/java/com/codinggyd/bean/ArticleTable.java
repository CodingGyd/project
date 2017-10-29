package com.codinggyd.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * 
 * @Title:  ArticleTable.java
 * @Package: com.codinggyd.bean
 * @Description: 文章列表表格对象
 *
 * @author: guoyd
 * @Date: 2017年10月29日 下午3:22:01
 *
 * Copyright @ 2017 Corpration Name
 */
public class ArticleTable implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -537655470838582074L;

	private Integer total;
	
	private List<Article> rows;

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public List<Article> getRows() {
		return rows;
	}

	public void setRows(List<Article> rows) {
		this.rows = rows;
	}
	
	
}
