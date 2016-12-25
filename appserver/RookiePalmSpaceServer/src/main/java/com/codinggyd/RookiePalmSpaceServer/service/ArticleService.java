package com.codinggyd.RookiePalmSpaceServer.service;

import java.io.IOException;
import java.util.List;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codinggyd.RookiePalmSpaceServer.bean.ArticleInfo;
import com.codinggyd.RookiePalmSpaceServer.bean.ArticleInfoListWrap;
import com.codinggyd.RookiePalmSpaceServer.bean.ResponseFlag;
import com.codinggyd.RookiePalmSpaceServer.mapper.ArticleMapper;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @ClassName: ArticleService.java
 * @Description: 
 * @author guoyd
 * @date 2016年8月22日 下午4:41:33
 */
@Service
public class ArticleService {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	public ArticleMapper mapper;
	
	public String getAll(Integer userId,Integer type){
		 
		ArticleInfoListWrap articleInfoListWrap = new ArticleInfoListWrap();
		
		Object jObject = null;
		try {
			if(null == userId || "".equals(userId)){
				articleInfoListWrap.status = "without login!";
			} else {
			 
				articleInfoListWrap.data = mapper.getAll(userId,type);
			 
				if (null == articleInfoListWrap.data  ){
					articleInfoListWrap.status = "error";
				} else if(articleInfoListWrap.data .isEmpty()){
					articleInfoListWrap.status = "empty";
				} else{
					articleInfoListWrap.status = "success";
				}
			}
			 
			jObject = JSONObject.wrap(articleInfoListWrap);
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return jObject.toString();
	}
	
 
	public String insertSingle(String articleInfoJson) throws JsonParseException, JsonMappingException, IOException{
		ObjectMapper objectMapper = new ObjectMapper();
		
		ArticleInfo articleInfo = objectMapper.readValue(articleInfoJson, ArticleInfo.class);
		boolean isInserted = mapper.insertSingle(articleInfo);
	
		ResponseFlag responseFlag = new ResponseFlag();
		responseFlag.status = isInserted? "success":"failed";
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("msg", isInserted? "文章记录保存成功!":"文章信息保存失败,请稍后再试!");
		jsonObject.put("id", mapper.getNewId()+"");
		responseFlag.msg = jsonObject.toString();
		
		Object object = JSONObject.wrap(responseFlag);
		return object.toString();
	}
	
	public String deleteSingle(Integer articleId){
		boolean isDeleted = mapper.deleteSingle(articleId) > 0?true:false;
		ResponseFlag responseFlag = new ResponseFlag();
		responseFlag.status = isDeleted? "success":"failed";
		responseFlag.msg = isDeleted? "删除成功!":"删除失败,请稍后再试!";
		Object object = JSONObject.wrap(responseFlag);
		return object.toString();
	}
	
	public String getArticleCount(Integer type,Integer userId){
		int count = mapper.getCount(type,userId);
		ResponseFlag responseFlag = new ResponseFlag();
		responseFlag.status = "success";
		responseFlag.msg = count+"";
		Object object = JSONObject.wrap(responseFlag);
		return object.toString();
	}
	
	public String modifyArticle(String articleInfoJson) throws JsonParseException, JsonMappingException, IOException{
		
		ObjectMapper objectMapper = new ObjectMapper();
		ArticleInfo articleInfo = objectMapper.readValue(articleInfoJson, ArticleInfo.class);
		
		boolean modifyResult = mapper.updateSingle(articleInfo);
		ResponseFlag responseFlag = new ResponseFlag();
		responseFlag.status = modifyResult? "success":"failed";
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("msg", modifyResult? "修改成功!":"修改失败,请稍后再试!");
		 
		responseFlag.msg = jsonObject.toString();
		 
		Object object = JSONObject.wrap(responseFlag);
		return object.toString();
	}
	 
	public ArticleInfo getArticleById(Integer articleId){
		return mapper.getArticle(articleId);
	}
	
	public List<ArticleInfo> getArtilesToWeb(Integer userId,Integer type){
		 
			return mapper.getAll(userId, type);
	}
}
