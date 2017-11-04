 
package com.codinggyd.controller;

import java.util.Date;
import java.util.List;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.codinggyd.bean.Article;
import com.codinggyd.bean.ArticleType;
import com.codinggyd.bean.DataTable;
import com.codinggyd.redis.RedisClientUtils;
import com.codinggyd.service.IArticleService;
 
/**
 * 
 * @Title:  ArticleController.java
 * @Package: com.codinggyd.controller
 * @Description: 文章管理相关接口
 *
 * @author: guoyd
 * @Date: 2017年11月3日 下午9:58:18
 *
 * Copyright @ 2017 Corpration Name
 */
@Controller
@RequestMapping("sys")
public class ArticleController {
	
	@Qualifier("articleServiceImpl")
	@Autowired
	private IArticleService service;
	
	@Value("${image.upload.dir}")
	private String imageUploadDir;
	final Logger logger = LoggerFactory.getLogger(getClass());

	private static final String PATTERN = "yyyy-MM-dd HH:mm.ss.SSS";

	@RequestMapping(value="/article/article_byid",method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Article getArticleSingle(HttpServletRequest request,HttpServletResponse response) {
		Integer id = Integer.parseInt(request.getParameter("id"));
		Article article = service.queryArticle(id);
		return article;
	}
	
	@RequestMapping(value="/article/articlelist",method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody DataTable<Article> getArticleList(HttpServletRequest request,HttpServletResponse response) {
		
		DataTable<Article> articleTable = new DataTable<Article>();
		articleTable.setTotal(0);
		List<Article> articles = service.findArticles();
		if (CollectionUtils.isNotEmpty(articles)) {
			articleTable.setRows(articles);
			articleTable.setTotal(articles.size());
		}
		 
		return articleTable;
	}
	
	@RequestMapping(value="/article/insert",method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody String insert(HttpServletRequest request,HttpServletResponse response) {
		
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
	
	@RequestMapping(value="/article/update_notwithcontent",method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody String updateNotWithContent(@RequestBody Article article) {
		if (null != article) article.setUpdatetime(DateFormatUtils.format(new Date(), PATTERN));
		service.updateArticle(article);
		//删除缓存
 		RedisClientUtils.deleteFromCache(article.getId()+"");
		return "success";
	}
	
	@RequestMapping(value="/article/update_withcontent",method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody String updateWithContent(HttpServletRequest request,HttpServletResponse response) {
		Article article = new Article();
		article.setId(Integer.parseInt(request.getParameter("id")));
		article.setTitle(request.getParameter("title"));
		article.setContent(request.getParameter("content"));
		article.setHtmlContent(request.getParameter("htmlContent"));
		article.setDescs(request.getParameter("descs"));
		article.setUpdatetime(DateFormatUtils.format(new Date(), PATTERN));
		article.setType(request.getParameter("type"));
		//更新文章内容
 		service.updateArticleContent(article);
 		//删除缓存
 		RedisClientUtils.deleteFromCache(article.getId()+"");
		return "success";
	}

	@RequestMapping(value="/article/delete",method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody String delete(Integer id) {
	
		service.deleteArticle(id);
		//删除缓存
 		RedisClientUtils.deleteFromCache(id+"");
		return "success";
	}
  
	//文章分类
	@RequestMapping(value={"/article/article_types"})
	public @ResponseBody List<ArticleType> listTypes(HttpServletRequest request,HttpServletResponse response) {
		List<ArticleType> data = service.findArticleTypes();
		return data;
	}
	//图片上传
//	@RequestMapping(value={"/article/imgupload"},method = { RequestMethod.GET, RequestMethod.POST })
//	public  @ResponseBody Map<String,Object> imgupload(@RequestParam(value = "editormd-image-file", required = false) MultipartFile file,HttpServletRequest request,HttpServletResponse response) {
//		 logger.debug("图片上传========");
		 
//		 File dir = new File(imageUploadDir);
//		 if (!dir.exists()) {
//			 dir.mkdirs();
//		 }
//		   Map<String,Object> resultMap = new HashMap<String,Object>();
//	        String fileName = file.getOriginalFilename();  
//	        File targetFile = new File(imageUploadDir, fileName);  
//	        
//	        //保存  
//	        try {  
//	        	if(!targetFile.exists()){  
//	        		targetFile.createNewFile();
//	        	}  
//	            file.transferTo(targetFile);  
//	            resultMap.put("success", 1);
//	            resultMap.put("message", "上传成功！");
//	            resultMap.put("url",request.getContextPath()+"/images/upload/"+fileName);
//	        } catch (Exception e) {  
//	            resultMap.put("success", 0);
//	            resultMap.put("message", "上传失败！");
//	            e.printStackTrace();  
//	        }  
//	        return resultMap;  
//	}
	
}
