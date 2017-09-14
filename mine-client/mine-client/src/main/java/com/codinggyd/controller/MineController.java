 
package com.codinggyd.controller;

import java.util.ArrayList;
import java.util.List;
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
import com.codinggyd.service.IArticleService;
 
 
@Controller
public class MineController {
	@Autowired
	private IArticleService service;
	final Logger logger = LoggerFactory.getLogger(getClass());
 
	//首页
	@RequestMapping(value={"/","/index"})
	public String index(HttpServletRequest request,HttpServletResponse response) {
		
		List<Article> articles = new ArrayList<>();
		for (int i=0;i<10;i++) {
			Article article = new Article();
			article.setContent("测测测测测测测测测测测测测测测测测测测测测测测测测测测测测测测测"+i);
			article.setTitle("测试文章"+i);
			article.setId(i);
			article.setUpdatetime("2017-08-09");
			articles.add(article);
		}
		request.setAttribute("articleList", articles);
		return "index";
	}
	
	//首页
	@RequestMapping(value={"/page"})
	public @ResponseBody String page(HttpServletRequest request,HttpServletResponse response) {
		String curPage = request.getParameter("page");
		return curPage;
	}
	
	
	
	//文章详情
	@RequestMapping("/detail")
	public String article_dt(@ModelAttribute(value = "content") String content,Map<String,Object> model) {
		model.put("content",content);  
		return "article_dt";
	}
 
	//留言板
	@RequestMapping("/guestbook")
	public String guestbook() {
		return "guestbook";
	}
	//关于我
	@RequestMapping("/about")
	public String about() {
		return "about";
	}
	
	 
	//碎言碎语
	@RequestMapping("/shuo")
	public String shuo() {
		return "shuo";
	}
}
