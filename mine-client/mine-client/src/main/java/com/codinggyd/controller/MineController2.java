 
package com.codinggyd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
 
@Controller
@RequestMapping("/")
public class MineController2 {
 
	//首页-v1.0
	@RequestMapping("")
	public String index() {
		return "index";
	}
	
	 

}
