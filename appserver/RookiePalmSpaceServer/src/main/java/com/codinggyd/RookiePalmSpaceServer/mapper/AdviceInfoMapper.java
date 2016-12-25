package com.codinggyd.RookiePalmSpaceServer.mapper;

import org.apache.ibatis.annotations.Param;

import com.codinggyd.RookiePalmSpaceServer.bean.AdviceInfo;

/**
 * 
 * @author guoyading
 *
 * create at 2016年8月21日 下午5:32:13
 */
public interface AdviceInfoMapper {
	public int insertSingle(@Param("adviceinfo") AdviceInfo adviceInfo);
}
