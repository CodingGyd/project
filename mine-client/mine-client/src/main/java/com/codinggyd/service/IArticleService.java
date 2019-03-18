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
 * @Description: 文章操作业务层
 *
 * @author: guoyd
 * @Date: 2017年9月14日 下午10:01:16
 *
 * Copyright @ 2017 Corpration Name
 */
public interface IArticleService {
	
	/**
	 * 文章列表查询
	 * @param paginator 分页信息
	 * @param type_dm 文章分类代码
	 * @param label_dm 文章标签代码
	 * @return
	 */
	public ArticlePageBean<Article> getArticleList(Paginator paginator,String type_dm,String label_dm);
	/**
	 * 查询最新的10篇文章
	 * @return
	 */
	public ArticlePageBean<Article> getLatestArticleList();
	/**
	 * 随机获取10篇文章
	 * @return
	 */
	public ArticlePageBean<Article> getRandomArticleList();
	/**
	 * 根据文章ID查询文章详情
	 * @param id 文章ID
	 * @return
	 */
	public Article findArticleDetail(String id);
	/**
	 * 加载文章分类列表
	 * @param lbs 文章分类大类代码
	 * @param dm 文章分类小类代码
	 * @return
	 */
	public List<ArticleType> findArticleTypes(List<String> lbs,String dm);
	/**
	 * 将文章阅读数量+1
	 * @param id 文章ID
	 */
	public void updateReadCount(Integer id);
	/**
	 * 查询点击排行前rankTop的文章
	 * @param rankTop 排行
	 * @return
	 */
	public List<Article> getRankArticleList(Integer rankTop);
	
	/**
	 * 更新文章点赞量
	 * @param articleId 文章ID
	 * @param userInfo 点赞用户信息
	 * @return
	 */
	public String doPraise(Integer articleId,UserInfo userInfo); 
	
	/**
	 * 根据关键字检索文章列表
	 * @param searchcontent  关键字
	 * @return
	 */
	public List<Article> getSearchArticleList(String searchcontent);
	
}
