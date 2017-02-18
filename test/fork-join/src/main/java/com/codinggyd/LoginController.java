package com.codinggyd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codinggyd.service.impl.CalculatorService;
import com.codinggyd.service.impl.DocumentService;
import com.codinggyd.service.impl.PotisitionServiceImpl;


@RestController  
@RequestMapping("/")
public class LoginController {  
	
	@Autowired
	PotisitionServiceImpl saveService;
	@Autowired
	CalculatorService calService;
	@Autowired
	DocumentService documentService;
	@RequestMapping("save")  
    public Integer save() {  
		Integer result = saveService.save();
        return result;
    }  
     
	@RequestMapping("cal")  
    public Integer cal() {  
		Integer result = calService.calculator();
        return result;
    }  
	
	@RequestMapping("count")  
    public String count() {  
		String result = documentService.calWords();
        return result;
    }  
}  