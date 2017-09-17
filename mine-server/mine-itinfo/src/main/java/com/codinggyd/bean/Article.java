package com.codinggyd.bean;

import java.io.Serializable;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.codinggyd.annotation.MineFuncData;

/**
 * 
 * 
 * @Title: LearnSite.java
 * @Package: com.codinggyd.bean
 * @Description: IT资讯对象实体类
 * 
 * @author: guoyd
 * @Date: 2017年2月18日下午2:26:40
 * 
 *        Copyright @ 2017 Corpration Name
 */
@MineFuncData(name="MINE_ARTICLE_LIST",fieldNames={"id","title","descs","updatetime","readingcount","url","type","htmlContent"})
@Component
public class Article implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8736749011130769481L;
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
	 * 格式化的文章内容
	 */
	public String htmlContent;

	/**
	 * 更新时间
	 */
	private Date updatetime;
	/**
	 * 被阅读次数
	 */
	private Integer readingcount;
	/**
	 * 地址
	 */
	private String url;
	
	/**
	 * 类型
	 */
	private String type;
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	 

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDescs() {
		return descs;
	}

	public void setDescs(String descs) {
		this.descs = descs;
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

	 

}
