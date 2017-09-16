 
package com.codinggyd.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
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
 
//	//首页
//	@RequestMapping(value={"/","/index"})
//	public String index(HttpServletRequest request,HttpServletResponse response) {
//		String curPage = request.getParameter("curPage");
//		PageList<Article> articles = service.getArticleList(Integer.parseInt(curPage));
////		 asadsa
//		request.setAttribute("articleList", articles);
//		request.setAttribute("pageinfo", articles.getPaginator());
//
//		return "index";
//	}
	
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
	@RequestMapping("/detail")
	public String article_dt(@ModelAttribute(value = "content") String content,Map<String,Object> model) {
		model.put("content",content);  
		return "article_dt";
	}
  
}
