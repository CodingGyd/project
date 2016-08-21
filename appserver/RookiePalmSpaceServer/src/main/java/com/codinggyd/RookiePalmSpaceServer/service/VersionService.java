package com.codinggyd.RookiePalmSpaceServer.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codinggyd.RookiePalmSpaceServer.bean.VersionInfo;
import com.codinggyd.RookiePalmSpaceServer.mapper.VersionInfoMapper;

/**
 * 
 * @author guoyading
 *
 * create at 2016年8月21日 下午5:27:00
 */
@Service
public class VersionService {
	
	@Autowired
	public VersionInfoMapper mapper ;
	 
	public String getNewVersion(){
		VersionInfo version = mapper.getNewVersion();
		return JSONObject.wrap(version).toString();
	}
}
