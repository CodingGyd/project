package com.codinggyd.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codinggyd.annotation.MineService;
import com.codinggyd.bean.MinePageList;
import com.codinggyd.bean.UtilFunction;
import com.codinggyd.mapper.UtilFunctionMapper;
import com.codinggyd.service.IUtilFunctionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

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
	public String getUtilFunction() {
		int page = 1; //页号
		int pageSize = 10; //每页数据条数
		String sortString = "id.desc";//如果你想排序的话逗号分隔可以排序多列
		PageBounds pageBounds = new PageBounds(page, pageSize , Order.formString(sortString));
		pageBounds.setContainsTotalCount(true);
		List<UtilFunction> userinfo = mapper.findUtilFunctions(pageBounds);
		//获得结果集条总数
		PageList pageList = (PageList)userinfo;
		logger.debug("totalCount: " + pageList.getPaginator().getTotalCount());
		logger.debug("totalCount: " + pageList.toString());
		MinePageList data = new MinePageList();
		data.setData(userinfo);
		data.setLimit(pageList.getPaginator().getLimit());
		data.setPage(pageList.getPaginator().getPage());
		data.setTotalCount(pageList.getPaginator().getTotalCount());
		
		if(null == userinfo){
			logger.debug("暂未收录任何功能函数");
			return null;
		}
		
		ObjectMapper objectMapper = new ObjectMapper();
		try {
		 
			String json = objectMapper.writeValueAsString(userinfo);
			return json;
		} catch (Exception e) {
			logger.error("查询功能函数信息失败",e.getMessage());
		}
		return null;
	}

}
