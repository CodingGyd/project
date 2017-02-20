package com.codinggyd.bean;

import java.io.Serializable;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.codinggyd.annotation.MineFuncData;

/**
 * 
 * 
 * @Title: UtilFunction.java
 * @Package: com.codinggyd.bean
 * @Description: 功能函数对象实体类
 * 
 * @author: guoyd
 * @Date: 2017年1月21日下午7:04:40
 * 
 *        Copyright @ 2017 Corpration Name
 */
@Component
@MineFuncData(name="MINE_UTIL_FUNCTION",fieldNames={"title","content","updatetime","readingcount"})
public class UtilFunction implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8736749011130769481L;
	private Integer id;
	/**
	 * 功能函数描述
	 */
	public String title;
	/**
	 * 功能函数代码块
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

}
