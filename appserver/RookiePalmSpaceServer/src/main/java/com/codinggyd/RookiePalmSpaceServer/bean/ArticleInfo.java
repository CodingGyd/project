package com.codinggyd.RookiePalmSpaceServer.bean;

import java.io.Serializable;

/**
 * Created by guoyading on 2015-12-21.
 */
public class ArticleInfo implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
     * 文章编号
     */
    public Integer id;
    /**
     * 用户编号
     */
    public Integer userId;
    /**
     * 文章标题
     * 1.技术
     * 2.生活
     */
    public String title;
    /**
     * 发表地址
     */
    public String location;
    /**
     * 文章类型
     */
    public String type;
    /**
     * 文章内容
     */
    public String content;
    /**
     * 发表时间
     */
    public String time;

    /**
     * 下载链接
     */
    public String url;
    
    public ArticleInfo(Integer id, Integer userId, String title, String location, String type, String content,
			String time, String url) {
		super();
		this.id = id;
		this.userId = userId;
		this.title = title;
		this.location = location;
		this.type = type;
		this.content = content;
		this.time = time;
		this.url = url;
	}

	public ArticleInfo() {
    }

	@Override
	public String toString() {
		return "ArticleInfo [id=" + id + ", userId=" + userId + ", title=" + title + ", location=" + location
				+ ", type=" + type + ", content=" + content + ", time=" + time + ", url=" + url + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
 
}
