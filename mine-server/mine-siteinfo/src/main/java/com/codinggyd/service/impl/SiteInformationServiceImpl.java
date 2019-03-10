 package com.codinggyd.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.codinggyd.annotation.MineService;
import com.codinggyd.bean.SiteInformation;
import com.codinggyd.mapper.SiteInformationMapper;
import com.codinggyd.service.ISiteInformationService;

/**
 * 
 * 
 * @Title:  SiteInformationServiceImpl.java
 * @Package: com.codinggyd.service.impl
 * @Description: 
 *
 * @author: guoyd
 * @Date: 2019年2月12日下午20:29:45
 *
 * Copyright @ 2017 Corpration Name
 */
@Service
@MineService
@Transactional(value="mineTransactionManager",propagation=Propagation.NOT_SUPPORTED,readOnly=true)
public class SiteInformationServiceImpl implements ISiteInformationService{

	final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private SiteInformationMapper mapper;
	@Override
	public SiteInformation getSiteInformation() {
		SiteInformation siteInformation = mapper.getSiteInformation();
		if (null == siteInformation) {
			logger.error("未维护站点信息");
			return null;
		}
		//文章数量
		Integer articlenums = mapper.getNumsOfArticles();
		//评论数据 功能暂未开发,置为0
		Integer commentnums = 0;
		
		siteInformation.setNumOfArticles(null != articlenums ? articlenums : 0);
		siteInformation.setNumOfComment(commentnums);
		
		return siteInformation;
	}
	     
 }
