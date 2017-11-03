package com.codinggyd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.codinggyd.bean.Article;
import com.codinggyd.bean.ArticleType;

/**
 * 
 * 
 * @Title:  ArticleMapper.java
 * @Package: com.codinggyd.mapper
 * @Description: 文章管理
 *
 * @author: guoyd
 * @Date: 2017年9月17日 下午1:45:11
 *
 * Copyright @ 2017 Corpration Name
 */
public interface ArticleMapper {
	//更新文章(包含原文字段的更新)
 	public void updateArticleContent(@Param("article") Article article);
 	//更新文章(不包含原文字段的更新)
 	public void updateArticle(@Param("article") Article article);
 	public void insertArticle(@Param("article") Article article);
 	public void deleteArticle(@Param("id") Integer id);
 	public Article queryArticle(@Param("id") Integer id);

 	
	public List<ArticleType> listArticleType();
	
	public List<Article> findArticles();

}
