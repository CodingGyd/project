 
package com.codinggyd.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codinggyd.bean.SiteInformation;
import com.codinggyd.service.ISiteInformationService;
 
 
@Controller
public class SiteController {
	@Autowired
	private ISiteInformationService siteInformationService;

	final Logger logger = LoggerFactory.getLogger(getClass());
 
	 	//文章详情
	@RequestMapping("/siteinfo")
	public @ResponseBody SiteInformation getsiteinfo() {
		return siteInformationService.getSiteInformation();
	}
  
}
