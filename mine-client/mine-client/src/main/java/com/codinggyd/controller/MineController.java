 
package com.codinggyd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
 
@Controller
@RequestMapping("/v1_1")
public class MineController {
	
	//首页-v1.1
	@RequestMapping("/index")
	public String index2() {
		return "v1_1/index";
	}
	
	//疯言疯语-v1.1
	@RequestMapping("/shuo")
	public String shuo() {
		return "v1_1/shuo";
	}
	
	//旅游相册-v1.1
	@RequestMapping("/xc")
	public String xc() {
		return "v1_1/xc";
	}
	
	//关于我-v1.1
	@RequestMapping("/about")
	public String about() {
		return "v1_1/about";
	}
	
	//留言板-v1.1
	@RequestMapping("/guestbook")
	public String guestbook() {
		return "v1_1/guestbook";
	}
	
	//留言板-v1.1
	@RequestMapping("/tarticle")
	public String tarticle() {
		return "v1_1/tarticle";
	}
 

}
