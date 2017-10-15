package com.codinggyd.bean;

import java.io.Serializable;

import org.springframework.stereotype.Component;

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
public class DailEssays implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8736749011130769481L;
	 
	/**
	 * 功能函数代码块
	 */
	public String content;

	/**
	 * 更新时间
	 */
	private String updatetime;

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
	
	
}
