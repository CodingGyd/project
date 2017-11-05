package com.codinggyd.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @Title:  ArticleKeyWordRelationParent.java
 * @Package: com.codinggyd.bean
 * @Description: 
 *
 * @author: guoyd
 * @Date: 2017年11月5日 下午1:32:54
 *
 * Copyright @ 2017 Corpration Name
 */
public class ArticleKeyWordRelationParent implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5459414973085124251L;
	/**
	 * 文章编号
	 */
	private Integer articleId;
	/**
	 * 文章绑定关键字列表
	 */
	private List<ArticleKeyWordRelation> relations;
	public Integer getArticleId() {
		return articleId;
	}
	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}
	public List<ArticleKeyWordRelation> getRelations() {
		return relations;
	}
	public void setRelations(List<ArticleKeyWordRelation> relations) {
		this.relations = relations;
	}
	
}
