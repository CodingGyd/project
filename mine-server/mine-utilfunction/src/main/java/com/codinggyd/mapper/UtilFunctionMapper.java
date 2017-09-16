package com.codinggyd.mapper;

import java.util.List;

import com.codinggyd.bean.UtilFunction;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
/**
 * 
 * 
 * @Title:  UtilFunctionMapper.java
 * @Package: com.codinggyd.mapper
 * @Description: 
 *
 * @author: guoyd
 * @Date: 2017年1月21日下午7:07:18
 *
 * Copyright @ 2017 Corpration Name
 */
public interface UtilFunctionMapper {
	public PageList<UtilFunction> findUtilFunctions(PageBounds pageBounds);
	public List<UtilFunction> findUtilFunctions();
}
