package com.codinggyd.RookiePalmSpaceServer.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.codinggyd.RookiePalmSpaceServer.bean.SourceInfo;

/**
 * 
 * @author guoyading
 *
 * create at 2016年8月21日 下午8:26:44
 */
public interface SourceMapper {
	public int deleteSingle(@Param("id") Integer id);
 
	public List<SourceInfo> getAll(@Param("userId") Integer userId,@Param("type") Integer type);
	
	public boolean addSource(@Param("sourceInfo") SourceInfo sourceInfo);
	
	public int getNewId();
	
	public int getCount(@Param("type") Integer type);
}
