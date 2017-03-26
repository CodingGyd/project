package com.codinggyd.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.codinggyd.bean.UtilFunction;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
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
	public List<UtilFunction> findUtilFunctions(PageBounds pageBounds);
}
