package com.codinggyd.RookiePalmSpaceServer.mapper;


import java.util.Date;

import org.apache.ibatis.annotations.Param;

import com.codinggyd.RookiePalmSpaceServer.bean.UserInfo;


public interface UserInfoMapper {
	public UserInfo findUser(@Param("phone") String phone,@Param("password") String password);
	
	public void addLoginCount(@Param("phone") String phone,@Param("curtime") Date date);
}
