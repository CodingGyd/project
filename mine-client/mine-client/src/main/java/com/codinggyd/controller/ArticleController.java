 
package com.codinggyd.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.codinggyd.bean.Article;
import com.codinggyd.bean.ArticlePageBean;
import com.codinggyd.bean.ArticleType;
import com.codinggyd.bean.MinePageBean;
import com.codinggyd.bean.Paginator;
import com.codinggyd.service.IArticleService;
import com.codinggyd.util.SysConstant;
 
 
@Controller
public class ArticleController {
	@Autowired
	private IArticleService articleService;

	final Logger logger = LoggerFactory.getLogger(getClass());
 
	//分页获取文章列表
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

		Paginator paginator = new Paginator(Integer.parseInt(page),Integer.parseInt(limit),Integer.MAX_VALUE);
		
		ArticlePageBean<Article> data = articleService.getArticleList(paginator,type_dm);
	 
		return data;
	}
	
	//最新文章
	@RequestMapping(value={"/latest_article"})
	public @ResponseBody MinePageBean<Article> latestArticle(HttpServletRequest request,HttpServletResponse response) {
		MinePageBean<Article> data = articleService.getLatestArticleList();
		return data;
	}
	
	//文章详情
	@RequestMapping("/article_dt/{id}")
	public String article_dt(@PathVariable String id,Map<String,Object> model) {

		Article article = articleService.findArticleDetail(id);
		if (null != article) {
			model.put("article",article);
		}
		
		return "v2/article_dt";
	}
	
	//文章分类
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
  
}
