package com.codinggyd.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codinggyd.annotation.MineService;
import com.codinggyd.bean.Article;
import com.codinggyd.mapper.ArticleMapper;
import com.codinggyd.service.IArticleSiteService;
import com.codinggyd.util.PageBoundUtils;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

/**
 * 
 * 
 * @Title:  articleServiceImpl.java
 * @Package: com.codinggyd.service.impl
 * @Description: 
 *
 * @author: guoyd
 * @Date: 2017年2月18日下午2:29:45
 *
 * Copyright @ 2017 Corpration Name
 */
@Service
@MineService
public class ArticleServiceImpl implements IArticleSiteService{

	final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private ArticleMapper mapper;
	
	@Override
	public List<Article> listArticle(String type,String[] pageInfo) {
		
		PageBounds pageBounds = null;
		if (null != pageInfo) {
			try {
				pageBounds = PageBoundUtils.getPageBounds(pageInfo);
			} catch (Exception e) {
				logger.error("解析分页参数出错,{},{}",pageInfo,e);
				return null;
			}
		}
		
		List<Article> articleList = null;
		if (null != pageBounds) {
			articleList = mapper.findArticle(type,pageBounds);
		} else {
			articleList = mapper.findArticle(type);
		}
		
		if(CollectionUtils.isEmpty(articleList)){
			logger.debug("未查到任何文章信息");
		}
		
		return articleList;
	}

	@Override
	public Article listDetail(String id) {
		return mapper.findDetailById(id);
	}

}
