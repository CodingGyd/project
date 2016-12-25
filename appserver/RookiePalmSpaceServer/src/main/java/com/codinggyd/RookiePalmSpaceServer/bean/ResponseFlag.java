package com.codinggyd.RookiePalmSpaceServer.bean;

import java.io.Serializable;

public class ResponseFlag implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String status ;
	public String msg ;
	@Override
	public String toString() {
		return "ResponseFlag [status=" + status + ", msg=" + msg + "]";
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	 
}
