package com.codinggyd.RookiePalmSpaceServer.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@RequestMapping("/login")
	public String login(){
		return "login success"; 
	}
	
 
}
