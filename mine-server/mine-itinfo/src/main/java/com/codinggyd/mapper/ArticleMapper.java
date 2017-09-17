package com.codinggyd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

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
	public PageList<Article> findArticle(PageBounds pageBounds);
	public List<Article> findArticle();
	
	public Article findDetailById(@Param("id") String id);

}
