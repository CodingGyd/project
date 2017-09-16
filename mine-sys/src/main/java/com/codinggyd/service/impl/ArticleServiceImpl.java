package com.codinggyd.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.codinggyd.bean.Article;
import com.codinggyd.service.IArticleService;

/**
 * 
 * 
 * @Title:  UtilFunctionServiceImpl.java
 * @Package: com.codinggyd.service.impl
 * @Description: 
 *
 * @author: guoyd
 * @Date: 2017年1月21日下午7:07:45
 *
 * Copyright @ 2017 Corpration Name
 */
@Service
public class ArticleServiceImpl implements IArticleService{

	final Logger logger = LoggerFactory.getLogger(getClass());
	
	public void updateArticle(Article article) {
	}
}
