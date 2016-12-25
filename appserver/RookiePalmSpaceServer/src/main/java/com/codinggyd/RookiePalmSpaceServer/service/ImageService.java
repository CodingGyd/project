package com.codinggyd.RookiePalmSpaceServer.service;


import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codinggyd.RookiePalmSpaceServer.bean.ImageInfo;
import com.codinggyd.RookiePalmSpaceServer.bean.ImageInfoListWrap;
import com.codinggyd.RookiePalmSpaceServer.bean.ResponseFlag;
import com.codinggyd.RookiePalmSpaceServer.mapper.ImageMapper;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @ClassName: ImageService.java
 * @Description: 
 * @author guoyd
 * @date 2016年8月22日 下午3:46:26
 */
@Service
public class ImageService {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	public ImageMapper mapper;
	 
	 
	public String getAll(Integer userId,Integer type){
	 
		ImageInfoListWrap imageInfoListWrap = new ImageInfoListWrap();
		
		String jObject = null;
		try {
			if(null == userId || "".equals(userId)){
				imageInfoListWrap.status = "without login!";
			} else {
					 
				imageInfoListWrap.data =  mapper.getAll(userId,type);
			 
				if (null == imageInfoListWrap.data ){
					imageInfoListWrap.status = "error";
				} else if(imageInfoListWrap.data.isEmpty()){
					imageInfoListWrap.status = "empty";
				} else{
					imageInfoListWrap.status = "success";
				}
			}
			 
			jObject = JSONObject.wrap(imageInfoListWrap).toString();
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return jObject;
	}
	
	
	public String insertSingle(String imageInfoJson) throws JsonParseException, JsonMappingException, IOException{
		
		ObjectMapper objectMapper = new ObjectMapper();
		String object = null;
		try {
			ImageInfo imageinfo = objectMapper.readValue(imageInfoJson, ImageInfo.class);
			
			boolean isInserted = mapper.addImage(imageinfo);

			ResponseFlag responseFlag = new ResponseFlag();
			responseFlag.status = isInserted? "success":"failed";
			
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("msg", isInserted? "图片信息保存成功!":"图片信息保存失败,请稍后再试!");
			jsonObject.put("id", mapper.getNewId()+"");
			responseFlag.msg = jsonObject.toString();
			
			object = JSONObject.wrap(responseFlag).toString();
		} catch (JSONException e) {
			logger.error(e.toString());
		}
		return object;
	}
	
	public String deleteSingle(Integer imageId){
		String object = null;
		try {
			boolean isDeleted = mapper.deleteSingle(imageId) > 0?true:false;
			ResponseFlag responseFlag = new ResponseFlag();
			responseFlag.status = isDeleted? "success":"failed";
			responseFlag.msg = isDeleted? "删除成功!":"删除失败,请稍后再试!";
			object = JSONObject.wrap(responseFlag).toString();
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return object;
	}
	
	public int getImageCount(Integer type){
		int count = -1;
		try {
			count = mapper.getCount(type);
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return count;
	}
	
}
