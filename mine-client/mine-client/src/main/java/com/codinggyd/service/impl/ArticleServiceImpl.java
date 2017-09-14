package com.codinggyd.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.codinggyd.service.IArticleService;
import com.codinggyd.util.HttpClientUtil;
import com.codinggyd.util.SysConstant;
import com.fasterxml.jackson.databind.JsonNode;
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
public class ArticleServiceImpl implements IArticleService{

	final Logger logger = LoggerFactory.getLogger(getClass());
 
	
	@Override
	public String getArticleList(Integer curPage) {
		String requestJson = "{\"ServiceId\":\"MINE_UTIL_FUNCTION\",\"Params\":[]}";
		String responseData = HttpClientUtil.sendPost2(SysConstant.SERVER_URL, requestJson);
 	 
		String result = null;
		if (StringUtils.isEmpty(responseData)) {
			logger.error("接口MINE_UTIL_FUNCTION返回数据为空");
 		} else {
 			ObjectMapper mapper = new ObjectMapper();
 			try {
				JsonNode node = mapper.readTree(responseData);
				String code = node.get("code").asText();
				if (SysConstant.RESPONSE_CODE_SUCCESS.equals(code)) {
					result = node.get("data").toString();
					
					if (StringUtils.isEmpty(result)) {
						logger.error("接口MINE_UTIL_FUNCTION返回数据为空");
					}
					
				} else {
					logger.error("接口MINE_UTIL_FUNCTION错误,响应码{}",code);
				}
			} catch (Exception e) {
				logger.error("接口MINE_UTIL_FUNCTION返回数据有误,{},{}",responseData,e);
			}
 			
 		}
		
		return result;
	}
	
 

}
