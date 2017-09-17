 
package com.codinggyd.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codinggyd.bean.Article;
import com.codinggyd.bean.MinePageBean;
import com.codinggyd.bean.Paginator;
import com.codinggyd.service.IArticleService;
 
 
@Controller
public class MineController {
	@Autowired
	private IArticleService service;
	final Logger logger = LoggerFactory.getLogger(getClass());
 
	
	//分页获取文章列表
	@RequestMapping(value={"/article_page"})
	public @ResponseBody MinePageBean<Article> page(HttpServletRequest request,HttpServletResponse response) {
		String page = request.getParameter("page");
		String limit = request.getParameter("limit");
		
		Paginator paginator = new Paginator(Integer.parseInt(page),Integer.parseInt(limit),Integer.MAX_VALUE);
		
		MinePageBean<Article> data = service.getArticleList(paginator);
	 
		return data;
	}
	
	
	
	//文章详情
	@RequestMapping("/article_dt/{id}")
	public String article_dt(@PathVariable String id,Map<String,Object> model) {
		model.put("article",service.findArticleDetail(id));
		return "article_dt";
	}
	
	//文章详情
	@RequestMapping("/load_article")
	public @ResponseBody Article loadArticle(@ModelAttribute(value = "id") String id) {
		return service.findArticleDetail(id);
	}
  
}
