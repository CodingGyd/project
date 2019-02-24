package com.codinggyd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.codinggyd.bean.SysConst;

/**
 * 
 * @Title: SysConstMapper.java
 * @Package: com.codinggyd.mapper
 * @Description: 常量表操作类 
 * 
 * @Author: guoyd
 * @Date: 2019年2月23日 下午10:51:57
 *
 * Copyright @ 2019 Corpration Name
 */
public interface SysConstMapper {
	
	@Select("select id,lb,lbmc,dm,ms,updatetime,remarks from mine_sysconst ")
	public List<SysConst> queryConst();
	@Update("update mine_sysconst set lb=#{bean.lb},lbmc=#{bean.lbmc},dm=#{bean.dm},ms=#{bean.ms},updatetime=#{bean.updatetime},remarks=#{bean.remarks} WHERE id=#{bean.id}")
	public void updateSysConst(@Param("bean") SysConst bean);
	@Insert("insert into mine_sysconst(lb,lbmc,dm,ms,updatetime,remarks) values(#{bean.lb},#{bean.lbmc},#{bean.dm},#{bean.ms},#{bean.updatetime},#{bean.remarks})")
 	public void insertSysConst(@Param("bean") SysConst bean);
 	@Update("DELETE FROM mine_sysconst WHERE ID = #{id}")
 	public void deleteSysConst(@Param("id") Integer id);
	 
}
