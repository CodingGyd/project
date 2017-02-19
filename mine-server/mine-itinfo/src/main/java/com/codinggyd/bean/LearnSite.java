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
@MineFuncData(name="TEST_MINE_FUNC_DATA",fieldNames={"title","content","url"})
@Component
public class LearnSite implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8736749011130769481L;
	private Integer id;
	/**
	 * IT资讯标题
	 */
	public String title;
	/**
	 * IT资讯描述
	 */
	public String content;

	/**
	 * 更新时间
	 */
	private Date updatetime;
	/**
	 * 被阅读次数
	 */
	private Integer readingcount;
	/**
	 * IT资讯地址
	 */
	private String url;
	
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

	@Override
	public String toString() {
		return "LearnSite [id=" + id + ", title=" + title + ", content=" + content + ", updatetime=" + updatetime
				+ ", readingcount=" + readingcount + ", url=" + url + "]";
	}

}
