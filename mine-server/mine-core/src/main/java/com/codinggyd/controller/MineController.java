package com.codinggyd.controller;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codinggyd.core.MineServiceExecuter;

@RestController  
@RequestMapping(value="/api/func/", produces = MediaType.APPLICATION_JSON_VALUE)
public class MineController extends BaseController{
	
//	@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST }, consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST })
	public void run(HttpServletRequest request, HttpServletResponse response){
		OutputStream outputStream = null;
		String contentJson = "{\"ServiceId\":\"TEST_LEARN_SERVICE\",\"Params\":[1]}";
		try {
			String result = MineServiceExecuter.invoke(contentJson);
			outputStream = response.getOutputStream();
			outputStream.write(result.getBytes());
			outputStream.flush();
			outputStream.close();
		} catch (Exception e) {
			logger.error("{}",e);
		} finally {
			try {
				if (null != outputStream) {
					outputStream.close();
				} 
			} catch (IOException e) {
					logger.error("{}",e);
			}
		}

	}
	
 
	
}
	
