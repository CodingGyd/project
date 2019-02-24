 
package com.codinggyd.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.codinggyd.fastdfs.FastDFSClientWrapper;
import com.codinggyd.jsch.SFTPUtil;
import com.codinggyd.utils.DateUtils;
 
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
@RequestMapping("sys/image")
public class ImageController {
	
 	@Autowired
	private FastDFSClientWrapper dfsClient;

//sftp.host=47.111.1.180
//sftp.port=22
//sftp.username=guoyd
//sftp.password=guoyd
//sftp.seperator=/
 	@Value("${sftp.host}")
 	private String sftpHost;
 	@Value("${sftp.port}")
 	private Integer sftpPort;
 	@Value("${sftp.username}")
 	private String sftpUsername;
 	@Value("${sftp.password}")
 	private String sftpPassword;
 	@Value("${sftp.seperator}")
 	private String sftpSeperator;
 	@Value("${sftp.upload.root.directory}")
 	private String sftpRootDirectory;
 	@Value("${sftp.access.url}")
 	private String sftpAccessUrl;
	final Logger logger = LoggerFactory.getLogger(getClass());
 	@RequestMapping(value={"/upload"},method = { RequestMethod.GET, RequestMethod.POST })
	public  @ResponseBody Map<String,Object> imgupload(@RequestParam(value = "editormd-image-file", required = false) MultipartFile file,HttpServletRequest request,HttpServletResponse response) {
		logger.debug("图片上传========");
		long start = System.currentTimeMillis();
	    Map<String,Object> resultMap = new HashMap<>();
	    String imgUrl;
		try {
			String dateDirectory = DateUtils.dateToString(new Date(), "yyyyMMdd");
//			imgUrl = dfsClient.uploadFile(file);//fastdfs 废弃，改用sftp
			
			imgUrl = SFTPUtil.upload2("/", sftpHost, sftpPort, sftpUsername, sftpPassword, sftpRootDirectory, dateDirectory, file.getInputStream(),file.getOriginalFilename());
			resultMap.put("success", 1);
			resultMap.put("message", "上传成功！");
			resultMap.put("url",sftpAccessUrl+sftpSeperator+imgUrl);//imgUrl只是是相对路径
		} catch (Exception e) {
			logger.error("访问sftp出错,上传图片失败,错误信息{}",e);
			resultMap.put("failure", 1);
			resultMap.put("message", "上传失败！");
		}
		logger.debug("上传耗时[{}]ms",System.currentTimeMillis()-start);
		 return resultMap;  
	}
		
}
