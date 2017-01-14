package com.codinggyd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codinggyd.service.impl.UserServiceImpl;


@RestController  
@RequestMapping("/api/func/")
public class LoginController {  
	
	@Autowired
	UserServiceImpl userService;
    
	@RequestMapping("login")  
    public String login(@RequestParam("phone") String phone,@RequestParam("password") String password) {  
		String result = userService.login(phone, password);
        return result;
    }  
      
}  