package com.codinggyd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.codinggyd.bean.SysConst;

/**
 * 
 * 
 * @Title:  SysConstMapper.java
 * @Package: com.codinggyd.mapper
 * @Description: 常量查询
 *
 * @author: guoyd
 * @Date: 2017年9月23日 上午1:22:55
 *
 * Copyright @ 2017 Corpration Name
 */
public interface SysConstMapper {
	
	public List<SysConst> listConst(@Param("lbs") List<String> lbs,@Param("dm") String dm);

}
