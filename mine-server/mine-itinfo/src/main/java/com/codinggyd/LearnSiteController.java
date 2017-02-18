package com.codinggyd;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codinggyd.bean.LearnSite;
import com.codinggyd.service.impl.LearnSiteServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;



@RestController  
@RequestMapping("/api/func/")
public class LearnSiteController {  
	final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	LearnSiteServiceImpl learnSiteService;
    
	@RequestMapping("learnsite")  
    public String login(HttpServletRequest request) {  
		List<LearnSite> result = learnSiteService.listLearnSite();
		ObjectMapper objectMapper = new ObjectMapper();
		String callback = request.getParameter("callback");
		try {
			String json = objectMapper.writeValueAsString(result);
			json = callback+"("+json+")";
	        return json;
	        
		} catch (Exception e) {
			logger.error("查询IT资讯信息失败{},",e);
			return null;
		}
	
    }  
      
}  