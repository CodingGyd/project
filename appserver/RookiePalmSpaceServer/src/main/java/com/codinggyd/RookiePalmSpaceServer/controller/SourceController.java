package com.codinggyd.RookiePalmSpaceServer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.codinggyd.RookiePalmSpaceServer.service.SourceService;

/**
 * 
 * @author guoyading
 *
 * create at 2016年8月21日 下午5:25:55
 */
@RestController
@RequestMapping("/sources")
public class SourceController {
    
	@Autowired   
	public SourceService sourceService;
	
	@RequestMapping(value="/getSources",method={RequestMethod.GET,RequestMethod.POST})
	public String getSources(@RequestParam("userId") Integer userId,@RequestParam(name="type",required=false) Integer type){
		String result = sourceService.getAll(userId, type);
		return result;
	}
	
	@RequestMapping(value="/insertSource",method={RequestMethod.GET,RequestMethod.POST})
	public String insertSource(@RequestParam("sourceJson") String sourceJson){
		
		String result = sourceService.insertSingle(sourceJson);
		return result;
	}
	
	@RequestMapping(value="/delSource",method={RequestMethod.GET,RequestMethod.POST})
	public String delSource(@RequestParam("sourceId") Integer sourceId){
		String result = sourceService.deleteSingle(sourceId);
		return result;
	}
}
