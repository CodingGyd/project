package com.codinggyd.RookiePalmSpaceServer.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codinggyd.RookiePalmSpaceServer.service.ArticleService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

/**
 * 
 * @author guoyading
 *
 * create at 2016年8月22日 下午5:25:55
 */
@RestController
@RequestMapping("/articles")
public class ArticleController {
    
	@Autowired   
	public ArticleService articleService;
	
	@RequestMapping(value="/getArticles",method={RequestMethod.GET,RequestMethod.POST})
	public String getSources(@RequestParam("userId") Integer userId,@RequestParam(name="type",required=false) Integer type){
		String result = articleService.getAll(userId, type);
		return result;
	}
	
	@RequestMapping(value="/insertArticle",method={RequestMethod.GET,RequestMethod.POST})
	public String insertSource(@RequestParam("article") String articleJson) throws JsonParseException, JsonMappingException, IOException{
		String result = articleService.insertSingle(articleJson);
		return result;
	}
	
	@RequestMapping(value="/delArticle",method={RequestMethod.GET,RequestMethod.POST})
	public String delSource(@RequestParam("articleId") Integer articleId){
		String result = articleService.deleteSingle(articleId);
		return result;
	}
	
	@RequestMapping(value="/getArticleCount",method={RequestMethod.GET,RequestMethod.POST})
	public String getArticleCount(@RequestParam("userId") Integer userId,@RequestParam(name="type",required=false) Integer type){
		String result = articleService.getArticleCount(type, userId);
		return result;
	}

	@RequestMapping(value="/modifyArticle",method={RequestMethod.GET,RequestMethod.POST})
	public String modifyArticle(@RequestParam("article") String articleJson) throws JsonParseException, JsonMappingException, IOException{
		String result = articleService.modifyArticle(articleJson);
		return result;
	}
}
