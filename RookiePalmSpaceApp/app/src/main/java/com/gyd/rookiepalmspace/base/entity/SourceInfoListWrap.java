package com.gyd.rookiepalmspace.base.entity;

import java.util.List;

public class SourceInfoListWrap {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String status ;
	public List<SourceInfo> data;
	@Override
	public String toString() {
		return "SourceInfoListWrap [status=" + status + ", data=" + data + "]";
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<SourceInfo> getData() {
		return data;
	}
	public void setData(List<SourceInfo> data) {
		this.data = data;
	}
	
}
