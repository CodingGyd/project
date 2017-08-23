 
package com.codinggyd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
 
@Controller
public class MineController {
	//首页
	@RequestMapping("/")
	public String index() {
		return "index";
	}
	//首页
	@RequestMapping("/index")
	public String index2() {
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
