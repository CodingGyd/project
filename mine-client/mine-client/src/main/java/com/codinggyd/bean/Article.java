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
	 * 描述
	 */
	public String descs;
	
	/**
	 * md格式的文章内容
	 */
	public String content;
	/**
	 * html格式的文章内容
	 */
	public String htmlContent;

	/**
	 * 更新时间
	 */
	private String updatetime;
	/**
	 * 被阅读次数
	 */
	private Integer readingcount;
	
	/**
	 * 类型代码
	 */
	private String type;
	/**
	 * 类型名称
	 */
	private String typeName;
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

 
	public String getDescs() {
		return descs;
	}

	public void setDescs(String descs) {
		this.descs = descs;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getHtmlContent() {
		return htmlContent;
	}

	public void setHtmlContent(String htmlContent) {
		this.htmlContent = htmlContent;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	 
}
