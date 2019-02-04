package com.codinggyd.utils;

import java.util.ArrayList;
import java.util.List;

public class CollectionUtils {
	/**
	 * 从多个 list 里取交集
	 * 
	 * @param lists
	 * @return
	 */
	public static List<String> intersection(List<List<String>> lists) {

		if (lists == null || lists.size() == 0) {
			return new ArrayList<String>();
		}

		ArrayList<List<String>> arrayList = new ArrayList<>(lists);
		for (int i = 0; i < arrayList.size(); i++) {
			List<String> list = arrayList.get(i);
			// 若存在空集合,则交集直接返回空
			if (null == list || list.size() == 0) {
				return new ArrayList<String>();
			}
		}

		List<String> intersection = arrayList.get(0);

		// 有多个集合，直接挨个交集
		for (int i = 0; i < arrayList.size(); i++) {
			intersection.retainAll(arrayList.get(i));
		}
		return intersection;
	}
	
	
	/**
	 * 
	 * @Title: checkExistEmpty 
	 * @Description: 检查输入参数是否存在为null  
	 * @param args
	 * @return boolean  输入参数存在为null则输出true，否则false
	 * @throws 
	 * @Author guoyd
	 * @Date 2019年1月24日 上午10:52:08
	 */
	public static boolean checkExistEmpty(Object...args) {
		if (null != args && args.length != 0) {
			for (Object obj : args) {
				if (null == obj ) {
					return true;
				}
			}
		}
		return false;
	}
}
