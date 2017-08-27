package com.codinggyd.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codinggyd.annotation.MineService;
import com.codinggyd.bean.UtilFunction;
import com.codinggyd.mapper.UtilFunctionMapper;
import com.codinggyd.service.IUtilFunctionService;
import com.github.miemiedev.mybatis.paginator.domain.Order;
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
	public List<UtilFunction> getUtilFunction() {
		int page = 1; //页号
		int pageSize = 100; //每页数据条数
		String sortString = "id.desc";//如果你想排序的话逗号分隔可以排序多列
		PageBounds pageBounds = new PageBounds(page, pageSize , Order.formString(sortString));
		pageBounds.setContainsTotalCount(true);
		List<UtilFunction> userinfo = mapper.findUtilFunctions(pageBounds);
		
		if(null == userinfo){
			logger.debug("暂未收录任何功能函数");
		}
		return userinfo;
	 
	}

}
