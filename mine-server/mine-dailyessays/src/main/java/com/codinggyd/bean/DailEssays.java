package com.codinggyd.bean;

import java.io.Serializable;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.codinggyd.annotation.MineFuncData;

/**
 * 
 * @Title:  DailEssays.java
 * @Package: com.codinggyd.bean
 * @Description: 随笔
 *
 * @author: guoyd
 * @Date: 2017年10月15日 下午8:13:26
 *
 * Copyright @ 2017 Corpration Name
 */
@Component
@MineFuncData(name="MINE_DAILY_ESSAYS",fieldNames={"content","updatetime"})
public class DailEssays implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8736749011130769481L;
	 
	private Integer id;
	
	/**
	 * 随笔内容
	 */
	public String content;

	/**
	 * 更新时间
	 */
	private Date updatetime;

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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
}
