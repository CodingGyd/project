package com.codinggyd.RookiePalmSpaceServer.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codinggyd.RookiePalmSpaceServer.service.STSService;

/**
 * 
 * @author guoyading
 *
 * create at 2016年8月21日 下午5:25:55
 */
@RestController
@RequestMapping("/sts")
public class StsController {
    
	@Autowired   
	public STSService stsService;
	
	@RequestMapping(value="/getSts",method={RequestMethod.GET,RequestMethod.POST})
	public String getSts(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		String sts = stsService.doGet(request, response);
		System.out.println("==sts:"+sts);
		return sts;
	}
}
