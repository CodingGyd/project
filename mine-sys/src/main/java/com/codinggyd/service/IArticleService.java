package com.codinggyd.service;

import java.util.List;

import com.codinggyd.bean.Article;
import com.codinggyd.bean.ArticleType;

/**
 * 
 * 
 * @Title:  IArticleService.java
 * @Package: com.codinggyd.service
 * @Description: 文章管理
 *
 * @author: guoyd
 * @Date: 2017年9月14日 下午10:01:16
 *
 * Copyright @ 2017 Corpration Name
 */
public interface IArticleService {
	void insertArticle(Article article);
	//更新文章(不包含原文字段的更新)
	void updateArticle(Article article);
	//更新文章(包含原文字段的更新)
	void updateArticleContent(Article article);
	void deleteArticle(Integer id);
	Article queryArticle(Integer id);
	
	//加载文章分类
	public List<ArticleType> findArticleTypes();
	
	//加载文章列表
	public List<Article> findArticles();
}
