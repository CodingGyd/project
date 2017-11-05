package com.codinggyd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import com.codinggyd.bean.KeyWord;

/**
 * 
 * @Title:  KeyWordMapper.java
 * @Package: com.codinggyd.mapper
 * @Description: 关键词管理
 *
 * @author: guoyd
 * @Date: 2017年11月5日 上午11:32:14
 *
 * Copyright @ 2017 Corpration Name
 */
public interface KeyWordMapper {
 	public List<KeyWord> findKeywords(@Param("ids") List<Integer> ids);
 	public void updateKeyWords(@Param("key") KeyWord key);
 	public void insertKeyWords(@Param("key") KeyWord key);
 	public KeyWord queryKeyWords(@Param("id") Integer id);
 	@Update("DELETE FROM mine_keywords WHERE ID = #{id}")
 	public void deleteKeyWord(@Param("id") Integer id);
}
