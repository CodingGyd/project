package com.codinggyd.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.codinggyd.bean.Article;
import com.codinggyd.bean.MinePageBean;
import com.codinggyd.bean.PageList;
import com.codinggyd.bean.Paginator;
import com.codinggyd.service.IArticleService;
import com.codinggyd.util.HttpClientUtil;
import com.codinggyd.util.PageUtils;
import com.codinggyd.util.SysConstant;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

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
	
	private  static final String PATTERN = "yyyy-MM-dd";
	
	public MinePageBean<Article> getArticleList(Paginator paginator) {
  	
		//学习网站相关博文
		List<Article> articles = new ArrayList<>();
		articles.addAll(getServerLearnSite());
		articles.addAll(getServerUtilFunction());
		
		if (CollectionUtils.isEmpty(articles)) {
			logger.error("获取文章数据为空!");
			return null;
		}
		int total = articles.size();

		List<Article> pageArticles = PageUtils.paging(articles, paginator);
		Paginator paginatorReturn = new Paginator(paginator.getPage(),paginator.getLimit(),total);
		if (CollectionUtils.isEmpty(pageArticles)) {
			logger.error("分页获取文章数据为空!");
			return new MinePageBean<Article>(paginatorReturn,new PageList<>());
		} else {
			return new MinePageBean<Article>(paginatorReturn,pageArticles);
		}
		 
	}
	
	
	/**
	 * 加载学习网站数据
	 * @return
	 */
	private List<Article> getServerLearnSite() {
		String requestJson = "{\"serviceId\":\"MINE_LIST_LEARN_SITE\",\"params\":[null]}";

		String responseData = HttpClientUtil.sendPost2(SysConstant.SERVER_URL, requestJson);
 	 
		List<Article> result = new ArrayList<>();
		if (StringUtils.isEmpty(responseData)) {
			logger.error("接口MINE_LIST_LEARN_SITE返回数据为空");
 		} else {
 			
 			ObjectMapper mapper = new ObjectMapper();
 			try {
				JsonNode node = mapper.readTree(responseData);
				String code = node.get("code").asText();
				if (SysConstant.RESPONSE_CODE_SUCCESS.equals(code)) {
					JsonNode resultJson = node.get("data").get(0);
					
					if (null == resultJson) {
						logger.error("接口MINE_LIST_LEARN_SITE返回数据为空");
					} else {
						//解析json格式数据
						int size = resultJson.size();
						for (int i=0;i<size;i++) {
							JsonNode temp = resultJson.get(i);
							Integer id = temp.get("id").asInt();
							String title = temp.get("title").asText();
							Integer readingcount = temp.get("readingcount").asInt();
							String updatetime = temp.get("updatetime").asText();
							String descs = temp.get("descs").asText();
							
							Article article = new Article();
							article.setId(id);
							article.setTitle(title);
							article.setReadingcount(readingcount);
							article.setUpdatetime(formatDateStr(updatetime));
							article.setDescs(descs);
							result.add(article);
						}
					}
					
				} else {
					logger.error("接口MINE_LIST_LEARN_SITE错误,响应码{}",code);
				}
			} catch (Exception e) {
				logger.error("接口MINE_LIST_LEARN_SITE返回数据有误,{},{}",responseData,e);
			}
 			
 		}
		
		return result;
	}
 
	/**
	 * 加载功能函数数据
	 * @return
	 */
	private List<Article> getServerUtilFunction() {
		String requestJson = "{\"serviceId\":\"MINE_UTIL_FUNCTION\",\"params\":[null]}";

		String responseData = HttpClientUtil.sendPost2(SysConstant.SERVER_URL, requestJson);
 	 
		List<Article> result = new ArrayList<>();
		if (StringUtils.isEmpty(responseData)) {
			logger.error("接口MINE_UTIL_FUNCTION返回数据为空");
 		} else {
 			
 			ObjectMapper mapper = new ObjectMapper();
 			try {
				JsonNode node = mapper.readTree(responseData);
				String code = node.get("code").asText();
				if (SysConstant.RESPONSE_CODE_SUCCESS.equals(code)) {
					JsonNode resultJson = node.get("data").get(0);
					
					if (null == resultJson) {
						logger.error("接口MINE_UTIL_FUNCTION返回数据为空");
					} else {
						//解析json格式数据
						int size = resultJson.size();
						for (int i=0;i<size;i++) {
							JsonNode temp = resultJson.get(i);
							Integer id = temp.get("id").asInt();
							String title = temp.get("title").asText();
							Integer readingcount = temp.get("readingcount").asInt();
							String updatetime = temp.get("updatetime").asText();
							String content = temp.get("content").asText();
							
							Article article = new Article();
							article.setId(id);
							article.setTitle(title);
							article.setReadingcount(readingcount);
							article.setUpdatetime(formatDateStr(updatetime));
							article.setDescs(content);
							result.add(article);
						}
					}
					
				} else {
					logger.error("接口MINE_UTIL_FUNCTION错误,响应码{}",code);
				}
			} catch (Exception e) {
				logger.error("接口MINE_UTIL_FUNCTION返回数据有误,{},{}",responseData,e);
			}
 			
 		}
		
		return result;
	}

	private String formatDateStr(String time) {
		try {
			
			return DateFormatUtils.format(Long.parseLong(time), PATTERN, Locale.CHINESE);
		} catch (Exception e) {
			logger.error("日期字段格式化出错,{}",e);
		}
		return null;
	}
	 
}
