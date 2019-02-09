 package com.codinggyd.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.codinggyd.annotation.MineService;
import com.codinggyd.bean.Article;
import com.codinggyd.mapper.ArticleMapper;
import com.codinggyd.redis.RedisClientUtils;
import com.codinggyd.service.IArticleSiteService;
import com.codinggyd.util.PageBoundUtils;
import com.codinggyd.utils.MathUtils;
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
@Transactional(value="mineTransactionManager",propagation=Propagation.NOT_SUPPORTED,readOnly=false)
public class ArticleServiceImpl implements IArticleSiteService{

	final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private ArticleMapper mapper;
	private static final String REDIS_KEY_ARTICLE = "mine_articles_";
	@Override
	public List<Article> listArticle(String type,String[] pageInfo) {
		List<Article> articleList = null;
		try {
			PageBounds pageBounds = null;
			if (null != pageInfo) {
				try {
					pageBounds = PageBoundUtils.getPageBounds(pageInfo);
				} catch (Exception e) {
					logger.error("解析分页参数出错,{},{}",pageInfo,e);
					return null;
				}
			}
			
			if (null != pageBounds) {
				articleList = mapper.findArticle(type,pageBounds);
			} else {
				articleList = mapper.findArticle(type);
			}
			
			if(CollectionUtils.isEmpty(articleList)){
				logger.debug("未查到任何文章信息");
			}
		} catch (Exception e) {
			logger.error("查询文章列表出错,{}",e);
		}
		return articleList;
	}

	@Override
	public Article listDetail(String id) {
		Article article = null;
//		try {
//			article = (Article) RedisClientUtils.getFromCache(REDIS_KEY_ARTICLE+id);
//		} catch (Exception e) {
//			logger.error("缓存 读取出错,{}",e);
//		}
//		if (null != article) {
//			logger.debug("id[{}],查的是缓存",id);
//			return article;
//		}
		try {
			article = mapper.findDetailById(id);
		} catch (Exception e) {
			logger.error("数据库 读取出错,{}",e);
			return null;
		}
//		try {
//			RedisClientUtils.cache(REDIS_KEY_ARTICLE+id, article);//缓存
//		} catch (Exception e) {
//			logger.error("缓存写入出错,{}",e);
//		}
		return article;
	}

	@Override
	public List<Article> listLatestArticle(Integer top) {
		List<Article> articles= null;
		try {
			articles = mapper.findLatestArticle(top);
		} catch (Exception e) {
			logger.error("产生最新文章出错,{}",e);
			return articles;
		}
		return articles;
	}

	@Override
	public List<Article> listRandomArticle(Integer top) {
		
		if (null == top) {
			return null;
		}
		
		List<Article> randArticles = null;
		try {
			//1.查找表里所有的id
			List<Integer> ids = mapper.findArticleIds();
			if (CollectionUtils.isEmpty(ids)) {
				logger.error("文章表中没有数据!");
				return randArticles;
			}
			
			//2.从1中查到的id列表中随机出top个id,sets保存随机id在id列表中的位置
			int size = ids.size();
			if (top > size) {
				return mapper.findRandomArticle(ids);
			}
			
			int max = size-1;
			int min = 0;
			HashSet<Integer> sets = new HashSet<>();
			MathUtils.randomSet(min, max, top,top, sets);
			if (CollectionUtils.isEmpty(sets)) {
				logger.error("获取随机文章为0篇！");
				return randArticles;
			}
			//3.获取随机id集合
			List<Integer> randomIds = new ArrayList<>();
			for (Integer idPos : sets) {
				randomIds.add(ids.get(idPos));
			}
			//4.查询随机文章
			randArticles = mapper.findRandomArticle(randomIds);
		} catch (Exception e) {
			logger.error("产生随机文章出错,{}",e);
			return randArticles;
		}
		return randArticles;
	}

	@Override
	@Transactional(value="mineTransactionManager",propagation=Propagation.REQUIRED,readOnly=false)
	public synchronized String updateReadCount(Integer articleId) {
		try {
			mapper.updateArticleReadCount(articleId);
		} catch (Exception e) {
			logger.error("更新出错,{}",e);
			return "error";
		}
		return "success";
	}

	@Override
	public List<Article> rankTopArticle(Integer rankTop) {
		return  mapper.findArticleOrderByClickCount(rankTop);
	}
}
