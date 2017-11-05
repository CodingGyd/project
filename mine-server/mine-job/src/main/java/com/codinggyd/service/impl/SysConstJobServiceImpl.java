package com.codinggyd.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.codinggyd.annotation.MineService;
import com.codinggyd.bean.SysConst2;
import com.codinggyd.mapper.SysConstMapper2;
import com.codinggyd.service.ISysConstJobService;

/**
 * 
 * 
 * @Title:  SysConstServiceImpl.java
 * @Package: com.codinggyd.service.impl
 * @Description: 常量查询
 *
 * @author: guoyd
 * @Date: 2017年9月23日 上午1:24:46
 *
 * Copyright @ 2017 Corpration Name
 */
@Service
@MineService
@Transactional(value="mineTransactionManager",propagation=Propagation.NOT_SUPPORTED,readOnly=true)
public class SysConstJobServiceImpl implements ISysConstJobService{

	@Autowired
	private SysConstMapper2 mapper;
	@Override
	public List<SysConst2> getAllConst() {
		return mapper.listConst(null);
	}
 
	 
}
