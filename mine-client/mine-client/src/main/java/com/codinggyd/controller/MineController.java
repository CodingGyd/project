// 
//package com.codinggyd.controller;
//
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.codinggyd.bean.Article;
//import com.codinggyd.bean.ArticleType;
//import com.codinggyd.bean.DailEssays;
//import com.codinggyd.bean.MinePageBean;
//import com.codinggyd.bean.Paginator;
//import com.codinggyd.config.SpringContextHolder;
//import com.codinggyd.service.IArticleService;
//import com.codinggyd.service.IDailyEssayService;
// 
// 
//@Controller
//public class MineController {
//	@Autowired
//	private IArticleService articleService;
//	@Autowired
//	private IDailyEssayService dailyService;
//	final Logger logger = LoggerFactory.getLogger(getClass());
// 
//	//分页获取文章列表
//	@RequestMapping(value={"/article_page"})
//	public @ResponseBody MinePageBean<Article> page(HttpServletRequest request,HttpServletResponse response) {
//		String page = request.getParameter("page");
//		String limit = request.getParameter("limit");
//		String type_dm = request.getParameter("type_dm");
//		Paginator paginator = new Paginator(Integer.parseInt(page),Integer.parseInt(limit),Integer.MAX_VALUE);
//		
//		MinePageBean<Article> data = articleService.getArticleList(paginator,type_dm);
//	 
//		return data;
//	}
//	
//	//最新文章
//	@RequestMapping(value={"/latest_article"})
//	public @ResponseBody MinePageBean<Article> latestArticle(HttpServletRequest request,HttpServletResponse response) {
//		MinePageBean<Article> data = articleService.getLatestArticleList();
//		return data;
//	}
//	//随机文章
//	@RequestMapping(value={"/random_article"})
//	public @ResponseBody MinePageBean<Article> randomArticle(HttpServletRequest request,HttpServletResponse response) {
//		MinePageBean<Article> data = articleService.getRandomArticleList();
//		return data;
//	}
//	
//	//文章分类
//	@RequestMapping(value={"/article_types"})
//	public @ResponseBody MinePageBean<ArticleType> listTypes(HttpServletRequest request,HttpServletResponse response) {
//		MinePageBean<ArticleType> data = new MinePageBean<>(null, articleService.findArticleTypes());
//		return data;
//	}
//	
//	//文章详情
//	@RequestMapping("/article_dt/{id}")
//	public String article_dt(@PathVariable String id,Map<String,Object> model) {
//
//		Article article = articleService.findArticleDetail(id);
//		if (null != article) {
//			model.put("article",article);
//			//浏览量加1
//			SpringContextHolder.executorService.submit(new Runnable() {
//				
//				@Override
//				public void run() {
//					articleService.updateReadCount(Integer.parseInt(id));
//				}
//			});
//		}
//		
//		return "article_dt";
//	}
//	
//	//随笔
//	@RequestMapping(value={"/daily_essays"})
//	public @ResponseBody MinePageBean<DailEssays> dailyEssays(HttpServletRequest request,HttpServletResponse response) {
//		MinePageBean<DailEssays> data = dailyService.getDailyEssays();
//		return data;
//	}
//  
//}
