package com.codinggyd.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codinggyd.bean.Article;
import com.codinggyd.bean.ArticleType;
import com.codinggyd.mapper.ArticleMapper;
import com.codinggyd.service.IArticleService;

/**
 * 
 * 
 * @Title:  UtilFunctionServiceImpl.java
 * @Package: com.codinggyd.service.impl
 * @Description: 文章管理
 *
 * @author: guoyd
 * @Date: 2017年1月21日下午7:07:45
 *
 * Copyright @ 2017 Corpration Name
 */
@Service
public class ArticleServiceImpl implements IArticleService{

	final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ArticleMapper mapper;
	@Override
	public void updateArticle(Article article) {
		mapper.updateArticle(article);
	}
	@Override
	public void updateArticleContent(Article article) {
		mapper.updateArticleContent(article);
	}
	@Override
	public void insertArticle(Article article) {
		mapper.insertArticle(article);
	}
	@Override
	public List<ArticleType> findArticleTypes() {
		return mapper.listArticleType();
	}

	@Override
	public List<Article> findArticles() {
		return mapper.findArticles();
	}
	@Override
	public void deleteArticle(Integer id) {
		 mapper.deleteArticle(id);
 	}
	@Override
	public Article queryArticle(Integer id) {
		return mapper.queryArticle(id);
	}
}
