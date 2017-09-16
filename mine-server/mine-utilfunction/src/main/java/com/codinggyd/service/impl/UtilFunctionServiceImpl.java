package com.codinggyd.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codinggyd.annotation.MineService;
import com.codinggyd.bean.UtilFunction;
import com.codinggyd.mapper.UtilFunctionMapper;
import com.codinggyd.service.IUtilFunctionService;
import com.codinggyd.util.PageBoundUtils;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

/**
 * 
 * 
 * @Title:  UtilFunctionServiceImpl.java
 * @Package: com.codinggyd.service.impl
 * @Description: 
 *
 * @author: guoyd
 * @Date: 2017年1月21日下午7:07:45
 *
 * Copyright @ 2017 Corpration Name
 */
@Service
@MineService
public class UtilFunctionServiceImpl implements IUtilFunctionService{

	final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private UtilFunctionMapper mapper;
	
	@Override
	public List<UtilFunction> getUtilFunction(String[] pageInfo) {
		
		PageBounds pageBounds = null;
		if (null != pageInfo) {
			try {
				pageBounds = PageBoundUtils.getPageBounds(pageInfo);
			} catch (Exception e) {
				logger.error("解析分页参数出错,{},{}",pageInfo,e);
				return null;
			}
		}
		List<UtilFunction> userinfo = null;
		if (null != pageBounds) {
			userinfo = mapper.findUtilFunctions(pageBounds);
		} else {
			userinfo = mapper.findUtilFunctions();
		}
		 
		if(CollectionUtils.isEmpty(userinfo)){
			logger.error("当前条件下没有查到数据,分页参数{}",pageInfo);
		}
		return userinfo;
	 
	}
	
}
