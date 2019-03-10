 package com.codinggyd.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.codinggyd.annotation.MineService;
import com.codinggyd.bean.LoggerEntity;
import com.codinggyd.mapper.LoggerMapper;
import com.codinggyd.service.ILoggerService;

/**
 * 
 * 
 * @Title:  LoggerServiceImpl.java
 * @Package: com.codinggyd.service.impl
 * @Description: 
 *
 * @author: guoyd
 * @Date: 2019年3月10日 下午1:09:54
 *
 * Copyright @ 2019 Corpration Name
 */
@Service
@MineService
public class LoggerServiceImpl implements ILoggerService{

	@Autowired
	private LoggerMapper mapper;
	
	private static final Integer BATCH_SIZE = 300;
	@Override
	@Transactional(value="mineTransactionManager",propagation=Propagation.REQUIRED,readOnly=false)
	public String saveLoggerInfo(List<LoggerEntity> datas) {
		int sta = 0;
		int end = 0;
		int size = datas.size();
		while (size > (sta = end)) {
			end = sta + BATCH_SIZE;
			if (end > size ) {
				end = size;
			}
			
			mapper.addLogger(datas.subList(sta, end));
		}
		
		return "SUCCESS";
	}
  
 
 }
