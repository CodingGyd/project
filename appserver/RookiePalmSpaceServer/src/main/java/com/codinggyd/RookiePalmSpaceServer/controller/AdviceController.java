package com.codinggyd.RookiePalmSpaceServer.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codinggyd.RookiePalmSpaceServer.service.AdviceService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

/**
 * 
 * @author guoyading
 *
 * create at 2016年8月21日 下午5:25:55
 */
@RestController
@RequestMapping("/advice")
public class AdviceController {
    
	@Autowired   
	public AdviceService adviceService;
	
	@RequestMapping(value="/insertAdvice",method={RequestMethod.GET,RequestMethod.POST})
	public String insertSource(@RequestParam("advice") String adviceJson) throws JsonParseException, JsonMappingException, IOException{
		String result = adviceService.insertSingle(adviceJson);
		return result;
	}
}
