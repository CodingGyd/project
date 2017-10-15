package com.codinggyd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.codinggyd.bean.Article;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

/**
 * 
 * 
 * @Title:  LearnSiteMapper.java
 * @Package: com.codinggyd.mapper
 * @Description: 
 *
 * @author: guoyd
 * @Date: 2017年2月18日下午2:28:18
 *
 * Copyright @ 2017 Corpration Name
 */
public interface ArticleMapper {
	//文章列表
	public PageList<Article> findArticle(@Param("type") String type, PageBounds pageBounds);
	public List<Article> findArticle(@Param("type") String type);
	
	//文章详情
	public Article findDetailById(@Param("id") String id);

	//最新文章
	public List<Article> findLatestArticle(@Param("top") Integer top);
	
	//随机文章
	public List<Article> findRandomArticle(@Param("ids") List<Integer> ids);
	
	//文章主键集合
	@Select("SELECT ID FROM mine_articles")
	public List<Integer> findArticleIds();

}
