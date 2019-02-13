package com.codinggyd.service;

import java.util.List;

import com.codinggyd.bean.Keywords;

/**
 * 
 * 
 * @Title:  IKeywordsService.java
 * @Package: com.codinggyd.service
 * @Description: 
 *
 * @author: guoyd
 * @Date: 2019年2月13日 下午4:08:24
 *
 * Copyright @ 2019 Corpration Name
 */
public interface IKeywordsService {
	
	/**
	 * 文章关键字集合
	 * @return
	 */
	public List<Keywords> listKeywords();
	 
}
