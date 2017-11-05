package com.codinggyd.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.codinggyd.annotation.MineService;
import com.codinggyd.bean.DailEssays;
import com.codinggyd.mapper.DailyEssaysMapper;
import com.codinggyd.service.IDailyEssaysService;
import com.codinggyd.util.PageBoundUtils;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

/**
 * 
 * 
 * @Title:  DailyEssaysServiceImpl.java
 * @Package: com.codinggyd.service.impl
 * @Description: 随笔
 *
 * @author: guoyd
 * @Date: 2017年10月15日 下午8:12:21
 *
 * Copyright @ 2017 Corpration Name
 */
@Service
@MineService
@Transactional(value="mineTransactionManager",propagation=Propagation.NOT_SUPPORTED,readOnly=true)
public class DailyEssaysServiceImpl implements IDailyEssaysService{

	final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private DailyEssaysMapper mapper;
	
	@Override
	public List<DailEssays> getDailyEssays(String[] pageInfo) {
		
		PageBounds pageBounds = null;
		if (null != pageInfo) {
			try {
				pageBounds = PageBoundUtils.getPageBounds(pageInfo);
			} catch (Exception e) {
				logger.error("解析分页参数出错,{},{}",pageInfo,e);
				return null;
			}
		}
		List<DailEssays> result = null;
		if (null != pageBounds) {
			result = mapper.findDailyEssays(pageBounds);
		} else {
			result = mapper.findDailyEssays();
		}
		 
		if(CollectionUtils.isEmpty(result)){
			logger.error("当前条件下没有查到数据,分页参数{}",pageInfo != null? pageInfo.toString() :"");
		}
		return result;
	 
	}
	
}
