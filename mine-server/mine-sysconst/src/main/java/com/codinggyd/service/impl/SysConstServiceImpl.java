package com.codinggyd.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codinggyd.annotation.MineService;
import com.codinggyd.bean.SysConst;
import com.codinggyd.mapper.SysConstMapper;
import com.codinggyd.service.ISysConstService;

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
public class SysConstServiceImpl implements ISysConstService{

	final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private SysConstMapper mapper;
	@Override
	public List<SysConst> listConst(List<String> lbs) {
		return mapper.listConst(lbs);
	}
	 
}
