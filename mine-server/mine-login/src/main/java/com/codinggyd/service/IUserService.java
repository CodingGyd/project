package com.codinggyd.service;

import com.codinggyd.annotation.MineMethod;

/**
 * 
 * 
 * @Title:  UserService.java
 * @Package: com.codinggyd.service
 * @Description:  用户登录
 *
 * @author: guoyd
 * @Date: 2017年1月15日上午12:23:47
 *
 * Copyright @ 2017 Corpration Name
 */

public interface IUserService {
	@MineMethod("MINE_USER_LOGIN")
	public String login(String phone,String password);
}
