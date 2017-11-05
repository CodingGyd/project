 
package com.codinggyd.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codinggyd.bean.Article;
import com.codinggyd.bean.ArticleKeyWordRelation;
import com.codinggyd.bean.ArticleType;
import com.codinggyd.bean.DataTable;
import com.codinggyd.constant.AppConfig;
import com.codinggyd.redis.RedisClientUtils;
import com.codinggyd.service.IArticleService;
import com.codinggyd.util.CommonUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
 
/**
 * 
 * @Title:  ArticleController.java
 * @Package: com.codinggyd.controller
 * @Description: 文章管理相关接口
 *
 * @author: guoyd
 * @Date: 2017年11月3日 下午9:58:18
 *
 * Copyright @ 2017 Corpration Name
 */
@Controller
@RequestMapping("sys/article")
public class ArticleController {
	
	@Qualifier("articleServiceImpl")
	@Autowired
	private IArticleService service;
	
	@Value("${image.upload.dir}")
	private String imageUploadDir;
	final Logger logger = LoggerFactory.getLogger(getClass());

	private static final String PATTERN = "yyyy-MM-dd HH:mm.ss.SSS";

	@RequestMapping(value="/article_byid",method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Article getArticleSingle(HttpServletRequest request,HttpServletResponse response) {
		Integer id = Integer.parseInt(request.getParameter("id"));
		Article article = service.queryArticle(id);
		return article;
	}
	
	@RequestMapping(value="/articlelist",method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody DataTable<Article> getArticleList(HttpServletRequest request,HttpServletResponse response) {
		
		DataTable<Article> articleTable = new DataTable<Article>();
		articleTable.setTotal(0);
		List<Article> articles = service.findArticles();
		if (CollectionUtils.isNotEmpty(articles)) {
			articleTable.setRows(articles);
			articleTable.setTotal(articles.size());
		}
		 
		return articleTable;
	}
	
	@RequestMapping(value="/insert",method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody String insert(HttpServletRequest request,HttpServletResponse response) {
		List<ArticleKeyWordRelation> keys = formatKeysRelation(request.getParameter("keywords"));
		 
		Article article = new Article();
		article.setTitle(request.getParameter("title"));
		article.setContent(request.getParameter("content"));
		article.setHtmlContent(request.getParameter("htmlContent"));
		article.setDescs(request.getParameter("descs"));
		article.setUpdatetime(DateFormatUtils.format(new Date(), PATTERN));
		article.setReadingcount(0);
		article.setType(request.getParameter("type"));
		article.setKeys(keys);
		service.insertArticle(article);
		return "success";
	}
	
	@RequestMapping(value="/update_notwithcontent",method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody String updateNotWithContent(@RequestBody Article article) {
		if (null != article) {
			article.setUpdatetime(DateFormatUtils.format(new Date(), PATTERN));
			service.updateArticle(article);
			RedisClientUtils.deleteFromCache(article.getId()+"");
		}
		return "success";
	}
	
	@RequestMapping(value="/update_withcontent",method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody String updateWithContent(HttpServletRequest request,HttpServletResponse response) {
		
		List<ArticleKeyWordRelation> keys = formatKeysRelation(request.getParameter("keywords"));
		
		Integer articleId = Integer.parseInt(request.getParameter("id"));
		if(CollectionUtils.isNotEmpty(keys)) {
			for (ArticleKeyWordRelation key : keys ) {
				key.setArticleId(articleId);
			}
		}
		
		Article article = new Article();
		article.setId(articleId);
		article.setTitle(request.getParameter("title"));
		article.setContent(request.getParameter("content"));
		article.setHtmlContent(request.getParameter("htmlContent"));
		article.setDescs(request.getParameter("descs"));
		article.setUpdatetime(DateFormatUtils.format(new Date(), PATTERN));
		article.setType(request.getParameter("type"));
		article.setKeys(keys);
 		service.updateArticleContent(article);
 		RedisClientUtils.deleteFromCache(AppConfig.getRedis_key_article()+article.getId());
		return "success";
	}

	@RequestMapping(value="/delete",method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody String delete(Integer id) {
	
		service.deleteArticle(id);
 		RedisClientUtils.deleteFromCache(AppConfig.getRedis_key_article()+id);
		return "success";
	}
  
	@RequestMapping(value={"/article_types"})
	public @ResponseBody List<ArticleType> listTypes(HttpServletRequest request,HttpServletResponse response) {
		List<ArticleType> data = service.findArticleTypes();
		return data;
	}
	
	
	private List<ArticleKeyWordRelation> formatKeysRelation(String keywordsJson) {
		List<ArticleKeyWordRelation> result = new ArrayList<>();
		String[] keywords = null;
		if (StringUtils.isNotEmpty(keywordsJson)) {
			try {
				ObjectMapper om = CommonUtils.getMappingInstance();
				keywords = om.readValue(keywordsJson, String[].class);
			} catch (Exception e) {
				logger.error("文章关键词数据有误,{}",e);
			}
			if (null != keywords && keywords.length > 0) {
				Date date = new Date();
				
				for (String keyid : keywords) {
					ArticleKeyWordRelation k = new ArticleKeyWordRelation();
					k.setKeyWordId(Integer.parseInt(keyid));
					k.setUpdatetime(DateFormatUtils.format(date, PATTERN));
					result.add(k);
				}
 			}
		}
		return result;
	}
}
