package com.codinggyd.mapper;

import org.apache.ibatis.annotations.Param;

import com.codinggyd.bean.Article;

/**
 * 
 * 
 * @Title:  ArticleMapper.java
 * @Package: com.codinggyd.mapper
 * @Description: 
 *
 * @author: guoyd
 * @Date: 2017年9月17日 下午1:45:11
 *
 * Copyright @ 2017 Corpration Name
 */
public interface ArticleMapper {
 	public void updateArticle(@Param("article") Article article);
}
