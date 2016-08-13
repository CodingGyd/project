package com.codinggyd.RookiePalmSpaceServer.bean;

import java.io.Serializable;
import java.util.List;

public class ArticleInfoListWrap implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String status ;
	public List<ArticleInfo> data;
	@Override
	public String toString() {
		return "ArticleInfoListWrap [status=" + status + ", data=" + data + "]";
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<ArticleInfo> getData() {
		return data;
	}
	public void setData(List<ArticleInfo> data) {
		this.data = data;
	}
	
}
