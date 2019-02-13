package com.codinggyd.bean;

import java.io.Serializable;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.codinggyd.annotation.MineFuncData;

/**
 * 
 * 
 * @Title: Keywords.java
 * @Package: com.codinggyd.bean
 * @Description: 文章关键字
 * 
 * @author: guoyd
 * @Date: 2019年2月13日下午2:26:40
 * 
 *        Copyright @ 2019 Corpration Name
 */
@MineFuncData(name="MINE_KEYWORDS",fieldNames={"id","name","descs","updatetime"})
@Component
public class Keywords implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3526321790333861045L;
	/**
	 * 关键字ID
	 */
	private Integer id;
	/**
	 * 关键字名称
	 */
	private String name; 
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

}
