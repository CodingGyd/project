package com.codinggyd.RookiePalmSpaceServer.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.codinggyd.RookiePalmSpaceServer.bean.ArticleInfo;

/**
 * 
 * @ClassName: ArticleMapper.java
 * @Description: 
 * @author guoyd
 * @date 2016年8月22日 下午4:15:38
 */
public interface ArticleMapper {
	
	public int deleteSingle(@Param("id") Integer id);
	
	public List<ArticleInfo> getAll(@Param("userId") Integer userId,@Param("type") Integer type);
	
	public boolean insertSingle(@Param("articleInfo") ArticleInfo articleInfo);
	
	public int getNewId();
	
	public boolean updateSingle(@Param("articleInfo") ArticleInfo articleInfo);
	
	public int getCount(@Param("userId") Integer userId,@Param("type") Integer type);
	
	public ArticleInfo getArticle(@Param("id") Integer id);

}
