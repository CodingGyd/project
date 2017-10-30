package com.codinggyd.service;

import java.util.List;

import com.codinggyd.bean.Article;
import com.codinggyd.bean.ArticleType;

/**
 * 
 * 
 * @Title:  IArticleService.java
 * @Package: com.codinggyd.service
 * @Description: 文章列表获取
 *
 * @author: guoyd
 * @Date: 2017年9月14日 下午10:01:16
 *
 * Copyright @ 2017 Corpration Name
 */
public interface IArticleService {
	public void insertArticle(Article article);
	public void updateArticle(Article article);
	public void deleteArticle(Integer id);

	
	//加载文章分类
	public List<ArticleType> findArticleTypes();
	
	//加载文章列表
	public List<Article> findArticles();
}
