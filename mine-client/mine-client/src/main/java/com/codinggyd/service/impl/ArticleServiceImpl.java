package com.codinggyd.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.codinggyd.bean.Article;
import com.codinggyd.bean.ArticlePageBean;
import com.codinggyd.bean.ArticleType;
import com.codinggyd.bean.PageList;
import com.codinggyd.bean.Paginator;
import com.codinggyd.service.IArticleService;
import com.codinggyd.util.HttpClientUtil;
import com.codinggyd.util.PageUtils;
import com.codinggyd.util.SysConstant;
import com.codinggyd.utils.CommonUtils;
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
	private static final String SERVER_RANDOM_ARTICLE_LIST="MINE_RANDOM_ARTICLE";//数据接口地址-随机文章
	private static final String SERVER_LATEST_ARTICLE_LIST="MINE_LATEST_ARTICLE";//数据接口地址-最新文章
	private static final String SERVER_ARTICLE_LIST="MINE_ARTICLE_LIST";//数据接口地址-文章列表
	private static final String SERVER_ARTICLE_LIST_RANK_TOP="MINE_ARTICLE_LIST_RANK_TOP";//数据接口地址-查询点击排行的文章列表

	private static final String SERVER_ARTICLE_DETAIL="MINE_ARTICLE_DETAIL";//数据接口地址-文章详情
	private static final String SERVER_ARTICLE_TYPE="MINE_CONST";//数据接口地址-文章分类
	private static final String SERVER_ARTICLE_UPDATE_READ_COUNT="MINE_ARTICLE_UPDATE_READ_COUNT";//数据接口地址-更新文章阅读数

	private static ObjectMapper mapper = new ObjectMapper();
	@Override
	public Article findArticleDetail(String id) {
		return getServerArticleDetail(id);
	}
	@Override
	public ArticlePageBean<Article> getArticleList(Paginator paginator,String type_dm,String label_dm) {
  	
		//加载文章分类信息
		List<ArticleType> articleTypes = findArticleTypes(Arrays.asList(SysConstant.ARTICLE_CONST_LB), type_dm);
		ArticleType articleType = null;
		if (!CollectionUtils.isEmpty(articleTypes)) {
			//当分类为文章汇总时，约定type_dm为空
			if (StringUtils.isEmpty(type_dm)) {
				for (ArticleType type : articleTypes ) {
					if (StringUtils.isEmpty(type.getDm())) {
						articleType = type;
						break;
					}
				}
			} else {
				articleType = articleTypes.get(0);
			}
		}
		if (null == articleType) {
			return null;
		}
		
		//加载博文列表
		List<Article> articles = new ArrayList<>();
		articles.addAll(getServerArticleList(type_dm,label_dm,null));
		
		if (CollectionUtils.isEmpty(articles)) {
			logger.error("获取文章数据为空!");
			return new ArticlePageBean<Article>(articleType,null,new PageList<>());
		}
		int total = articles.size();

		List<Article> pageArticles = PageUtils.paging(articles, paginator);
		Paginator paginatorReturn = new Paginator(paginator.getPage(),paginator.getLimit(),total);
		if (CollectionUtils.isEmpty(pageArticles)) {
			logger.error("分页获取文章数据为空!");
			return new ArticlePageBean<Article>(articleType,paginatorReturn,new PageList<>());
		} else {
			return new ArticlePageBean<Article>(articleType,paginatorReturn,pageArticles);
		}
		 
	}
	
	@Override
	public ArticlePageBean<Article> getLatestArticleList() {
 		//加载最新文章
		List<Article> articles = new ArrayList<>();
		articles.addAll(getServerLatestArticleList());
		
		if (CollectionUtils.isEmpty(articles)) {
			logger.error("获取最新文章数据为空!");
			return null;
		}
		return new ArticlePageBean<Article>(null,null,articles);
	}
	
	@Override
	public ArticlePageBean<Article> getRandomArticleList() {
 		//加载随机文章
		List<Article> articles = new ArrayList<>();
		articles.addAll(getServerRandomArticleList());
		
		if (CollectionUtils.isEmpty(articles)) {
			logger.error("获取随机文章数据为空!");
			return null;
		}
		return new ArticlePageBean<Article>(null,null,articles);
	}
	
	/**
	 * 加载随机文章
	 * @return
	 */
	private List<Article> getServerRandomArticleList() {
		List<Article> result = new ArrayList<>();

		String responseData = HttpClientUtil.requestServer(SERVER_RANDOM_ARTICLE_LIST, 10);
 	 
		if (StringUtils.isEmpty(responseData)) {
			logger.error("接口[{}]返回数据为空",SERVER_RANDOM_ARTICLE_LIST);
 		} else {
 		
 			try {
				JsonNode node = mapper.readTree(responseData);
				String code = node.get("code").asText();
				if (SysConstant.RESPONSE_CODE_SUCCESS.equals(code)) {
					JsonNode resultJson = node.get("data").get(0);
					
					if (null == resultJson) {
						logger.error("接口[{}]返回数据为空",SERVER_RANDOM_ARTICLE_LIST);
					} else {
						//解析json格式数据
						int size = resultJson.size();
						for (int i=0;i<size;i++) {
							JsonNode temp = resultJson.get(i);
							result.add(formatArticleBean(temp));
						}
					}
					
				} else {
					logger.error("接口[{}]错误,响应码{}",SERVER_RANDOM_ARTICLE_LIST,code);
				}
			} catch (Exception e) {
				logger.error("接口[{}]返回数据有误,{},{}",SERVER_RANDOM_ARTICLE_LIST,responseData,e);
			}
 			
 		}
		
		return result;
	}
	
	/**
	 * 加载最新文章
	 * @return
	 */
	private List<Article> getServerLatestArticleList() {
		List<Article> result = new ArrayList<>();
		
		String responseData = HttpClientUtil.requestServer(SERVER_LATEST_ARTICLE_LIST, 3);
 	 
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
	private List<Article> getServerArticleList(String type_dm,String label_dm,String[] pageInfo) {
		List<Article> result = new ArrayList<>();
		
		String responseData = HttpClientUtil.requestServer(SERVER_ARTICLE_LIST, type_dm,label_dm,pageInfo);
 	 
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
		String responseData = HttpClientUtil.requestServer(SERVER_ARTICLE_DETAIL, id);
	 
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
	public List<ArticleType> findArticleTypes(List<String> lbs,String dm) {
		List<ArticleType> result = new ArrayList<>();

		List<Object> params = new ArrayList<>();
		
		params.add(lbs);
		params.add(dm);
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

		String responseData = HttpClientUtil.sendPost(SysConstant.SERVER_URL, requestJson);
 	 
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
							dm = temp.get("dm").asText("");
							String ms = temp.get("ms").asText("");
							String remarks = temp.get("remarks").asText("");

							ArticleType types = new ArticleType();
							types.setId(id);
							types.setLb(lb);
							types.setLbmc(lbmc);
							types.setDm(dm);
							types.setMs(ms);
							types.setRemarks(remarks);
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
	
	private Article formatArticleBean(JsonNode jsonNode) throws Exception {
		ObjectMapper om = CommonUtils.getMappingInstance();
		return om.readValue(jsonNode.toString(), Article.class);
	}
	 
	private String formatDateStr(String time) {
		return !StringUtils.isEmpty(time) && time.length() >= 10 ? time.substring(0,10) : ""; 
	}

	@Override
	public void updateReadCount(Integer id) {
		String responseData = HttpClientUtil.requestServer(SERVER_ARTICLE_UPDATE_READ_COUNT, id);
		 
		if (StringUtils.isEmpty(responseData)) {
			logger.error("接口[{}]返回数据为空",SERVER_ARTICLE_UPDATE_READ_COUNT);
 		}  else {
 			logger.debug("调用文章阅读数量更新接口,返回接口{}",responseData);
 		}
	}
	@Override
	public List<Article> getRankArticleList(Integer rankTop) {
		return getServerArticleListOrderByClickCount(rankTop);
	}

	private List<Article> getServerArticleListOrderByClickCount(Integer rankTop) {
		List<Article> result = new ArrayList<>();
		
		String responseData = HttpClientUtil.requestServer(SERVER_ARTICLE_LIST_RANK_TOP, rankTop);
 	 
		if (StringUtils.isEmpty(responseData)) {
			logger.error("接口[{}]返回数据为空",SERVER_ARTICLE_LIST_RANK_TOP);
 		} else {
 		
 			try {
 				
				JsonNode node = mapper.readTree(responseData);
				String code = node.get("code").asText();
				if (SysConstant.RESPONSE_CODE_SUCCESS.equals(code)) {
					JsonNode resultJson = node.get("data").get(0);
					
					if (null == resultJson) {
						logger.error("接口[{}]返回数据为空",SERVER_ARTICLE_LIST_RANK_TOP);
					} else {
						//解析json格式数据
						int size = resultJson.size();
						for (int i=0;i<size;i++) {
							JsonNode temp = resultJson.get(i);
							result.add(formatArticleBean(temp));
						}
					}
					
				} else {
					logger.error("接口[{}]错误,响应码{}",SERVER_ARTICLE_LIST_RANK_TOP,code);
				}
			} catch (Exception e) {
				logger.error("接口[{}]返回数据有误,{},{}",SERVER_ARTICLE_LIST_RANK_TOP,responseData,e);
			}
 		}
		return result;
	}
}
