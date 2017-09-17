package com.codinggyd.service;

import com.codinggyd.bean.Article;
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
	public MinePageBean<Article> getArticleList(Paginator paginator);
	
	public Article findArticleDetail(String id);
	
}
