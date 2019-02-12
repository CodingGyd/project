package com.codinggyd.mapper;

import org.apache.ibatis.annotations.Select;

import com.codinggyd.bean.SiteInformation;

/**
 * 
 * 
 * @Title:  SiteInformationMapper.java
 * @Package: com.codinggyd.mapper
 * @Description: 
 *
 * @author: guoyd
 * @Date: 2019年2月12日下午8:28:18
 *
 * Copyright @ 2019 Corpration Name
 */
public interface SiteInformationMapper {
	//获取建站信息
	@Select("SELECT site_create_time timeOfSiteCreate,remarks FROM mine_siteinformation " )
	public SiteInformation getSiteInformation();
	//获取文章数量
	@Select("SELECT COUNT(1) FROM mine_articles")
	public Integer getNumsOfArticles();
	//获取评论数量
//	@Select("SELECT COUNT(1) FROM mine_articles")
//	public Integer getNumsOfComment();
}
