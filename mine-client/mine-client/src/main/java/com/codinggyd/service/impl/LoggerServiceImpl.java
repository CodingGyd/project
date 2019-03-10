 package com.codinggyd.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.codinggyd.bean.LoggerEntity;
import com.codinggyd.service.ILoggerService;
import com.codinggyd.util.HttpClientUtil;
import com.codinggyd.util.SysConstant;
import com.codinggyd.utils.CommonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
public class LoggerServiceImpl implements ILoggerService{
	public static final String SERVER_SAVE_LOGGER = "MINE_SAVE_LOGGER";
	final Logger logger = LoggerFactory.getLogger(getClass());
	private static ObjectMapper mapper = CommonUtils.getMappingInstance();

	@Override
	public void saveLoggerInfo(List<LoggerEntity> datas) {
		
		try {
			String s = mapper.writeValueAsString(datas);
			System.out.println(s);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Object> params = new ArrayList<>();
		params.add(datas);
		Map<String,Object> dataMap = new HashMap<>();
		dataMap.put("serviceId", SERVER_SAVE_LOGGER);
		dataMap.put("params", params);
		
		String requestJson = null;
		try {
			requestJson = mapper.writeValueAsString(dataMap);
		} catch (JsonProcessingException e1) {
			logger.error("格式化请求参数出错,");
		}
		logger.debug("提交访问日志[{}]条...",datas.size());
		HttpClientUtil.sendPost(SysConstant.SERVER_URL, requestJson);
 	 
	}
	 
 }
