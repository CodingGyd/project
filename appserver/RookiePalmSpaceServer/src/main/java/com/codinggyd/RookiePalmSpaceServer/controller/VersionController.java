package com.codinggyd.RookiePalmSpaceServer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codinggyd.RookiePalmSpaceServer.service.VersionService;

/**
 * 
 * @author guoyading
 *
 * create at 2016年8月21日 下午5:25:55
 */
@RestController
@RequestMapping("/version")
public class VersionController {
    
	@Autowired   
	public VersionService versionService;
	
	@RequestMapping(value="/getNewVersion",method={RequestMethod.GET,RequestMethod.POST})
	public String getNewVersion(){
		String versionInfo = versionService.getNewVersion();
		return versionInfo;
	}
}
