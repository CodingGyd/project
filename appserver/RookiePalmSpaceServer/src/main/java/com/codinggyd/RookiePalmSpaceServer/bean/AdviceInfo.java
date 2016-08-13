package com.codinggyd.RookiePalmSpaceServer.bean;

import java.io.Serializable;

public class AdviceInfo  implements Serializable {
	public Integer id;
	public String content;
	public String time;
	public int userId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "AdviceInfo [id=" + id + ", content=" + content + ", time="
				+ time + ", userId=" + userId + "]";
	}
	public AdviceInfo(Integer id, String content, String time, int userId) {
		super();
		this.id = id;
		this.content = content;
		this.time = time;
		this.userId = userId;
	}
	public AdviceInfo() {
		super();
	}
	
	
}
