package com.codinggyd.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.codinggyd.bean.Article;
import com.codinggyd.bean.ArticleType;
import com.codinggyd.bean.MinePageBean;
import com.codinggyd.bean.PageList;
import com.codinggyd.bean.Paginator;
import com.codinggyd.service.IArticleService;
import com.codinggyd.util.HttpClientUtil;
import com.codinggyd.util.PageUtils;
import com.codinggyd.util.SysConstant;
import com.fasterxml.jackson.core.JsonProcessingException;
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
	
	private static final String SERVER_LATEST_ARTICLE_LIST="MINE_LATEST_ARTICLE";//数据接口地址-最新文章
	private static final String SERVER_ARTICLE_LIST="MINE_ARTICLE_LIST";//数据接口地址-文章列表
	private static final String SERVER_ARTICLE_DETAIL="MINE_ARTICLE_DETAIL";//数据接口地址-文章详情
	private static final String SERVER_ARTICLE_TYPE="MINE_CONST";//数据接口地址-文章分类
	
	private static ObjectMapper mapper = new ObjectMapper();
	@Override
	public Article findArticleDetail(String id) {
		return getServerArticleDetail(id);
	}
	
	public MinePageBean<Article> getArticleList(Paginator paginator,String type_dm) {
  	
		//加载博文列表
		List<Article> articles = new ArrayList<>();
		articles.addAll(getServerArticleList(type_dm,null));
		
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
	
	@Override
	public MinePageBean<Article> getLatestArticleList() {
 		//加载最新文章
		List<Article> articles = new ArrayList<>();
		articles.addAll(getServerLatestArticleList());
		
		if (CollectionUtils.isEmpty(articles)) {
			logger.error("获取最新文章数据为空!");
			return null;
		}
		return new MinePageBean<Article>(null,articles);
	}
	
	/**
	 * 加载最新文章
	 * @return
	 */
	private List<Article> getServerLatestArticleList() {
		List<Article> result = new ArrayList<>();

		String responseData = requestServer(SERVER_LATEST_ARTICLE_LIST, 5);
 	 
		if (StringUtils.isEmpty(responseData)) {
			logger.error("接口[{}]返回数据为空",SERVER_LATEST_ARTICLE_LIST);
 		} else {
 		
 			try {
				JsonNode node = mapper.readTree(responseData);
				String code = node.get("code").asText();
				if (SysConstant.RESPONSE_CODE_SUCCESS.equals(code)) {
					JsonNode resultJson = node.get("data").get(0);
					
					if (null == resultJson) {
						logger.error("接口[{}]返回数据为空",SERVER_LATEST_ARTICLE_LIST);
					} else {
						//解析json格式数据
						int size = resultJson.size();
						for (int i=0;i<size;i++) {
							JsonNode temp = resultJson.get(i);
							result.add(formatArticleBean(temp));
						}
					}
					
				} else {
					logger.error("接口[{}]错误,响应码{}",SERVER_LATEST_ARTICLE_LIST,code);
				}
			} catch (Exception e) {
				logger.error("接口[{}]返回数据有误,{},{}",SERVER_LATEST_ARTICLE_LIST,responseData,e);
			}
 			
 		}
		
		return result;
	}
	
	/**
	 * 加载文章列表
	 * @param type_dm 文章分类代码
	 * @param pageInfo 分页信息
	 * @return
	 */
	private List<Article> getServerArticleList(String type_dm,String[] pageInfo) {
		List<Article> result = new ArrayList<>();
		
		String responseData = requestServer(SERVER_ARTICLE_LIST, type_dm,pageInfo);
 	 
		if (StringUtils.isEmpty(responseData)) {
			logger.error("接口[{}]返回数据为空",SERVER_ARTICLE_LIST);
 		} else {
 		
 			try {
				JsonNode node = mapper.readTree(responseData);
				String code = node.get("code").asText();
				if (SysConstant.RESPONSE_CODE_SUCCESS.equals(code)) {
					JsonNode resultJson = node.get("data").get(0);
					
					if (null == resultJson) {
						logger.error("接口[{}]返回数据为空",SERVER_ARTICLE_LIST);
					} else {
						//解析json格式数据
						int size = resultJson.size();
						for (int i=0;i<size;i++) {
							JsonNode temp = resultJson.get(i);
							result.add(formatArticleBean(temp));
						}
					}
					
				} else {
					logger.error("接口[{}]错误,响应码{}",SERVER_ARTICLE_LIST,code);
				}
			} catch (Exception e) {
				logger.error("接口[{}]返回数据有误,{},{}",SERVER_ARTICLE_LIST,responseData,e);
			}
 			
 		}
		
		return result;
	}
	
	/**
	 * 加载文章详情
	 * @return
	 */
	private Article getServerArticleDetail(String id) {

		Article result = null;
		String responseData = requestServer(SERVER_ARTICLE_DETAIL, id);
	 
		if (StringUtils.isEmpty(responseData)) {
			logger.error("接口[{}]返回数据为空",SERVER_ARTICLE_DETAIL);
 		} else {
 			
 			ObjectMapper mapper = new ObjectMapper();
 			try {
				JsonNode node = mapper.readTree(responseData);
				String code = node.get("code").asText();
				if (SysConstant.RESPONSE_CODE_SUCCESS.equals(code)) {
					JsonNode temp = node.get("data").get(0);
					
					if (null == temp) {
						logger.error("接口[{}]返回数据为空",SERVER_ARTICLE_DETAIL);
					} else {
						//解析json格式数据
							result =formatArticleBean(temp);
						}
					
				} else {
					logger.error("接口[{}]错误,响应码{}",SERVER_ARTICLE_DETAIL,code);
				}
			} catch (Exception e) {
				logger.error("接口[{}]返回数据有误,{},{}",SERVER_ARTICLE_DETAIL,responseData,e);
			}
 			
 		}
		
		return result;
	}
	/**
	 * 加载文章分类
	 * @return
	 */
	@Override
	public List<ArticleType> findArticleTypes() {
		List<ArticleType> result = new ArrayList<>();

		List<Object> params = new ArrayList<>();
		
		List<String> lbs = new ArrayList<>();
		lbs.add("100");
		params.add(lbs);
		
		Map<String,Object> dataMap = new HashMap<>();
		dataMap.put("serviceId", SERVER_ARTICLE_TYPE);
		dataMap.put("params", params);
		
		String requestJson = null;
		try {
			requestJson = mapper.writeValueAsString(dataMap);
		} catch (JsonProcessingException e1) {
			logger.error("格式化请求参数出错,");
			return result; 
		}

		String responseData = HttpClientUtil.sendPost2(SysConstant.SERVER_URL, requestJson);
 	 
		if (StringUtils.isEmpty(responseData)) {
			logger.error("接口[{}]返回数据为空",SERVER_ARTICLE_TYPE);
 		} else {
 			
 		
 			try {
				JsonNode node = mapper.readTree(responseData);
				String code = node.get("code").asText();
				if (SysConstant.RESPONSE_CODE_SUCCESS.equals(code)) {
					JsonNode resultJson = node.get("data").get(0);
					
					if (null == resultJson) {
						logger.error("接口[{}]返回数据为空",SERVER_ARTICLE_TYPE);
					} else {
						//解析json格式数据
						int size = resultJson.size();
						for (int i=0;i<size;i++) {
							JsonNode temp = resultJson.get(i);
							Integer id = temp.get("id").asInt(0);
							String lb = temp.get("lb").asText("");
							String lbmc = temp.get("lbmc").asText("");
							String dm = temp.get("dm").asText("");
							String ms = temp.get("ms").asText("");
							ArticleType types = new ArticleType();
							types.setId(id);
							types.setLb(lb);
							types.setLbmc(lbmc);
							types.setDm(dm);
							types.setMs(ms);
							result.add(types);
						}
					}
					
				} else {
					logger.error("接口[{}]错误,响应码{}",SERVER_ARTICLE_TYPE,code);
				}
			} catch (Exception e) {
				logger.error("接口[{}]返回数据有误,{},{}",SERVER_ARTICLE_TYPE,responseData,e);
			}
 			
 		}
		
		return result;
	}
	
	private String requestServer(String serviceId,Object...args) {
		List<Object> params = new ArrayList<>();
		
		if (null != args) {
			for (int i=0;i<args.length;i++) {
				params.add(args[i]);
			}
		}
		
		Map<String,Object> dataMap = new HashMap<>();
		dataMap.put("serviceId", serviceId);
		dataMap.put("params", params);
		
		String requestJson = null;
		try {
			requestJson = mapper.writeValueAsString(dataMap);
		} catch (JsonProcessingException e1) {
			logger.error("格式化请求参数出错,");
			return null; 
		}
		return HttpClientUtil.sendPost2(SysConstant.SERVER_URL, requestJson);
	}
	
	private Article formatArticleBean(JsonNode jsonNode) {
		Integer id = jsonNode.get("id").asInt(0);
		String title = jsonNode.get("title").asText("");
		Integer readingcount = jsonNode.get("readingcount").asInt(0);
		String updatetime = jsonNode.get("updatetime").asText("");
		String descs = jsonNode.get("descs").asText("");
		String type = jsonNode.get("type").asText("");
		String typeName = jsonNode.get("typeName").asText("");
		String htmlContent = jsonNode.get("htmlContent").asText("");
		String content = jsonNode.get("content").asText("");
		
		Article article = new Article();
		article.setId(id);
		article.setTitle(title);
		article.setReadingcount(readingcount);
		article.setUpdatetime(formatDateStr(updatetime));
		article.setDescs(descs);
		article.setType(type);
		article.setTypeName(typeName);
		article.setHtmlContent(htmlContent);
		article.setContent(content);
		return article;
		
	}
	 
	private String formatDateStr(String time) {
		return !StringUtils.isEmpty(time) && time.length() >= 10 ? time.substring(0,10) : ""; 
	}

}
