package com.codinggyd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.codinggyd.bean.Keywords;

/**
 * 
 * 
 * @Title:  KeywordsMapper.java
 * @Package: com.codinggyd.mapper
 * @Description: 文章关键字表操作类
 *
 * @author: guoyd
 * @Date: 2019年2月13日下午2:28:18
 *
 * Copyright @ 2019 Corpration Name
 */
public interface KeywordsMapper {
	 
	//文章关键字列表
	@Select("SELECT id,name,updatetime FROM mine_keywords")
	public List<Keywords> findAllKeywords();
	

}
