package com.codinggyd.bean;

import java.io.Serializable;
import java.util.Date;

import org.springframework.stereotype.Component;

/**
 * 
 * 
 * @Title:  SysConst.java
 * @Package: com.codinggyd.bean
 * @Description: 系统常量
 *
 * @author: guoyd
 * @Date: 2017年9月23日 上午1:19:52
 *
 * Copyright @ 2017 Corpration Name
 */
@Component
public class SysConst2 implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8736749011130769481L;
	private Integer id;
	/**
	 * 常量类别代码
	 */
	public String lb;
	/**
	 * 常量类别名称
	 */
	public String lbmc;
	
	/**
	 * 常量代码
	 */
	public String dm;
	/**
	 * 常量名称
	 */
	public String ms;

	/**
	 * 更新时间
	 */
	private Date updatetime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLb() {
		return lb;
	}

	public void setLb(String lb) {
		this.lb = lb;
	}

	public String getLbmc() {
		return lbmc;
	}

	public void setLbmc(String lbmc) {
		this.lbmc = lbmc;
	}

	public String getDm() {
		return dm;
	}

	public void setDm(String dm) {
		this.dm = dm;
	}

	public String getMs() {
		return ms;
	}

	public void setMs(String ms) {
		this.ms = ms;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
  

}
