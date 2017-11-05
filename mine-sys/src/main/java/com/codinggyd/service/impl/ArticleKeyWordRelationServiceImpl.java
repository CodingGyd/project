package com.codinggyd.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.codinggyd.bean.ArticleKeyWordRelation;
import com.codinggyd.bean.ArticleKeyWordRelationParent;
import com.codinggyd.bean.KeyWord;
import com.codinggyd.mapper.ArticleKeyWordRelationMapper;
import com.codinggyd.service.IArticleKeyWordRelationService;
import com.codinggyd.service.IKeyWordService;

/**
 * 
 * @Title:  KeyWordServiceImpl.java
 * @Package: com.codinggyd.service.impl
 * @Description: 
 *
 * @author: guoyd
 * @Date: 2017年11月5日 上午11:43:03
 *
 * Copyright @ 2017 Corpration Name
 */
@Service
public class ArticleKeyWordRelationServiceImpl implements IArticleKeyWordRelationService{

	final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private ArticleKeyWordRelationMapper mapper;
	@Qualifier("keyWordServiceImpl")
	@Autowired
	private IKeyWordService keyWordService;
	private static final String PATTERN = "yyyy-MM-dd HH:mm.ss.SSS";

	@Override
	public List<ArticleKeyWordRelation> getKeyWords(Integer articleId) {
		
		List<ArticleKeyWordRelation> datas = mapper.queryRelation(articleId);
		if (CollectionUtils.isNotEmpty(datas)) {
			List<Integer> keyIds = new ArrayList<>();
			for (ArticleKeyWordRelation relation : datas) {
				if (!keyIds.contains(relation.getKeyWordId())) {
					keyIds.add(relation.getKeyWordId());
				}
			}
			
			List<KeyWord> keyInfos = keyWordService.getKeyWords(keyIds);
			if (CollectionUtils.isEmpty(keyInfos)) {
				logger.error("文章编号[{}]绑定的关键词在源表都已不存在!",articleId,keyIds.toString());
				return null;
			}
 
			Map<Integer,String> keyIdAndNameMap = new HashMap<>();
			for (KeyWord keyInfo : keyInfos) {
				keyIdAndNameMap.put(keyInfo.getId(), keyInfo.getName());
			}
			
			for (ArticleKeyWordRelation relation : datas) {
				String keyName = keyIdAndNameMap.get(relation.getKeyWordId());
				if (StringUtils.isEmpty(keyName)) {
					logger.error("文章编号[{}]绑定的关键词[{}]在源表已经被删除,无法关联出其基本信息!",relation.getArticleId(),relation.getKeyWordId());
					continue;
				}
				relation.setKeyName(keyName);
			}
		}
		
		return datas;
	}
	@Override
	public void updateRelation(ArticleKeyWordRelationParent parent) {
		if (null != parent) {
			deleteRelation(parent.getArticleId());
			insertRelation(parent.getRelations());
		}
	}
	@Override
	public void insertRelation(List<ArticleKeyWordRelation> keys) {
		if (CollectionUtils.isNotEmpty(keys)) {
			Date date = new Date();
			for (ArticleKeyWordRelation bean : keys) {
				bean.setUpdatetime(DateFormatUtils.format(date, PATTERN));
			}
			mapper.insertRelation(keys);
		}
	}
	@Override
	public void deleteRelation(Integer articleId) {
		if (null != articleId) {
			mapper.deleteRelation(articleId);
		}
	}
	 
}
