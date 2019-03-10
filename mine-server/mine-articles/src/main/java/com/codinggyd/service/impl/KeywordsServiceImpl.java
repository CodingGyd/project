 package com.codinggyd.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.codinggyd.annotation.MineService;
import com.codinggyd.bean.Keywords;
import com.codinggyd.mapper.KeywordsMapper;
import com.codinggyd.service.IKeywordsService;

/**
 * 
 * 
 * @Title:  KeywordsServiceImpl.java
 * @Package: com.codinggyd.service.impl
 * @Description: 
 *
 * @author: guoyd
 * @Date: 2019年2月13日下午2:29:45
 *
 * Copyright @ 2019 Corpration Name
 */
@Service
@MineService
@Transactional(value="mineTransactionManager",propagation=Propagation.NOT_SUPPORTED,readOnly=true)
public class KeywordsServiceImpl implements IKeywordsService{

	final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private KeywordsMapper mapper;
	@Override
	public List<Keywords> listKeywords() {
		return mapper.findAllKeywords();
	}
 	 
}
