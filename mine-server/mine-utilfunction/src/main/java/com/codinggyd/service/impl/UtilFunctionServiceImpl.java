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
import com.fasterxml.jackson.databind.ObjectMapper;

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
		List<UtilFunction> userinfo = mapper.findUtilFunctions();
		
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
