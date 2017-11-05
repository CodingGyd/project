package com.codinggyd;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codinggyd.service.IDataExportService;

/**
 * 
 * @Title:  DataExportController.java
 * @Package: com.codinggyd
 * @Description: 通用数据导出
 *
 * @author: guoyd
 * @Date: 2017年3月26日下午12:32:57
 *
 * Copyright @ 2017 Corpration Name
 */
@RestController  
@RequestMapping("/api/func/")
public class DataExportController {  
	
	final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	@Qualifier("dataExportServiceImpl")
	IDataExportService service;
     
	@RequestMapping("data-export")  
    public String dataExport(HttpServletRequest request, HttpServletResponse response,@RequestParam("script") String script) {  
		
		ServletOutputStream outputStream;
		try {
			String charactSet = "ISO8859_1";
			response.setContentType("application/vnd.ms-excel;charset="+charactSet);  
			response.setCharacterEncoding(charactSet);
			String filename = "导出数据";
 			response.setHeader("Content-disposition", "attachment; filename=" + new String(filename.getBytes("gb2312"), charactSet) + ".xls");// 组装名称和格式  
 			
			outputStream = response.getOutputStream();
			service.dataExport(outputStream,script);
		} catch (Exception e) {
			logger.error("导出发生错误,{}",e);
 		}  
	
        return null;
    }  
      
}  