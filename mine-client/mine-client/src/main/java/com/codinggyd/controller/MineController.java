 
package com.codinggyd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codinggyd.service.ILearnSiteService;
import com.codinggyd.service.IUtilFunctionService;
 
@Controller
public class MineController {
	@Autowired
	private IUtilFunctionService utilService;
	
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
	public String index() {
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
