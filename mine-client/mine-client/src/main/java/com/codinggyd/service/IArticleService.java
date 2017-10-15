package com.codinggyd.service;

import java.util.List;

import com.codinggyd.bean.Article;
import com.codinggyd.bean.ArticleType;
import com.codinggyd.bean.MinePageBean;
import com.codinggyd.bean.Paginator;

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
	
	//加载文章列表
	public MinePageBean<Article> getArticleList(Paginator paginator,String type_dm);
	//加载最新文章
	public MinePageBean<Article> getLatestArticleList();
	//加载随机文章
	public MinePageBean<Article> getRandomArticleList();
	//加载文章详情
	public Article findArticleDetail(String id);
	//加载文章分类
	public List<ArticleType> findArticleTypes();
	
}
