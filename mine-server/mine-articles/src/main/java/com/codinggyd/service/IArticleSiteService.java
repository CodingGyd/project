package com.codinggyd.service;

import java.util.List;

import com.codinggyd.annotation.MineMethod;
import com.codinggyd.bean.Article;

/**
 * 
 * 
 * @Title:  ArticleSiteService.java
 * @Package: com.codinggyd.service
 * @Description: 文章操作接口层
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
	public List<Article> listArticle(String type,String label,String[] pageInfo);
	
	/**
	 * 点击排行前n的文章
	 * @param type 文章分类
	 * @param pageInfo 分页参数
	 * @return
	 */
	@MineMethod(value="MINE_ARTICLE_LIST_RANK_TOP")
	public List<Article> rankTopArticle(Integer rankTop);
	
	
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
	 * @return  最新的top篇文章
	 */
	@MineMethod(value="MINE_LATEST_ARTICLE")
	public List<Article> listLatestArticle(Integer top);
	
	/**
	 * 随机文章
	 * @param top 要获取的随机文章数量
	 * @return  随机的top篇文章
	 */
	@MineMethod(value="MINE_RANDOM_ARTICLE")
	public List<Article> listRandomArticle(Integer top);
	
	/**
	 * 更新文章数量，阅读数量加一
	 * @param id 文章ID
	 */
	@MineMethod(value="MINE_ARTICLE_UPDATE_READ_COUNT")
	public String updateReadCount(Integer articleId);
	
	/**
	 * 文章点赞加1
	 * @param id
	 * @return
	 */
	@MineMethod(value="MINE_ARTICLE_PRAISE")
	public String doPraise(Integer articleId);
	
	/**
	 * 文章搜索
	 * @param searchcontent
	 * @return
	 */
	@MineMethod(value="MINE_ARTICLE_SEARCH")
	public List<Article> searchArticle(String searchcontent);
	
	
}
