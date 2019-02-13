 package com.codinggyd.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.codinggyd.bean.Keywords;
import com.codinggyd.service.IKeywordsService;
import com.codinggyd.util.HttpClientUtil;
import com.codinggyd.util.SysConstant;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

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
public class KeywordsServiceImpl implements IKeywordsService{

	final Logger logger = LoggerFactory.getLogger(getClass());
	private static final String SERVER_KEYWORD_LIST="MINE_KEYWORDS";//数据接口地址-关键字
	private static ObjectMapper mapper = new ObjectMapper();

	@Override
	public List<Keywords> listKeywords() {
		List<Keywords> result = new ArrayList<>();
		List<Object> params = new ArrayList<>();
		
		Map<String,Object> dataMap = new HashMap<>();
		dataMap.put("serviceId", SERVER_KEYWORD_LIST);
		dataMap.put("params", params);
		
		String requestJson = null;
		try {
			requestJson = mapper.writeValueAsString(dataMap);
		} catch (JsonProcessingException e1) {
			logger.error("格式化请求参数出错,");
			return null; 
		}

		String responseData = HttpClientUtil.sendPost(SysConstant.SERVER_URL, requestJson);
 	 
		if (StringUtils.isEmpty(responseData)) {
			logger.error("接口[{}]返回数据为空",SERVER_KEYWORD_LIST);
 		} else {
 			
 			try {
				JsonNode node = mapper.readTree(responseData);
				String code = node.get("code").asText();
				if (SysConstant.RESPONSE_CODE_SUCCESS.equals(code)) {
					JsonNode resultJson = node.get("data").get(0);
					
					if (null == resultJson) {
						logger.error("接口[{}]返回数据为空",SERVER_KEYWORD_LIST);
					} else {
						//解析json格式数据
						int size = resultJson.size();
						for (int i=0;i<size;i++) {
							JsonNode temp = resultJson.get(i);
							Integer id = temp.get("id").asInt(0);
							String name = temp.get("name").asText("");
							Keywords keys = new Keywords();
							keys.setId(id);
							keys.setName(name);
							result.add(keys);
						}
					}
					
				} else {
					logger.error("接口[{}]错误,响应码{}",SERVER_KEYWORD_LIST,code);
				}
			} catch (Exception e) {
				logger.error("接口[{}]返回数据有误,{},{}",SERVER_KEYWORD_LIST,responseData,e);
			}
 			
 		}
		
		return result;
	}
 	 
}
