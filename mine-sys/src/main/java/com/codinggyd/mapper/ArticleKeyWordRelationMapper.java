package com.codinggyd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import com.codinggyd.bean.ArticleKeyWordRelation;

/**
 * 
 * @Title:  ArticleKeyWordRelationMapper.java
 * @Package: com.codinggyd.mapper
 * @Description: 文章关键词关系
 *
 * @author: guoyd
 * @Date: 2017年11月5日 下午12:44:43
 *
 * Copyright @ 2017 Corpration Name
 */
public interface ArticleKeyWordRelationMapper {
/* 	public List<ArticleKeyWordRelation> findRelation();*/
  	public void insertRelation(@Param("keys") List<ArticleKeyWordRelation> keys);
 	public List<ArticleKeyWordRelation> queryRelation(@Param("articleId") Integer articleId);
 	@Update("DELETE FROM mine_article_keyword_relation WHERE articleId = #{articleId}")
 	public void deleteRelation(@Param("articleId") Integer articleId);
}
