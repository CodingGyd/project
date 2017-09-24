package com.codinggyd.service;

import java.util.List;

import com.codinggyd.annotation.MineMethod;
import com.codinggyd.bean.Article;

/**
 * 
 * 
 * @Title:  ArticleSiteService.java
 * @Package: com.codinggyd.service
 * @Description: 
 *
 * @author: guoyd
 * @Date: 2017年9月17日 下午4:08:24
 *
 * Copyright @ 2017 Corpration Name
 */
public interface IArticleSiteService {
	
	/**
	 * 文章列表
	 * @param type 文章分类
	 * @param pageInfo 分页参数
	 * @return
	 */
	@MineMethod(value="MINE_ARTICLE_LIST")
	public List<Article> listArticle(String type,String[] pageInfo);
	
	/**
	 * 文章详情
	 * @param id 文章id
	 * @return
	 */
	@MineMethod(value="MINE_ARTICLE_DETAIL")
	public Article listDetail(String id);
	
	/**
	 * 最新文章
	 * @param top 要获取的最新文章数量
	 * @return  最新的5篇文章
	 */
	@MineMethod(value="MINE_LATEST_ARTICLE")
	public List<Article> listLatestArticle(Integer top);
}
