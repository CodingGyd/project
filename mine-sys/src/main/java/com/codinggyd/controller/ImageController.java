 
package com.codinggyd.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.codinggyd.fastdfs.FastDFSClientWrapper;
 
/**
 * 
 * @Title:  ImageController.java
 * @Package: com.codinggyd.controller
 * @Description: 图片管理
 *
 * @author: guoyd
 * @Date: 2017年11月5日 上午12:10:13
 *
 * Copyright @ 2017 Corpration Name
 */
@Controller
@RequestMapping("sys")
public class ImageController {
	
 	@Autowired
	private FastDFSClientWrapper dfsClient;
	
	final Logger logger = LoggerFactory.getLogger(getClass());
	//图片上传
	@RequestMapping(value={"/image/upload"},method = { RequestMethod.GET, RequestMethod.POST })
	public  @ResponseBody Map<String,Object> imgupload(@RequestParam(value = "editormd-image-file", required = false) MultipartFile file,HttpServletRequest request,HttpServletResponse response) {
		logger.debug("图片上传========");
		long start = System.currentTimeMillis();
	    Map<String,Object> resultMap = new HashMap<>();
	    String imgUrl;
		try {
			imgUrl = dfsClient.uploadFile(file);
			resultMap.put("success", 1);
			resultMap.put("message", "上传成功！");
			resultMap.put("url",imgUrl);
		} catch (IOException e) {
			logger.error("访问fdfs出错,上传图片失败,错误信息{}",e);
			resultMap.put("failure", 1);
			resultMap.put("message", "上传失败！");
		}
		logger.debug("上传耗时[{}]ms",System.currentTimeMillis()-start);
		 return resultMap;  
	}
		
}
