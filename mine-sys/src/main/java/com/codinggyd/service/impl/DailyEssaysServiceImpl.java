package com.codinggyd.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.codinggyd.bean.DailEssays;
import com.codinggyd.mapper.DailyEssaysMapper;
import com.codinggyd.service.IDailyEssaysService;

/**
 * 
 * 
 * @Title:  DailyEssaysServiceImpl.java
 * @Package: com.codinggyd.service.impl
 * @Description: 随笔管理
 *
 * @author: guoyd
 * @Date: 2017年10月15日 下午8:12:21
 *
 * Copyright @ 2017 Corpration Name
 */
@Service
@Transactional(value="mineTransactionManager",propagation=Propagation.NOT_SUPPORTED,readOnly=true)
public class DailyEssaysServiceImpl implements IDailyEssaysService{

	final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private DailyEssaysMapper mapper;
	
	@Override
	public List<DailEssays> getDailyEssays() {
		return mapper.findDailyEssays();
	}

	@Override
	@Transactional(value="mineTransactionManager",propagation=Propagation.REQUIRED,readOnly=false)
	public void deleteDailyEssays(Integer id) {
		mapper.deleteDailyEssays(id);
	}

	@Override
	@Transactional(value="mineTransactionManager",propagation=Propagation.REQUIRED,readOnly=false)
	public void updateDailyEssays(DailEssays daily) {
		mapper.updateDailyEssays(daily);
	}

	@Override
	@Transactional(value="mineTransactionManager",propagation=Propagation.REQUIRED,readOnly=false)
	public void insertDailyEssays(DailEssays daily) {
		mapper.insertDailyEssays(daily);
	}

	@Override
	public DailEssays queryDailyEssays(Integer id) {
		return mapper.queryDailyEssays(id);
	}
}
