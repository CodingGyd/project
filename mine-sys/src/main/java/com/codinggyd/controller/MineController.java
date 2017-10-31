 
package com.codinggyd.controller;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.codinggyd.bean.Article;
import com.codinggyd.bean.ArticleTable;
import com.codinggyd.bean.ArticleType;
import com.codinggyd.service.IArticleService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
 
 
@Controller
@RequestMapping("sys")
public class MineController {
	
	@Qualifier("articleServiceImpl")
	@Autowired
	private IArticleService service;
	
	@Value("${image.upload.dir}")
	private String imageUploadDir;
	final Logger logger = LoggerFactory.getLogger(getClass());

	private static final String PATTERN = "yyyy-MM-dd HH:mm.ss.SSS";
	private static ObjectMapper om = new ObjectMapper();
	@RequestMapping(value="/articlelist",method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody String getArticleList(HttpServletRequest request,HttpServletResponse response) {
		
		ArticleTable articleTable = new ArticleTable();
		articleTable.setTotal(0);
		List<Article> articles = service.findArticles();
		if (CollectionUtils.isNotEmpty(articles)) {
			articleTable.setRows(articles);
			articleTable.setTotal(articles.size());
		}
		String result = null;
		try {
			result = om.writeValueAsString(articleTable);
		} catch (JsonProcessingException e) {
			logger.error("序列化result出错,{}",e);
		}
 		return result;
	}
	
	@RequestMapping(value="/insert",method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody String update(HttpServletRequest request,HttpServletResponse response) {
		
		Article article = new Article();
		article.setTitle(request.getParameter("title"));
		article.setContent(request.getParameter("content"));
		article.setHtmlContent(request.getParameter("htmlContent"));
		article.setDescs(request.getParameter("descs"));
		article.setUpdatetime(DateFormatUtils.format(new Date(), PATTERN));
		article.setReadingcount(0);
		article.setType(request.getParameter("type"));
		
		service.insertArticle(article);
		return "success";
	}
	
	@RequestMapping(value="/updatebatch",method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody String updatebatch(@RequestBody Article article) {
		if (null != article) article.setUpdatetime(DateFormatUtils.format(new Date(), PATTERN));
		service.updateArticle(article);
		
		return "success";
	}

	@RequestMapping(value="/delete",method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody String delete(Integer id) {
	
		service.deleteArticle(id);
		
		return "success";
	}
  
	//文章分类
	@RequestMapping(value={"/article_types"})
	public @ResponseBody List<ArticleType> listTypes(HttpServletRequest request,HttpServletResponse response) {
		List<ArticleType> data = service.findArticleTypes();
		return data;
	}
	//图片上传
	@RequestMapping(value={"/imgupload"},method = { RequestMethod.GET, RequestMethod.POST })
	public  @ResponseBody Map<String,Object> imgupload(@RequestParam(value = "editormd-image-file", required = false) MultipartFile file,HttpServletRequest request,HttpServletResponse response) {
		 logger.debug("图片上传========");
		 
		 File dir = new File(imageUploadDir);
		 if (!dir.exists()) {
			 dir.mkdirs();
		 }
		   Map<String,Object> resultMap = new HashMap<String,Object>();
	        String fileName = file.getOriginalFilename();  
	        File targetFile = new File(imageUploadDir, fileName);  
	        
	        //保存  
	        try {  
	        	if(!targetFile.exists()){  
	        		targetFile.createNewFile();
	        	}  
	            file.transferTo(targetFile);  
	            resultMap.put("success", 1);
	            resultMap.put("message", "上传成功！");
	            resultMap.put("url",request.getContextPath()+"/images/upload/"+fileName);
	        } catch (Exception e) {  
	            resultMap.put("success", 0);
	            resultMap.put("message", "上传失败！");
	            e.printStackTrace();  
	        }  
	        return resultMap;  
	}
	
}
