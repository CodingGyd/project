package com.codinggyd.service;

import java.util.List;

import com.codinggyd.annotation.MineMethod;
import com.codinggyd.bean.SysConst;

/**
 * 
 * 
 * @Title:  ISysConstService.java
 * @Package: com.codinggyd.service
 * @Description: 常量查询
 *
 * @author: guoyd
 * @Date: 2017年9月23日 上午1:24:20
 *
 * Copyright @ 2017 Corpration Name
 */
public interface ISysConstService {
	
	@MineMethod(value="MINE_CONST")
	public List<SysConst> listConst(List<String> lb);
	 
}
