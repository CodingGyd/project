 
package com.codinggyd.controller;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codinggyd.bean.Article;
import com.codinggyd.bean.ArticleKeyWordRelation;
import com.codinggyd.bean.ArticlePageBean;
import com.codinggyd.bean.ArticleType;
import com.codinggyd.bean.MinePageBean;
import com.codinggyd.bean.Paginator;
import com.codinggyd.bean.ResponseBean;
import com.codinggyd.service.IArticleService;
import com.codinggyd.util.SysConstant;
 
/**
 * 
 * 
 * @Title: ArticleController.java
 * @Package: com.codinggyd.controller
 * @Description: 文章信息操作相关接口层
 * 
 * @Author: guoyd
 * @Date: 2019年2月21日 下午5:43:27
 *
 * Copyright @ 2019 Corpration Name
 */
@Controller
public class ArticleController {
	
	@Autowired
	private IArticleService articleService;

	final Logger logger = LoggerFactory.getLogger(getClass());
 
	/**
	 * 分页获取文章列表-接口
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value={"/article_page"})
	public @ResponseBody ArticlePageBean<Article> page(HttpServletRequest request,HttpServletResponse response) {
		String page = request.getParameter("page");
		if (StringUtils.isEmpty(page)) {
			return null;
		}
		
		String limit = request.getParameter("limit");
		if (StringUtils.isEmpty(limit)) {
			return null;
		}
		String type_dm = request.getParameter("type_dm");
		if (StringUtils.isEmpty(type_dm) || type_dm.equals("undefined") ||type_dm.equals("null")) type_dm = null;

		String label_dm = request.getParameter("label_dm");
		if (StringUtils.isEmpty(label_dm) || label_dm.equals("undefined") ||label_dm.equals("null")) label_dm = null;

		Paginator paginator = new Paginator(Integer.parseInt(page),Integer.parseInt(limit),Integer.MAX_VALUE);
		
		ArticlePageBean<Article> data = articleService.getArticleList(paginator,type_dm,label_dm);
		

		String label_ms = request.getParameter("label_ms");
		if (null != data && StringUtils.isNotEmpty(label_ms)) {
			
			ArticleKeyWordRelation keyword = new ArticleKeyWordRelation();
			try {

				keyword.setKeyName(new String(label_ms.getBytes("ISO-8859-1"),"UTF-8"));
			} catch (UnsupportedEncodingException e) {
				logger.error("解码出错,{}",e);
			}
			data.setKeyWordRelation(keyword);
		}
		return data;
	}
	
	/**
	 * 最新文章-接口
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value={"/latest_article"})
	public @ResponseBody MinePageBean<Article> latestArticle(HttpServletRequest request,HttpServletResponse response) {
		MinePageBean<Article> data = articleService.getLatestArticleList();
		return data;
	}
	/**
	 * 点击排行前10的文章-接口
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value={"/rank_article"})
	public @ResponseBody List<Article> rankArticle(HttpServletRequest request,HttpServletResponse response) {
		return articleService.getRankArticleList(10);
	}
	/**
	 * 文章详情-接口
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/article_dt/{id}")
	public String article_dt(@PathVariable String id,Map<String,Object> model) {

		Article article = articleService.findArticleDetail(id);
		if (null != article) {
			model.put("article",article);
		}
		
		return "v2/article_dt";
	}
	
	/**
	 * 文章分类-接口
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value={"/article_types"})
	public @ResponseBody List<ArticleType> listTypes(HttpServletRequest request,HttpServletResponse response) {
		List<ArticleType> articleTypes = articleService.findArticleTypes(Arrays.asList(SysConstant.ARTICLE_CONST_LB),null);
		Collections.sort(articleTypes, new Comparator<ArticleType>() {

			@Override
			public int compare(ArticleType o1, ArticleType o2) {
				if (StringUtils.isEmpty(o1.getDm())) {
					return -1;
				}
				if (StringUtils.isEmpty(o2.getDm())) {
					return 1;
				}
				return o1.getDm().compareTo(o2.getDm());
			}
		});
		return articleTypes;
	}
	/**
	 * 文章点赞-接口
	 * @param id
	 * @return
	 */
	@RequestMapping(value={"/dopraise"})
	public @ResponseBody ResponseBean dopraise(@RequestParam("id") Integer id) {
		String msg = articleService.doPraise(id, null);
		ResponseBean result = new ResponseBean();
		result.setCode(100);
		result.setMsg("谢谢支持~");
		return result;
	}
  
	/**
	 * 点击排行前10的文章
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value={"/article_search"})
	public @ResponseBody List<Article> searcharticle(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
		String condition = request.getParameter("searchcontent");
		if (null != condition) {
			condition = new String(condition.getBytes("iso-8859-1"),"UTF-8");
			return articleService.getSearchArticleList(condition);
		} else {
			return null;
		}
	}
	
}
