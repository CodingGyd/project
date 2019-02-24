package com.codinggyd.bean;

import java.io.Serializable;

/**
 * 
 * @Title:  ArticleKeyWordRelation.java
 * @Package: com.codinggyd.bean
 * @Description: 文章关键词关系
 *
 * @author: guoyd
 * @Date: 2017年11月5日 下午12:34:59
 *
 * Copyright @ 2017 Corpration Name
 */
public class ArticleKeyWordRelation implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2192272855376241743L;
	private Integer id;
	private Integer articleId;
	private Integer keyWordId;
	private String keyName;
	private String updatetime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getKeyWordId() {
		return keyWordId;
	}
	public void setKeyWordId(Integer keyWordId) {
		this.keyWordId = keyWordId;
	}
	public String getKeyName() {
		return keyName;
	}
	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}
	public String getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}
	public Integer getArticleId() {
		return articleId;
	}
	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}
	
}
