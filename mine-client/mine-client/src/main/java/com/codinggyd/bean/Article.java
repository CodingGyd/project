package com.codinggyd.bean;

import java.io.Serializable;

/**
 * 
 * @Title: Article.java
 * @Package: com.codinggyd.bean
 * @Description: 文章信息实体类
 * 
 * @Author: guoyd
 * @Date: 2017年9月11日
 *
 * Copyright @ 2017 Corpration Name
 */
public class Article implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4785066758102282805L;
	private Integer id;
	/**
	 * 标题
	 */
	public String title;
	/**
	 * 内容
	 */
	public String content;

	/**
	 * 更新时间
	 */
	private String updatetime;
	/**
	 * 被阅读次数
	 */
	private Integer readingcount;
	 
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}

	public Integer getReadingcount() {
		return readingcount;
	}

	public void setReadingcount(Integer readingcount) {
		this.readingcount = readingcount;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	 

	 
}
