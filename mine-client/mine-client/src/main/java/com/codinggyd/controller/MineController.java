 
package com.codinggyd.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.DateUtils;

import com.codinggyd.bean.Article;
import com.codinggyd.bean.LearnSite;
import com.codinggyd.bean.UtilFunction;
import com.codinggyd.service.ILearnSiteService;
import com.codinggyd.service.IUtilFunctionService;
import com.fasterxml.jackson.databind.ObjectMapper;
 
 
@Controller
public class MineController {
	@Autowired
	private IUtilFunctionService utilService;
	final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private ILearnSiteService itinfoService;
	
	@RequestMapping(value= {"/data/utilfunction"},produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String utilfunction() {
		return utilService.getUtilFunction();
	}
	
	@RequestMapping(value= {"/data/itinfo"},produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String itinfo() {
		return itinfoService.listLearnSite();
	}
	
 
	//首页
	@RequestMapping(value={"/","/index"})
	public String index(Map<String,Object> model) {
		String learnsiteJson = itinfoService.listLearnSite();
		String utilJson = utilService.getUtilFunction();

		ObjectMapper objectmapper =new ObjectMapper();
		
		List<LearnSite> learnSites = null;
		try {
			learnSites = objectmapper.readValue(learnsiteJson, objectmapper.getTypeFactory().constructParametricType(List.class, LearnSite.class));
		} catch (Exception e) {
			logger.error("format error ,{}",e);
		}
		
		List<UtilFunction> utilFunctions = null;
		try {
			utilFunctions = objectmapper.readValue(utilJson, objectmapper.getTypeFactory().constructParametricType(List.class, UtilFunction.class));
		} catch (Exception e) {
			logger.error("format error ,{}",e);
		}
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		List<Article> list2 = new ArrayList<>(); 
		if (!CollectionUtils.isEmpty(learnSites)) {
			for (LearnSite site : learnSites) {
				Article article = new Article();
				article.setId(site.getId());
				article.setTitle(site.getTitle());
				article.setContent(site.getContent());
				article.setUpdatetime(format.format(site.getUpdatetime()));
				list2.add(article);
			}
		}
		
		if (!CollectionUtils.isEmpty(utilFunctions)) {
			for (UtilFunction site : utilFunctions) {
				Article article = new Article();
				article.setId(site.getId());
				article.setTitle(site.getTitle());
				article.setContent(site.getContent());
				article.setUpdatetime(format.format(site.getUpdatetime()));
				list2.add(article);
			}
		}
		model.put("articleList",list2);  
		return "index";
	}
	 
	//编程文章
	@RequestMapping("/tarticle")
	public String tarticle() {
		return "tarticle";
	}
	//留言板
	@RequestMapping("/guestbook")
	public String guestbook() {
		return "guestbook";
	}
	//关于我
	@RequestMapping("/about")
	public String about() {
		return "about";
	}
	
	//相册
	@RequestMapping("/xc")
	public String xc() {
		return "xc";
	}
	//碎言碎语
	@RequestMapping("/shuo")
	public String shuo() {
		return "shuo";
	}
}
