 
package com.codinggyd.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codinggyd.bean.Article;
import com.codinggyd.bean.MinePageBean;
import com.codinggyd.bean.Paginator;
import com.codinggyd.config.SpringContextHolder;
import com.codinggyd.service.IArticleService;
 
 
@Controller
public class ArticleController {
	@Autowired
	private IArticleService articleService;

	final Logger logger = LoggerFactory.getLogger(getClass());
 
	//分页获取文章列表
	@RequestMapping(value={"/article_page"})
	public @ResponseBody MinePageBean<Article> page(HttpServletRequest request,HttpServletResponse response) {
		String page = request.getParameter("page");
		String limit = request.getParameter("limit");
		String type_dm = request.getParameter("type_dm");
		Paginator paginator = new Paginator(Integer.parseInt(page),Integer.parseInt(limit),Integer.MAX_VALUE);
		
		MinePageBean<Article> data = articleService.getArticleList(paginator,type_dm);
	 
		return data;
	}
	
	//最新文章
	@RequestMapping(value={"/latest_article"})
	public @ResponseBody MinePageBean<Article> latestArticle(HttpServletRequest request,HttpServletResponse response) {
		MinePageBean<Article> data = articleService.getLatestArticleList();
		return data;
	}
	
	//文章详情
	@RequestMapping("/article_dt/{id}")
	public String article_dt(@PathVariable String id,Map<String,Object> model) {

		Article article = articleService.findArticleDetail(id);
		if (null != article) {
			model.put("article",article);
//			//浏览量加1
//			SpringContextHolder.executorService.submit(new Runnable() {
//				
//				@Override
//				public void run() {
//					articleService.updateReadCount(Integer.parseInt(id));
//				}
//			});
		}
		
		return "v2/article_dt";
	}
 
}
