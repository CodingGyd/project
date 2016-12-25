package com.codinggyd.RookiePalmSpaceServer.service;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	public VersionInfoMapper mapper ;
	 
	public String getNewVersion(){
		String object = null;
		System.out.println("===v");
		try{
			VersionInfo version = mapper.getNewVersion();
			object = JSONObject.wrap(version).toString();
		}catch(Exception e){
			logger.error(e.toString());
		}
		
		return object;
	}
}
