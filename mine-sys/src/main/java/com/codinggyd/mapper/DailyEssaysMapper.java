package com.codinggyd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import com.codinggyd.bean.DailEssays;

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
 	public List<DailEssays> findDailyEssays();
 	public void updateDailyEssays(@Param("daily") DailEssays daily);
 	public void insertDailyEssays(@Param("daily") DailEssays daily);
 	public DailEssays queryDailyEssays(@Param("id") Integer id);
 	@Update("DELETE FROM mine_dailyessays WHERE ID = #{id}")
 	public void deleteDailyEssays(@Param("id") Integer id);
}
