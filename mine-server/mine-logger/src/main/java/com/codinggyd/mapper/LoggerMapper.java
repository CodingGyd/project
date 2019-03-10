package com.codinggyd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.codinggyd.bean.LoggerEntity;

/**
 * 
 * 
 * @Title:  LoggerMapper.java
 * @Package: com.codinggyd.mapper
 * @Description: 
 *
 * @author: guoyd
 * @Date: 2019年2月13日下午2:28:18
 *
 * Copyright @ 2019 Corpration Name
 */
public interface LoggerMapper {
//	@Insert("<script>"
//			+"INSERT INTO mine_loginfo("
//			+"		ip,"
//			+"		url,"
//			+"		type,"
//			+"		method,"
//			+"		paramData,"
//			+"		sessionId,"
//			+"		time,"
//			+"		httpStatusCode,"
//			+"		timeConsuming,"
//			+"		returnTime,"
//			+"		returnData"
//			+"	)"
//			+"	VALUES <foreach collection=\"list\" item=\"item\" index=\"index\" separator=\",\">"
//			+"	(		 "
//			+"			 #{item.ip},"
//			+"			 #{item.url},"
//			+"			 #{item.type},"
//			+"			 #{item.method,jdbcType=VARCHAR},"
//			+"			 #{item.paramData,jdbcType=VARCHAR},"
//			+"			 #{item.sessionId,jdbcType=INTEGER},"
//		 	+"			 #{item.time,jdbcType=TIMESTAMP},"
//			+"			 #{item.httpStatusCode,jdbcType=VARCHAR},"
//			+"			 #{item.timeConsuming,jdbcType=INTEGER},"
//			+"			 #{item.returnTime,jdbcType=VARCHAR},"
//			+"			 #{item.returnData,jdbcType=VARCHAR}"
//			+"  			 )"
//			+"	</foreach>"
//			+ " </script>")
	public void addLogger(@Param("list") List<LoggerEntity> data);
}