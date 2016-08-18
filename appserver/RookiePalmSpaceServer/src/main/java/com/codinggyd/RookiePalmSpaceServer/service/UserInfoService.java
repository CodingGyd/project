package com.codinggyd.RookiePalmSpaceServer.service;

import java.util.Date;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codinggyd.RookiePalmSpaceServer.bean.ResponseFlag;
import com.codinggyd.RookiePalmSpaceServer.bean.UserInfo;
import com.codinggyd.RookiePalmSpaceServer.mapper.UserInfoMapper;
import com.codinggyd.RookiePalmSpaceServer.util.TextUtils;

@Service
public class UserInfoService {
	@Autowired
	UserInfoMapper mapper;
	 
	
	public String login(String phone,String password){
		ResponseFlag responseFlag = new ResponseFlag();
		responseFlag.status ="";
		responseFlag.msg = "";
		if(TextUtils.isEmpty(phone) || TextUtils.isEmpty(password)){
			responseFlag.status ="failure";
			responseFlag.msg ="登录信息有误,请重新输入!";
		}else{

			UserInfo userinfo = mapper.findUser(phone, password);
			
			if(userinfo == null){
				responseFlag.status ="failure";
				responseFlag.msg ="账号或密码有误!";
			}else{
				//更新登录次数
				mapper.addLoginCount(phone,new Date());
			 
				responseFlag.status = "success";
				responseFlag.msg = JSONObject.wrap(userinfo).toString();
			}
		}
	
		return JSONObject.wrap(responseFlag).toString();
	}
	
}
