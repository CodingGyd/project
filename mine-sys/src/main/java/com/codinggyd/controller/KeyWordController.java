 
package com.codinggyd.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codinggyd.bean.DataTable;
import com.codinggyd.bean.KeyWord;
import com.codinggyd.constant.AppConfig;
import com.codinggyd.redis.RedisClientUtils;
import com.codinggyd.service.IKeyWordService;
 
/**
 * 
 * @Title:  KeyWordController.java
 * @Package: com.codinggyd.controller
 * @Description: 关键词管理接口
 *
 * @author: guoyd
 * @Date: 2017年11月5日 上午11:50:58
 *
 * Copyright @ 2017 Corpration Name
 */
@Controller
@RequestMapping("sys/keyword")
public class KeyWordController {
	
	@Qualifier("keyWordServiceImpl")
	@Autowired
	private IKeyWordService service;
	
	final Logger logger = LoggerFactory.getLogger(getClass());
	private static final String PATTERN = "yyyy-MM-dd HH:mm.ss.SSS";

	@RequestMapping(value="/keyword_byid",method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody KeyWord getDailySingle(HttpServletRequest request,HttpServletResponse response) {
		Integer id = Integer.parseInt(request.getParameter("id"));
		KeyWord keyword = service.queryKeyWords(id);
		return keyword;
	}
	
	@RequestMapping(value="/keywordlist",method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody DataTable<KeyWord> getDailysList(HttpServletRequest request,HttpServletResponse response) {
		
		DataTable<KeyWord> keywordTable = new DataTable<KeyWord>();
		keywordTable.setTotal(0);
		List<KeyWord> keywords = service.getKeyWords(null);
		if (CollectionUtils.isNotEmpty(keywords)) {
			keywordTable.setRows(keywords);
			keywordTable.setTotal(keywords.size());
		}
 
 		return keywordTable;
	}
	
	@RequestMapping(value="/keywordlist2",method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody List<KeyWord> getDailysList2(HttpServletRequest request,HttpServletResponse response) {
 		return service.getKeyWords(null);
	}
	
	@RequestMapping(value="/insert",method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody String insert(HttpServletRequest request,HttpServletResponse response) {
		
		KeyWord key = new KeyWord();
		key.setName(request.getParameter("name"));
		key.setUpdatetime(DateFormatUtils.format(new Date(), PATTERN));
		service.insertKeyWords(key);
		return "success";
	}
 
	@RequestMapping(value="/update",method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody String update(@RequestBody KeyWord key) {
		 
		key.setUpdatetime(DateFormatUtils.format(new Date(), PATTERN));
		service.updateKeyWords(key);
 		RedisClientUtils.deleteFromCache(AppConfig.getRedis_key_keyword()+key.getId());
		return "success";
	}

	@RequestMapping(value="/delete",method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody String delete(Integer id) {
		service.deleteKeyWords(id);
 		RedisClientUtils.deleteFromCache(AppConfig.getRedis_key_keyword()+id);
		return "success";
	}
}
