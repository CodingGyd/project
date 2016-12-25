package com.codinggyd.RookiePalmSpaceServer.service;

import java.util.Date;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codinggyd.RookiePalmSpaceServer.bean.ResponseFlag;
import com.codinggyd.RookiePalmSpaceServer.bean.UserInfo;
import com.codinggyd.RookiePalmSpaceServer.mapper.UserInfoMapper;
import com.codinggyd.RookiePalmSpaceServer.util.DateUtil;
import com.codinggyd.RookiePalmSpaceServer.util.TextUtils;

/**
 * 
 * @author guoyading
 *
 * create at 2016年8月18日 下午5:25:22
 */
@Service
@Transactional
public class UserInfoService {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	UserInfoMapper mapper;
	
	public String login(String phone,String password){
		String object = null;
		ResponseFlag responseFlag = new ResponseFlag();
		try {
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
				object = JSONObject.wrap(responseFlag).toString();
			}
		} catch (Exception e) {
			logger.error(e.toString());
		}
	
		return object;
	}
	
	public String register(String phone,String password,String sex){
		
		String object = null;
		ResponseFlag responseFlag = new ResponseFlag();
		
		try {
			if(TextUtils.isEmpty(phone) || TextUtils.isEmpty(password) || TextUtils.isEmpty(sex)){
				responseFlag.status = "failure";
				responseFlag.msg = "注册信息不完整!";
			}else{
				String registerTime = DateUtil.format(System.currentTimeMillis());

				if(mapper.findUser(phone, password) != null){
					responseFlag.status = "failure";
					responseFlag.msg = "注册失败,手机号已注册!";
				}else{
					UserInfo userInfo = new UserInfo(0, password, phone, registerTime, "", 0, sex, "");
					int result = mapper.addUser(userInfo);
					if(result > 0 ){
						int newId = mapper.getNewId();
						userInfo.id = newId;
						responseFlag.status = "success";
						responseFlag.msg = JSONObject.wrap(userInfo).toString();
					}else{
						responseFlag.status = "failure";
						responseFlag.msg = "注册失败,请再试一次!";
					}
				}
			}
			object = JSONObject.wrap(responseFlag).toString();
		} catch (Exception e) {
			logger.error(e.toString());
		}
			
		return object;
	}
	
	
}
