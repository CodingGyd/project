package com.codinggyd.service;

import java.util.List;

import com.codinggyd.annotation.MineMethod;
import com.codinggyd.bean.LearnSite;

/**
 * 
 * 
 * @Title:  UtilFunctionService.java
 * @Package: com.codinggyd.service
 * @Description: 
 *
 * @author: guoyd
 * @Date: 2017年1月21日下午7:09:39
 *
 * Copyright @ 2017 Corpration Name
 */
public interface ILearnSiteService {
	
	@MineMethod(value="MINE_LIST_LEARN_SITE")
	public List<LearnSite> listLearnSite(String[] pageInfo);
}
