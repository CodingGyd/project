package com.codinggyd.bean;

import java.io.Serializable;
import java.util.Date;

public class LoggerEntity implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = -2525765225281090658L;
	/**
	 * 记录ID
	 */
	private Integer id;
	/**
	 * 用户IP
	 */
	private String ip;
	/**
	 * 访问URL
	 */
	private String url;
	/**
	 * 访问类型
	 */
	private String type;
	/**
	 * 请求方法
	 */
	private String method;
	/**
	 * 请求参数
	 */
	private String paramData;
	/**
	 * 会话ID
	 */
	private String sessionId;
	/**
	 * 访问时间
	 */
	private Date time;
	/**
	 * 响应时间
	 */
	private String returnTime;
	/**
	 * 响应数据
	 */
	private String returnData;
	/**
	 * HTTP响应码
	 */
	private String httpStatusCode;
	/**
	 * 请求响应执行耗时
	 */
	private Integer timeConsuming;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getParamData() {
		return paramData;
	}

	public void setParamData(String paramData) {
		this.paramData = paramData;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getReturnTime() {
		return returnTime;
	}

	public void setReturnTime(String returnTime) {
		this.returnTime = returnTime;
	}

	public String getReturnData() {
		return returnData;
	}

	public void setReturnData(String returnData) {
		this.returnData = returnData;
	}

	public String getHttpStatusCode() {
		return httpStatusCode;
	}

	public void setHttpStatusCode(String httpStatusCode) {
		this.httpStatusCode = httpStatusCode;
	}

	public Integer getTimeConsuming() {
		return timeConsuming;
	}

	public void setTimeConsuming(Integer timeConsuming) {
		this.timeConsuming = timeConsuming;
	}

	@Override
	public String toString() {
		return "LoggerEntity [id=" + id + ", ip=" + ip + ", url=" + url + ", type=" + type + ", method=" + method
				+ ", paramData=" + paramData + ", sessionId=" + sessionId + ", time=" + time + ", returnTime="
				+ returnTime + ", returnData=" + returnData + ", httpStatusCode=" + httpStatusCode + ", timeConsuming="
				+ timeConsuming + "]";
	}

}
