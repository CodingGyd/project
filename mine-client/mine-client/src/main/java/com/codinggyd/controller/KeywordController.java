 
package com.codinggyd.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codinggyd.bean.Keywords;
import com.codinggyd.service.IKeywordsService;
 
 /**
  * 
  * 
  * @Title: KeywordController.java
  * @Package: com.codinggyd.controller
  * @Description: 文章关键字操作相关接口层
  * 
  * @Author: guoyd
  * @Date: 2019年2月21日 下午5:44:07
  *
  * Copyright @ 2019 Corpration Name
  */
@Controller
public class KeywordController {
	@Autowired
	private IKeywordsService keywordsService;

	final Logger logger = LoggerFactory.getLogger(getClass());
 
	/**
	 * 关键字列表-接口
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value={"/keywords"})
	public @ResponseBody List<Keywords> keywords(HttpServletRequest request,HttpServletResponse response) {
		return keywordsService.listKeywords();
	}
}
