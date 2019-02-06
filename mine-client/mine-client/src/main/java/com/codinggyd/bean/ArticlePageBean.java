package com.codinggyd.bean;

import java.util.List;

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
