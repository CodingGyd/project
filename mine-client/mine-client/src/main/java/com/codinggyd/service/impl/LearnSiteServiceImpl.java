package com.codinggyd.service.impl;

 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

 
import com.codinggyd.service.ILearnSiteService;
import com.codinggyd.util.HttpClientUtil;
import com.codinggyd.util.SysConstant;
 
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * 
 * @Title:  LearnSiteServiceImpl.java
 * @Package: com.codinggyd.service.impl
 * @Description: 
 *
 * @author: guoyd
 * @Date: 2017年2月18日下午2:29:45
 *
 * Copyright @ 2017 Corpration Name
 */
@Service
public class LearnSiteServiceImpl implements ILearnSiteService{

	final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public String listLearnSite() {
		String requestJson = "{\"ServiceId\":\"MINE_LIST_LEARN_SITE\",\"Params\":[]}";
		String responseData = HttpClientUtil.sendPost2(SysConstant.SERVER_URL, requestJson);
		
	 
		String result = null;
		if (StringUtils.isEmpty(responseData)) {
			logger.error("接口MINE_LIST_LEARN_SITE返回数据为空");
 		} else {
 			ObjectMapper mapper = new ObjectMapper();
 			try {
				JsonNode node = mapper.readTree(responseData);
				String code = node.get("code").asText();
				if (SysConstant.RESPONSE_CODE_SUCCESS.equals(code)) {
					result = node.get("data").toString();
					if (StringUtils.isEmpty(result)) {
						logger.error("接口MINE_LIST_LEARN_SITE返回数据为空");
					}
					
				} else {
					logger.error("接口MINE_LIST_LEARN_SITE错误,响应码{}",code);
				}
			} catch (Exception e) {
				logger.error("接口MINE_LIST_LEARN_SITE返回数据有误,{},{}",responseData,e);
			}
 			
 		}
		
		return result;
		
	}

}
