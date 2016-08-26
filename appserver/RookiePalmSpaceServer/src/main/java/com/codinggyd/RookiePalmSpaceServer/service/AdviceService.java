package com.codinggyd.RookiePalmSpaceServer.service;

import java.io.IOException;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codinggyd.RookiePalmSpaceServer.bean.AdviceInfo;
import com.codinggyd.RookiePalmSpaceServer.bean.ResponseFlag;
import com.codinggyd.RookiePalmSpaceServer.mapper.AdviceInfoMapper;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @ClassName: AdviceService.java
 * @Description: 
 * @author guoyd
 * @date 2016年8月22日 下午4:09:16
 */
@Service
public class AdviceService {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	public AdviceInfoMapper mapper;
	
	public String insertSingle(String adviceInfoJson) throws JsonParseException, JsonMappingException, IOException{
		Object object = null;
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			AdviceInfo adviceinfo = objectMapper.readValue(adviceInfoJson, AdviceInfo.class);
			
			boolean isInserted = mapper.insertSingle(adviceinfo) >0?true:false;
  
			ResponseFlag responseFlag = new ResponseFlag();
			responseFlag.status = isInserted? "success":"failed";
			responseFlag.msg = isInserted? "谢谢您的反馈!":"反馈失败,请稍后再试!";
  
			object = JSONObject.wrap(responseFlag);
		} catch (Exception e) {
			logger.error(e.toString());
		}
		
		
		return object.toString();
	}
}
