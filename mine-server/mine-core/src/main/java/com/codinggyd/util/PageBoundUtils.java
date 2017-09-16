package com.codinggyd.util;

import org.apache.commons.lang3.math.NumberUtils;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

public class PageBoundUtils {
	public static PageBounds getPageBounds(String[] pageArray) throws Exception{
		if (null == pageArray) {
			throw new RuntimeException("分页参数["+pageArray+"]不能为空");
		}
		
		int page = 0;//当前页码
		int limit = 0;//页大小
		boolean containsTotalCount;//是否包含总记录数
		String order;//如果你想排序的话逗号分隔可以排序多列
		try {
			
			if (NumberUtils.isNumber(pageArray[0]) && NumberUtils.isNumber(pageArray[1])) {
				page = Integer.parseInt(pageArray[0]);
				limit = Integer.parseInt(pageArray[1]);
			}
			containsTotalCount = Boolean.parseBoolean(pageArray[2]); 
		    order = pageArray[3];
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	
		return new PageBounds(page, limit,Order.formString(order), containsTotalCount);
	}
}
