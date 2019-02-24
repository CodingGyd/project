package com.codinggyd.bean;

import java.util.Date;

/**
 * 
 * 
 * @Title: SysConst.java
 * @Package: com.codinggyd.bean
 * @Description: 常量表实体类 
 * 
 * @Author: guoyd
 * @Date: 2019年2月23日 下午10:49:04
 *
 * Copyright @ 2019 Corpration Name
 */
public class SysConst {
	private Integer id;
	/**
	 * 常量类别代码
	 */
	private String lb;
	/**
	 * 常量类别名称
	 */
	private String lbmc;
	
	/**
	 * 常量代码
	 */
	private String dm;
	/**
	 * 常量名称
	 */
	private String ms;

	/**
	 * 更新时间
	 */
	private Date updatetime;
	private String updatetimestr;
	
	/**
	 * 备注
	 */
	public String remarks;
	
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getRemarks() {
		return remarks;
	}
	
	
	public void setUpdatetimestr(String updatetimestr) {
		this.updatetimestr = updatetimestr;
	}
	public String getUpdatetimestr() {
		return updatetimestr;
	}
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
