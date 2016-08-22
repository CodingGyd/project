package com.codinggyd.RookiePalmSpaceServer.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codinggyd.RookiePalmSpaceServer.service.ImageService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

/**
 * 
 * @author guoyading
 *
 * create at 2016年8月21日 下午5:25:55
 */
@RestController
@RequestMapping("/images")
public class ImageController {
    
	@Autowired   
	public ImageService imageService;
	
	@RequestMapping(value="/getImages",method={RequestMethod.GET,RequestMethod.POST})
	public String getImages(@RequestParam("userId") Integer userId,@RequestParam(name="type",required=false) Integer type){
		String result = imageService.getAll(userId, type);
		return result;
	}
	
	@RequestMapping(value="/insertImage",method={RequestMethod.GET,RequestMethod.POST})
	public String insertSource(@RequestParam("imageJson") String imageJson) throws JsonParseException, JsonMappingException, IOException{
		String result = imageService.insertSingle(imageJson);
		return result;
	}
}
