package com.codinggyd.core;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codinggyd.bean.MineFuncBean;
import com.codinggyd.bean.MineServiceBean;

/**
 * 
 * @Title:  MineServiceContext
 * @Package: com.codinggyd.core
 * @Description: service管理类
 *
 * @author: guoyd
 * @Date: 2017年2月18日下午7:15:42
 *
 * Copyright @ 2017 Corpration Name
 */
public abstract class MineServiceContext {
	/**
	 * service对象集合
	 */
	private static final Map<String,MineServiceBean> mineServiceBeansMap;
	/**
	 * service执行结果集集合
	 */
	private static final Map<String,MineFuncBean> mineFuncBeansMap;
	static {
		mineServiceBeansMap = new ConcurrentHashMap<>();
		mineFuncBeansMap = new ConcurrentHashMap<>();
	}
	final Logger logger = LoggerFactory.getLogger(getClass());
	 
	/**
	 * 新增service对象
	 * @param serviceId
	 * @param mineServiceBean
	 */
	public static void addMineServiceBean(String serviceId, MineServiceBean mineServiceBean) {
		if (null == mineServiceBean) {
			return ;
		}
		mineServiceBeansMap.put(serviceId, mineServiceBean);
	}
	
	/**
	 * 获取service对象
	 * @param serviceId
	 * @return
	 */
	public static MineServiceBean getMineServiceBean (String serviceId) {
		return mineServiceBeansMap.get(serviceId);
	}
	
	/**
	 * 新增结果集对象
	 * @param fundId
	 * @param mineFuncBean
	 */
	public static void addMineFuncBean(String fundId, MineFuncBean mineFuncBean) {
		if (null == mineFuncBean) {
			return ;
		}
		mineFuncBeansMap.put(fundId, mineFuncBean);
	}
	
	/**
	 * 获取结果集
	 * @param fundId
	 * @return
	 */
	public static MineFuncBean getMineFuncBean (String fundId) {
		return mineFuncBeansMap.get(fundId);
	}
	
}
