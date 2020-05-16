 
package com.codinggyd.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codinggyd.bean.SiteInformation;
import com.codinggyd.service.ISiteInformationService;

/**
 * 
 * 
 * @Title: SiteController.java
 * @Package: com.codinggyd.controller
 * @Description: 网站站点信息操作相关接口
 * 
 * @Author: guoyd
 * @Date: 2019年2月21日 下午5:44:22
 *
 * Copyright @ 2019 Corpration Name
 */
@Controller
public class SiteController {
	@Autowired
	private ISiteInformationService siteInformationService;

	final Logger logger = LoggerFactory.getLogger(getClass());
 
	/**
	 * 查询站点信息
	 * @return
	 */
	@RequestMapping("/siteinfo")
	public @ResponseBody SiteInformation getsiteinfo() {
		return siteInformationService.getSiteInformation();
	}

	@RequestMapping("/pwd")
	public String getPwd() {
		return "lvqi";
	}

}
