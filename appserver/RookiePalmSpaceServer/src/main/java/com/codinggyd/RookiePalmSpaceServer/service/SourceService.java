package com.codinggyd.RookiePalmSpaceServer.service;

import java.util.List;

import org.json.JSONObject;
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
	@Autowired
	private SourceMapper mapper;
	
	public String getAll(Integer userId,Integer type){
	 
		SourceInfoListWrap sourceInfoListWrap = new SourceInfoListWrap();
		
		if(null == userId || "".equals(userId)){
			sourceInfoListWrap.status = "without login!";
		} else {
			List<SourceInfo> list = null;
			System.out.println("===type："+type);
			list = mapper.getAll(userId,type);
			
			sourceInfoListWrap.data = list;
		 
			if (null == list ){
				sourceInfoListWrap.status = "error";
			} else if(list.isEmpty()){
				sourceInfoListWrap.status = "empty";
			} else{
				sourceInfoListWrap.status = "success";
			}
		}
		 
		Object jObject = JSONObject.wrap(sourceInfoListWrap);
		return jObject.toString();
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
			e.printStackTrace();
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
		 
		boolean isDeleted = mapper.deleteSingle(sourceId) > 0? true:false;
		ResponseFlag responseFlag = new ResponseFlag();
		responseFlag.status = isDeleted? "success":"failed";
		responseFlag.msg = isDeleted? "删除成功!":"删除失败,请稍后再试!";
		Object object = JSONObject.wrap(responseFlag);
		return object.toString();
	}
	
	public int getSourceCount(Integer type){
		return mapper.getCount(type);
		 
	}
	
}
