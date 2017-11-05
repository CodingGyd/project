package com.codinggyd.service;

import java.util.List;

import com.codinggyd.bean.ArticleKeyWordRelation;
import com.codinggyd.bean.ArticleKeyWordRelationParent;

/**
 * 
 * @Title:  IArticleKeyWordRelationService.java
 * @Package: com.codinggyd.service
 * @Description: 文章关键字查询
 *
 * @author: guoyd
 * @Date: 2017年11月5日 下午12:57:41
 *
 * Copyright @ 2017 Corpration Name
 */
public interface IArticleKeyWordRelationService {
	public List<ArticleKeyWordRelation> getKeyWords(Integer articleId);
	public void updateRelation(ArticleKeyWordRelationParent keys);
	public void insertRelation(List<ArticleKeyWordRelation> keys);
	public void deleteRelation(Integer articleId);
}
