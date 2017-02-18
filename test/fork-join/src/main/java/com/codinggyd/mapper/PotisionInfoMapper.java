package com.codinggyd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.codinggyd.bean.PositionInfo;
public interface PotisionInfoMapper {
	public Integer save(@Param("list") List<PositionInfo> list);

}
