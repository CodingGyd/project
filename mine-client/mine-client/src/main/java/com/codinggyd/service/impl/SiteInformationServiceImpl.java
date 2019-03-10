 package com.codinggyd.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.codinggyd.bean.SiteInformation;
import com.codinggyd.service.ISiteInformationService;
import com.codinggyd.util.HttpClientUtil;
import com.codinggyd.util.SysConstant;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

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
 * Copyright @ 2019 Corpration Name
 */
@Service
public class SiteInformationServiceImpl implements ISiteInformationService{
	public static final String SERVER_SITE_INFORMATION = "MINE_SITE_INFORMATION";
	final Logger logger = LoggerFactory.getLogger(getClass());
	private static ObjectMapper mapper = new ObjectMapper();

	@Override
	public SiteInformation getSiteInformation() {
		List<Object> params = new ArrayList<>();
		
		Map<String,Object> dataMap = new HashMap<>();
		dataMap.put("serviceId", SERVER_SITE_INFORMATION);
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
			logger.error("接口[{}]返回数据为空",SERVER_SITE_INFORMATION);
 		} else {
 			
 		
 			try {
				JsonNode node = mapper.readTree(responseData);
				String code = node.get("code").asText();
				if (SysConstant.RESPONSE_CODE_SUCCESS.equals(code)) {
					JsonNode resultJson = node.get("data").get(0);
					
					if (null == resultJson) {
						logger.error("接口[{}]返回数据为空",SERVER_SITE_INFORMATION);
					} else {
						//解析json格式数据
							Integer numOfArticles = resultJson.get("numOfArticles").asInt(0);
							Integer numOfComment = resultJson.get("numOfComment").asInt(0);

							String remarks = resultJson.get("remarks").asText("");
							String timeOfSiteCreate = resultJson.get("timeOfSiteCreate").asText("");

							SiteInformation siteInformation = new SiteInformation();
							siteInformation.setNumOfArticles(numOfArticles);
							siteInformation.setNumOfComment(numOfComment);
							siteInformation.setRemarks(remarks);
							siteInformation.setTimeOfSiteCreate(formatDateStr(timeOfSiteCreate));
							return siteInformation;
					}
					
				} else {
					logger.error("接口[{}]错误,响应码{}",SERVER_SITE_INFORMATION,code);
				}
			} catch (Exception e) {
				logger.error("接口[{}]返回数据有误,{},{}",SERVER_SITE_INFORMATION,responseData,e);
			}
 			
 		}
		return null;
	}
	private String formatDateStr(String time) {
		return !StringUtils.isEmpty(time) && time.length() >= 10 ? time.substring(0,10) : ""; 
	}

 }
