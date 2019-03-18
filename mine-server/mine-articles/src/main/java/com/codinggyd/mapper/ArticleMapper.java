package com.codinggyd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.codinggyd.bean.Article;
import com.codinggyd.bean.ArticleKeyWordRelation;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

/**
 * 
 * 
 * @Title:  ArticleMapper.java
 * @Package: com.codinggyd.mapper
 * @Description: 文章相关表操作类
 *
 * @author: guoyd
 * @Date: 2017年2月18日下午2:28:18
 *
 * Copyright @ 2017 Corpration Name
 */
public interface ArticleMapper {
	//文章列表
	public PageList<Article> findArticle(@Param("type") String type,@Param("label") String label, PageBounds pageBounds);
	public List<Article> findArticle(@Param("type") String type,@Param("label") String label);
	@Select("SELECT A.id,title,descs,A.updatetime,A.readingcount,A.url,A.praisecount,type,B.ms typeName"+
			"	FROM mine_articles A "+
			"	INNER JOIN mine_sysconst B "+
			"	ON A.type = B.dm AND B.lb = '100' "+
			" ORDER BY A.readingcount DESC LIMIT 10 " )
	public List<Article> findArticleOrderByClickCount(@Param("rankTop") Integer rankTop);

	//文章详情
	public Article findDetailById(@Param("id") String id);

	//最新文章
	public List<Article> findLatestArticle(@Param("top") Integer top);
	
	//随机文章
	public List<Article> findRandomArticle(@Param("ids") List<Integer> ids);
	
	//搜索文章
	public List<Article> findArticlelByTitle(@Param("condition") String condition);

	//文章主键集合
	@Select("SELECT ID FROM mine_articles")
	public List<Integer> findArticleIds();
	
	//文章阅读数量加1
	@Update("UPDATE mine_articles SET readingcount = readingcount+1 WHERE id=#{id}")
	public void updateArticleReadCount(@Param("id") Integer id);

	//文章点赞数量加1
	@Update("UPDATE mine_articles SET praisecount = praisecount+1 WHERE id=#{id}")
	public void updateArticlePraiseCount(@Param("id") Integer id);

		
	//文章关键字列表
	@Select("SELECT A.id,A.articleId,B.id keyWordId,B.name keyName,A.updatetime FROM mine_article_keyword_relation A INNER JOIN mine_keywords B " + 
			"	ON A.keyId = B.id WHERE A.articleId=#{articleId}")
 	public List<ArticleKeyWordRelation> queryRelation(@Param("articleId") Integer articleId);

}
