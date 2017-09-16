 
package com.codinggyd.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.codinggyd.bean.Article;
import com.codinggyd.service.IArticleService;
 
 
@Controller
public class MineController {
	@Autowired
	private IArticleService service;
	final Logger logger = LoggerFactory.getLogger(getClass());

	@RequestMapping("/update")
	public String update(HttpServletRequest request,HttpServletResponse response ,@RequestBody Article article) {
		return "update";
	}

  
}
