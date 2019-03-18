package com.codinggyd.bean;

/**
 * 
 * 
 * @Title:  SiteInformation.java
 * @Package: com.codinggyd.bean
 * @Description: 站点信息
 *
 * @author: guoyd
 * @Date: 2019年2月12日 下午20:08:24
 *
 * Copyright @ 2019 Corpration Name
 */
public class SiteInformation {
	/**
	 * 建站时间
	 */
	private String timeOfSiteCreate; 
	/**
	 * 文章数量
	 */
	private Integer numOfArticles;
	/**
	 * 评论数量
	 */
	private Integer numOfComment;
	/**
	 * 备注
	 */
	private String remarks;
	
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getRemarks() {
		return remarks;
	}
	
	public String getTimeOfSiteCreate() {
		return timeOfSiteCreate;
	}
	public void setTimeOfSiteCreate(String timeOfSiteCreate) {
		this.timeOfSiteCreate = timeOfSiteCreate;
	}
	public Integer getNumOfArticles() {
		return numOfArticles;
	}
	public void setNumOfArticles(Integer numOfArticles) {
		this.numOfArticles = numOfArticles;
	}
	public Integer getNumOfComment() {
		return numOfComment;
	}
	public void setNumOfComment(Integer numOfComment) {
		this.numOfComment = numOfComment;
	}
	
	
}
