package com.codinggyd.RookiePalmSpaceServer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codinggyd.RookiePalmSpaceServer.service.UserInfoService;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserInfoService service;

	@RequestMapping(value = "/login", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String login(@RequestParam("phone") String phone,
			@RequestParam("password") String password) {
		String result = service.login(phone, password);
		return result;

	}

	@RequestMapping(value = "/register", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String register(@RequestParam("phone") String phone,
			@RequestParam("password") String password,
			@RequestParam("sex") String sex) {
		String result = service.register(phone, password,sex);
		return result;

	}
}
