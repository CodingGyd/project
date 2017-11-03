package com.codinggyd.service;

import java.util.List;

import com.codinggyd.bean.DailEssays;

/**
 * 
 * 
 * @Title:  IDailyEssaysService.java
 * @Package: com.codinggyd.service
 * @Description: 随笔管理
 *
 * @author: guoyd
 * @Date: 2017年10月15日 下午8:10:46
 *
 * Copyright @ 2017 Corpration Name
 */
public interface IDailyEssaysService {
	public List<DailEssays> getDailyEssays();
	public void deleteDailyEssays(Integer id);
	public void updateDailyEssays(DailEssays daily);
	public void insertDailyEssays(DailEssays daily);
	public DailEssays queryDailyEssays(Integer daily);

}
