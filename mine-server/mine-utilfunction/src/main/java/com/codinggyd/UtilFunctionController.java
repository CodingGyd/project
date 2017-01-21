package com.codinggyd;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codinggyd.service.impl.UtilFunctionServiceImpl;


@RestController  
@RequestMapping("/api/func/")
public class UtilFunctionController {  
	
	@Autowired
	UtilFunctionServiceImpl userService;
    
	@RequestMapping("utilfunctions")  
    public String login(HttpServletRequest request) {  
		String result = userService.getUtilFunction();
		
		String callback = request.getParameter("callback");
		result = callback+"("+result+")";
        return result;
    }  
      
}  