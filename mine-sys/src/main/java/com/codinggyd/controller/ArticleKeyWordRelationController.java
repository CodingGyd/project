 
package com.codinggyd.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codinggyd.bean.ArticleKeyWordRelation;
import com.codinggyd.bean.ArticleKeyWordRelationParent;
import com.codinggyd.service.IArticleKeyWordRelationService;
 
/**
 * 
 * @Title:  KeyWordController.java
 * @Package: com.codinggyd.controller
 * @Description: 文章关键词更新查询接口
 *
 * @author: guoyd
 * @Date: 2017年11月5日 上午11:50:58
 *
 * Copyright @ 2017 Corpration Name
 */
@Controller
@RequestMapping("sys/article_keyword_relation")
public class ArticleKeyWordRelationController {
	
	@Qualifier("articleKeyWordRelationServiceImpl")
	@Autowired
	private IArticleKeyWordRelationService service;
	
	final Logger logger = LoggerFactory.getLogger(getClass());
 
	@RequestMapping(value="/relation_byarticleid",method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody List<ArticleKeyWordRelation> getDailySingle(@RequestParam("id") Integer id) {
		List<ArticleKeyWordRelation> keyword = service.getKeyWords(id);
		return keyword;
	}
	
	@RequestMapping(value="/insert",method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody String insert(@RequestBody List<ArticleKeyWordRelation> relations) {
		service.insertRelation(relations);
		return "success";
	}
 
	@RequestMapping(value="/update",method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody String update(@RequestBody ArticleKeyWordRelationParent parent) {
		service.updateRelation(parent);
		return "success";
	}

	@RequestMapping(value="/delete",method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody String delete(@RequestParam("articleId") Integer articleId) {
		service.deleteRelation(articleId);
 		return "success";
	}
}
