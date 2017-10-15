package com.codinggyd.service;

import java.util.List;

import com.codinggyd.annotation.MineMethod;
import com.codinggyd.bean.DailEssays;

/**
 * 
 * 
 * @Title:  IDailyEssaysService.java
 * @Package: com.codinggyd.service
 * @Description: 随笔
 *
 * @author: guoyd
 * @Date: 2017年10月15日 下午8:10:46
 *
 * Copyright @ 2017 Corpration Name
 */
public interface IDailyEssaysService {
	@MineMethod("MINE_DAILY_ESSAYS")
	public List<DailEssays> getDailyEssays(String[] pageInfo);
}
