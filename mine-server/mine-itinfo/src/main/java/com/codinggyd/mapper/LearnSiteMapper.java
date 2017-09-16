package com.codinggyd.mapper;

import java.util.List;

import com.codinggyd.bean.LearnSite;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

/**
 * 
 * 
 * @Title:  LearnSiteMapper.java
 * @Package: com.codinggyd.mapper
 * @Description: 
 *
 * @author: guoyd
 * @Date: 2017年2月18日下午2:28:18
 *
 * Copyright @ 2017 Corpration Name
 */
public interface LearnSiteMapper {
	public PageList<LearnSite> findLearnSite(PageBounds pageBounds);
	public List<LearnSite> findLearnSite();

}
