package com.codinggyd.bean;

import java.util.List;
/**
 * 
 * 
 * @Title: ArticlePageBean.java
 * @Package: com.codinggyd.bean
 * @Description: 文章分类分页对象
 * 
 * @Author: guoyd
 * @Date: 2019年2月21日 下午5:41:50
 *
 * Copyright @ 2019 Corpration Name
 */
public class ArticlePageBean<E> extends MinePageBean<E> {
   
	private ArticleType articleType;
	private ArticleKeyWordRelation keyWordRelation;
	public ArticlePageBean(ArticleType articleType,Paginator paginator, List<E> data) {
		super(paginator,data);
		this.articleType = articleType;
	}
	
	public ArticlePageBean(ArticleType articleType,ArticleKeyWordRelation keyWordRelation,Paginator paginator, List<E> data) {
		super(paginator,data);
		this.articleType = articleType;
		this.keyWordRelation = keyWordRelation;
	}
	public ArticleType getArticleType() {
		return articleType;
	}

	public void setArticleType(ArticleType articleType) {
		this.articleType = articleType;
	}

	public ArticleKeyWordRelation getKeyWordRelation() {
		return keyWordRelation;
	}

	public void setKeyWordRelation(ArticleKeyWordRelation keyWordRelation) {
		this.keyWordRelation = keyWordRelation;
	}
	
}
