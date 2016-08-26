package com.codinggyd.RookiePalmSpaceServer.service;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codinggyd.RookiePalmSpaceServer.bean.ResponseFlag;
import com.codinggyd.RookiePalmSpaceServer.bean.SourceInfo;
import com.codinggyd.RookiePalmSpaceServer.bean.SourceInfoListWrap;
import com.codinggyd.RookiePalmSpaceServer.mapper.SourceMapper;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author guoyading
 *
 * create at 2016年8月21日 下午8:22:00
 */
@Service
public class SourceService {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private SourceMapper mapper;
	
	public String getAll(Integer userId,Integer type){
	 
		SourceInfoListWrap sourceInfoListWrap = new SourceInfoListWrap();
		
		String object = null;
		try {
			if(null == userId || "".equals(userId)){
				sourceInfoListWrap.status = "without login!";
			} else {
				sourceInfoListWrap.data = mapper.getAll(userId,type);
				if (null == sourceInfoListWrap.data ){
					sourceInfoListWrap.status = "error";
				} else if(sourceInfoListWrap.data.isEmpty()){
					sourceInfoListWrap.status = "empty";
				} else{
					sourceInfoListWrap.status = "success";
				}
			}
			 
			object = JSONObject.wrap(sourceInfoListWrap).toString();
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return object;
	}
	
	
	public String insertSingle(String sourceJson){
		ObjectMapper objectMapper = new ObjectMapper();
		
		SourceInfo sourceInfo;
		try {
			sourceInfo = objectMapper.readValue(sourceJson, SourceInfo.class);

			boolean isInserted = mapper.addSource(sourceInfo);
		
			ResponseFlag responseFlag = new ResponseFlag();
			responseFlag.status = isInserted? "success":"failed";
			
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("msg", isInserted? "资料信息保存成功!":"资料信息保存失败,请稍后再试!");
			jsonObject.put("id", mapper.getNewId());
			responseFlag.msg = jsonObject.toString();
			
			Object object = JSONObject.wrap(responseFlag);
				
			return object.toString();
		} catch (Exception e) {
			logger.error(e.toString());
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("msg", "资料信息保存失败,请稍后再试!");
			jsonObject.put("id", mapper.getNewId());
			
			ResponseFlag responseFlag = new ResponseFlag();
			responseFlag.status = "failed";
			responseFlag.msg = jsonObject.toString();
			
			Object object = JSONObject.wrap(responseFlag);
				
			return object.toString();
		}
	 

	}
	
	public String deleteSingle(Integer sourceId){
		 
		String object = null;
		try {
			boolean isDeleted = mapper.deleteSingle(sourceId) > 0? true:false;
			ResponseFlag responseFlag = new ResponseFlag();
			responseFlag.status = isDeleted? "success":"failed";
			responseFlag.msg = isDeleted? "删除成功!":"删除失败,请稍后再试!";
			object = JSONObject.wrap(responseFlag).toString();
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return object;
	}
	
	public int getSourceCount(Integer type){
		int count = -1;
		try {
			count = mapper.getCount(type);
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return count;
	}
	
}
