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
	
	@Override
	public Article findArticleDetail(String id) {
		return getServerArticleDetail(id);
	}
	
	public MinePageBean<Article> getArticleList(Paginator paginator) {
  	
		//加载博文列表
		List<Article> articles = new ArrayList<>();
		articles.addAll(getServerArticleList());
		
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
	 * 加载文章列表
	 * @return
	 */
	private List<Article> getServerArticleList() {
		String requestJson = "{\"serviceId\":\"MINE_ARTICLE_LIST\",\"params\":[null]}";

		String responseData = HttpClientUtil.sendPost2(SysConstant.SERVER_URL, requestJson);
 	 
		List<Article> result = new ArrayList<>();
		if (StringUtils.isEmpty(responseData)) {
			logger.error("接口MINE_ARTICLE_LIST返回数据为空");
 		} else {
 			
 			ObjectMapper mapper = new ObjectMapper();
 			try {
				JsonNode node = mapper.readTree(responseData);
				String code = node.get("code").asText();
				if (SysConstant.RESPONSE_CODE_SUCCESS.equals(code)) {
					JsonNode resultJson = node.get("data").get(0);
					
					if (null == resultJson) {
						logger.error("接口MINE_ARTICLE_LIST返回数据为空");
					} else {
						//解析json格式数据
						int size = resultJson.size();
						for (int i=0;i<size;i++) {
							JsonNode temp = resultJson.get(i);
							Integer id = temp.get("id").asInt(0);
							String title = temp.get("title").asText("");
							Integer readingcount = temp.get("readingcount").asInt(0);
							String updatetime = temp.get("updatetime").asText("");
							String descs = temp.get("descs").asText("");
							String type = temp.get("type").asText("");
							Article article = new Article();
							article.setId(id);
							article.setTitle(title);
							article.setReadingcount(readingcount);
							article.setUpdatetime(formatDateStr(updatetime));
							article.setDescs(descs);
							article.setType(type);
							result.add(article);
						}
					}
					
				} else {
					logger.error("接口MINE_ARTICLE_LIST错误,响应码{}",code);
				}
			} catch (Exception e) {
				logger.error("接口MINE_ARTICLE_LIST返回数据有误,{},{}",responseData,e);
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
	
	
	/**
	 * 加载文章详情
	 * @return
	 */
	private Article getServerArticleDetail(String id) {
		String requestJson = "{\"serviceId\":\"MINE_ARTICLE_DETAIL\",\"params\":["+id+"]}";

		String responseData = HttpClientUtil.sendPost2(SysConstant.SERVER_URL, requestJson);
 	 
		Article result = null;
		if (StringUtils.isEmpty(responseData)) {
			logger.error("接口MINE_ARTICLE_DETAIL返回数据为空");
 		} else {
 			
 			ObjectMapper mapper = new ObjectMapper();
 			try {
				JsonNode node = mapper.readTree(responseData);
				String code = node.get("code").asText();
				if (SysConstant.RESPONSE_CODE_SUCCESS.equals(code)) {
					JsonNode temp = node.get("data").get(0);
					
					if (null == temp) {
						logger.error("接口MINE_ARTICLE_DETAIL返回数据为空");
					} else {
						//解析json格式数据
					  
 							String title = temp.get("title").asText("");
							Integer readingcount = temp.get("readingcount").asInt(0);
							String updatetime = temp.get("updatetime").asText("");
							String descs = temp.get("descs").asText("");
							String type = temp.get("type").asText("");
							String htmlContent = temp.get("htmlContent").asText("");
							result = new Article();
							result.setId(Integer.parseInt(id));
							result.setTitle(title);
							result.setReadingcount(readingcount);
							result.setUpdatetime(formatDateStr(updatetime));
							result.setDescs(descs);
							result.setType(type);
							result.setHtmlContent(htmlContent);
						}
					
				} else {
					logger.error("接口MINE_ARTICLE_DETAIL错误,响应码{}",code);
				}
			} catch (Exception e) {
				logger.error("接口MINE_ARTICLE_DETAIL返回数据有误,{},{}",responseData,e);
			}
 			
 		}
		
		return result;
	}
	 
}
