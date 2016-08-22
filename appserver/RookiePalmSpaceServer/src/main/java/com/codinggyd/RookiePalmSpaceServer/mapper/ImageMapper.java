package com.codinggyd.RookiePalmSpaceServer.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.codinggyd.RookiePalmSpaceServer.bean.ImageInfo;

/**
 * 
 * @ClassName: ImageMapper.java
 * @Description: 
 * @author guoyd
 * @date 2016年8月22日 下午3:39:54
 */
public interface ImageMapper {
	public int deleteSingle(@Param("id") Integer id);
	 
	public List<ImageInfo> getAll(@Param("userId") Integer userId,@Param("type") Integer type);
	
	public boolean addImage(@Param("imageinfo") ImageInfo imageinfo);
	
	public int getNewId();
	
	public int getCount(@Param("type") Integer type);
}
