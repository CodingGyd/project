package com.codinggyd.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codinggyd.annotation.MineService;
import com.codinggyd.bean.LearnSite;
import com.codinggyd.mapper.LearnSiteMapper;
import com.codinggyd.service.ILearnSiteService;

/**
 * 
 * 
 * @Title:  LearnSiteServiceImpl.java
 * @Package: com.codinggyd.service.impl
 * @Description: 
 *
 * @author: guoyd
 * @Date: 2017年2月18日下午2:29:45
 *
 * Copyright @ 2017 Corpration Name
 */
@Service
@MineService
public class LearnSiteServiceImpl implements ILearnSiteService{

	final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private LearnSiteMapper mapper;
	
	@Override
	public List<LearnSite> listLearnSite() {
		List<LearnSite> learnSiteList = mapper.findLearnSite();
		
		if(CollectionUtils.isEmpty(learnSiteList)){
			logger.debug("暂未收录任何IT资讯网站");
			return null;
		}
		
		return learnSiteList;
	}

}
