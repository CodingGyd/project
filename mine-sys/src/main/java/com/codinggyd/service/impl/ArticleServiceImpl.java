package com.codinggyd.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.codinggyd.bean.Article;
import com.codinggyd.bean.ArticleKeyWordRelation;
import com.codinggyd.bean.ArticleKeyWordRelationParent;
import com.codinggyd.bean.ArticleType;
import com.codinggyd.mapper.ArticleMapper;
import com.codinggyd.service.IArticleKeyWordRelationService;
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
@Transactional(value="mineTransactionManager",propagation=Propagation.REQUIRED,readOnly=false)
public class ArticleServiceImpl implements IArticleService{

	final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ArticleMapper mapper;
	@Autowired
	@Qualifier("articleKeyWordRelationServiceImpl")
	private IArticleKeyWordRelationService service;
	
	@Override
	public void updateArticle(Article article) {
		mapper.updateArticle(article);
	}
	@Override
	public void updateArticleContent(Article article) {
		mapper.updateArticleContent(article);
		
		ArticleKeyWordRelationParent parent = new ArticleKeyWordRelationParent();
		parent.setArticleId(article.getId());
		parent.setRelations(article.getKeys());
		service.updateRelation(parent);
		
	}
	@Override
	public void insertArticle(Article article) {
		Integer effectRows = mapper.insertArticle(article);
		if (effectRows == 1) {
			Integer newId = article.getId();
			List<ArticleKeyWordRelation> relations = article.getKeys();
			if (CollectionUtils.isNotEmpty(relations)) {
				for (ArticleKeyWordRelation key : relations) {
					key.setArticleId(newId);
				}
				service.insertRelation(relations);
			}
		}
		
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
