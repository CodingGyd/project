package com.codinggyd.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * 
 * @Title:  BaseController
 * @Package: com.codinggyd.controller
 * @Description: 
 *
 * @author: guoyd
 * @Date: 2017年2月20日下午3:28:28
 *
 * Copyright @ 2017 Corpration Name
 */
public abstract class BaseController {
	final Logger logger = LoggerFactory.getLogger(getClass());
	
	ObjectMapper objectMapper = new ObjectMapper();
	
	/**
	 * 返回数据给客户端
	 * @param request
	 * @param response
	 * @param result
	 */
	public void response(HttpServletRequest request, HttpServletResponse response, String result) {
		contentToTxt("ttttt.txt",result);
		OutputStream out = null;
		try {
	 		//解决错误:已拦截跨源请求：同源策略禁止读取位于 http://127.0.0.1:8080/mine-client/data/utilfunction 的远程资源。（原因：CORS 头缺少 'Access-Control-Allow-Origin'）。
	 		response.addHeader("Access-Control-Allow-Origin", "*");
			response.setStatus(200);
			response.setContentLength(result.length());
			response.setContentType("application/json; charset=UTF-8");
			
			out = response.getOutputStream();
			out.write(result.getBytes());
			out.flush();
			out.close();
		} catch (Exception e) {
			logger.error("响应出错{}",e);
		} finally {
			try {
				if (null != out) {
					out.close();
				}
			} catch (IOException e) {
				logger.error("响应出错{}",e);
 			}
		}
	}
	
	
	public static void contentToTxt(String filePath, String content) {  
        String str = new String(); //原有txt内容  
        String s1 = new String();//内容更新  
        try {  
            File f = new File(filePath);  
            if (f.exists()) {  
                System.out.print("文件存在");  
            } else {  
                System.out.print("文件不存在");  
                f.createNewFile();// 不存在则创建  
            }  
            BufferedReader input = new BufferedReader(new FileReader(f));  
  
            while ((str = input.readLine()) != null) {  
                s1 += str + "\n";  
            }  
            System.out.println(s1);  
            input.close();  
            s1 += content;  
  
            BufferedWriter output = new BufferedWriter(new FileWriter(f));  
            output.write(s1);  
            output.close();  
        } catch (Exception e) {  
            e.printStackTrace();  
  
        }  
    }  
  

	 
	@ExceptionHandler({ Exception.class })
	public void exception(Exception e, HttpServletRequest request, HttpServletResponse response) {
		logger.error("系统异常,{}",e);
		response(request,response,"系统异常");
	}
}
	
