package com.codinggyd.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codinggyd.annotation.MineService;
import com.codinggyd.bean.UserInfo;
import com.codinggyd.mapper.UserInfoMapper;
import com.codinggyd.service.IUserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * 
 * @Title:  UserServiceImpl.java
 * @Package: com.codinggyd.service.impl
 * @Description: 用户登录
 *
 * @author: guoyd
 * @Date: 2017年1月15日上午12:24:53
 *
 * Copyright @ 2017 Corpration Name
 */
@Service
@MineService
public class UserServiceImpl implements IUserService{

	final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private UserInfoMapper mapper;
	
	@Override
	public String login(String phone, String password) {
		UserInfo userinfo = mapper.findUser(phone, password);
		if(null == userinfo){
			logger.debug("用户名{},密码{},验证登录失败",phone,password);
			return null;
		}
		
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			String json = objectMapper.writeValueAsString(userinfo);
			logger.debug("用户登录信息{}",json);
			return json;
		} catch (JsonProcessingException e) {
			logger.error("登录失败,",e.getMessage());
		}
		return null;
	}

}
