package com.codinggyd.mapper;

import java.util.List;

import com.codinggyd.bean.DailEssays;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

/**
 * 
 * @Title:  DailyEssaysMapper.java
 * @Package: com.codinggyd.mapper
 * @Description: 随笔
 *
 * @author: guoyd
 * @Date: 2017年10月15日 下午8:15:53
 *
 * Copyright @ 2017 Corpration Name
 */
public interface DailyEssaysMapper {
	public PageList<DailEssays> findDailyEssays(PageBounds pageBounds);
	public List<DailEssays> findDailyEssays();
}
