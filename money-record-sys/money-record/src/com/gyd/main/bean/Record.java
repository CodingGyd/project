package com.gyd.main.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 订单子项类,多个订单子项组成一个订单
 * @author Administrator
 *
 */
public class Record  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Double price;  
	private String name;  
	private String remark;
	private Date spendtime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getSpendtime() {
		return spendtime;
	}
	public void setSpendtime(Date spendtime) {
		this.spendtime = spendtime;
	}
	 
}
