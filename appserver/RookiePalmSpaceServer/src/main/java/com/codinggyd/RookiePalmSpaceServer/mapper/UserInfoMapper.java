package com.codinggyd.RookiePalmSpaceServer.mapper;


import java.util.Date;

import org.apache.ibatis.annotations.Param;

import com.codinggyd.RookiePalmSpaceServer.bean.UserInfo;

/**
 * 
 * @author guoyading
 *
 * create at 2016年8月20日 下午5:31:47
 */
public interface UserInfoMapper {
	public UserInfo findUser(@Param("phone") String phone,@Param("password") String password);
	
	public void addLoginCount(@Param("phone") String phone,@Param("curtime") Date date);
	
	public int addUser(@Param("user") UserInfo userinfo );
	
	public int getNewId();
}
