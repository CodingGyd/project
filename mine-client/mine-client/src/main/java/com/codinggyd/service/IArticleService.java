package com.codinggyd.service;

import java.util.List;

import com.codinggyd.bean.Article;
import com.codinggyd.bean.ArticlePageBean;
import com.codinggyd.bean.ArticleType;
import com.codinggyd.bean.Paginator;
import com.codinggyd.bean.UserInfo;

/**
 * 
 * 
 * @Title:  IArticleService.java
 * @Package: com.codinggyd.service
 * @Description: 文章操作
 *
 * @author: guoyd
 * @Date: 2017年9月14日 下午10:01:16
 *
 * Copyright @ 2017 Corpration Name
 */
public interface IArticleService {
	
	//加载文章列表
	public ArticlePageBean<Article> getArticleList(Paginator paginator,String type_dm,String label_dm);
	//加载最新文章
	public ArticlePageBean<Article> getLatestArticleList();
	//加载随机文章
	public ArticlePageBean<Article> getRandomArticleList();
	//加载文章详情
	public Article findArticleDetail(String id);
	//加载文章分类列表
	public List<ArticleType> findArticleTypes(List<String> lbs,String dm);
	//更新文章阅读数量
	public void updateReadCount(Integer id);
	//加载点击排行前rankTop的文章
	public List<Article> getRankArticleList(Integer rankTop);
	
	//文章点赞
	public String doPraise(Integer articleId,UserInfo userInfo); 
	
}
