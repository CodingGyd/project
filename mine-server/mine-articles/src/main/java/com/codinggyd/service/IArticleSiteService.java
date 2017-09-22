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
	
	@MineMethod(value="MINE_ARTICLE_LIST")
	public List<Article> listArticle(String type,String[] pageInfo);
	
	@MineMethod(value="MINE_ARTICLE_DETAIL")
	public Article listDetail(String id);
}
