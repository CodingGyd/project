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

	public ArticlePageBean(ArticleType articleType,Paginator paginator, List<E> data) {
		super(paginator,data);
		this.articleType = articleType;
	}
	public ArticleType getArticleType() {
		return articleType;
	}

	public void setArticleType(ArticleType articleType) {
		this.articleType = articleType;
	}
}
