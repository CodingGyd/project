 
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
 
 
@Controller
public class KeywordController {
	@Autowired
	private IKeywordsService keywordsService;

	final Logger logger = LoggerFactory.getLogger(getClass());
 
	//关键字列表
	@RequestMapping(value={"/keywords"})
	public @ResponseBody List<Keywords> keywords(HttpServletRequest request,HttpServletResponse response) {
		return keywordsService.listKeywords();
	}
}
