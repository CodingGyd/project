package com.codinggyd.mapper;

import org.apache.ibatis.annotations.Param;

import com.codinggyd.bean.UserInfo;
public interface UserInfoMapper {
	public UserInfo findUser(@Param("phone") String phone,@Param("password") String password);
}
