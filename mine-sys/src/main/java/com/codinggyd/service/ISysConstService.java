package com.codinggyd.service;

import java.util.List;

import com.codinggyd.bean.SysConst;

/**
 * 
 * 
 * @Title: ISysConstService.java
 * @Package: com.codinggyd.service
 * @Description: 常量表操作接口 
 * 
 * @Author: guoyd
 * @Date: 2019年2月23日 下午10:53:06
 *
 * Copyright @ 2019 Corpration Name
 */
public interface ISysConstService {
	
	/**
	 * 查询所有常量
	 * @return
	 */
	public List<SysConst> queryConst();
	/**
	 * 删除常量
	 * @param id
	 */
	public void deleteConst(Integer id);
	/**
	 * 更新常量
	 * @param data
	 */
	public void updateConst(SysConst data);
	/**
	 * 新增常量
	 * @param data
	 */
	public void insertConst(SysConst data);
	
}
