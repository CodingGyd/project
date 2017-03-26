 
package com.codinggyd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
 
@Controller
@RequestMapping("/")
public class MineController {
 
	@RequestMapping("")
	public String index() {
		return "index";
	}


}
