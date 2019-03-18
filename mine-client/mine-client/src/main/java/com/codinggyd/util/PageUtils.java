package com.codinggyd.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.codinggyd.bean.PageList;
import com.codinggyd.bean.Paginator;

/**
 * 
 * 
 * @Title: PageUtils.java
 * @Package: com.codinggyd.util
 * @Description: 通用分页工具类
 * 
 * @Author: guoyd
 * @Date: 2019年3月18日 下午1:32:29
 *
 * Copyright @ 2019 Corpration Name
 */
public class PageUtils {
	/**
	 * java 代码层分页
	 */
	public static <T>  List<T> paging(List<T> list, Paginator paginator) {
		Integer pagenum = null;
		Integer pagesize = null;
		 
		pagenum = paginator != null ? paginator.getPage() :1;
		pagesize = paginator != null ? paginator.getLimit() : list.size();
		Integer m = 0;
		if(pagenum==null||pagesize==null||pagesize<1||pagenum<1){
			throw new RuntimeException( "分页参数不正确,页码:"+pagenum+",条数:"+pagesize);
		}
		List<T> pageinglist = new ArrayList<T>();
		if (list.size() <= pagesize && pagenum==1) {
			return list;
		}
		if (list.size() <= pagesize*(pagenum-1)) {
			throw new RuntimeException("页码越界,总条数:"+list.size()+",页码:"+pagenum+",条数:"+pagesize);
		}
		for (int i = pagesize * (pagenum - 1); i < pagesize * pagenum; i++) {
			if(i>=list.size()){
				break;
			}
			pageinglist.add(m, list.get(i));
			m++;
		}
		return pageinglist;
	}
	
	public static <T> PageList<T> pageList2(List<T> list, Paginator pageBounds) {
		if (CollectionUtils.isEmpty(list) || pageBounds == null) {
			return new PageList<>();
		}
		Integer pagenum = pageBounds.getPage();
		Integer pagesize = pageBounds.getLimit();
		if (pagenum == null || pagesize == null || pagesize < 1 || pagenum < 1) {
			throw new RuntimeException( "分页参数不正确,页码:"+pagenum+",条数:"+pagesize);
 		}
		int totalCount = list.size();
		Paginator paginator = new Paginator(pagenum, pagesize, totalCount);
		if (totalCount <= pagesize && pagenum == 1) {
			return new PageList<>(list, paginator);
		}
		if (totalCount <= pagesize * (pagenum - 1)) {
			throw new RuntimeException("页码越界,总条数:"+list.size()+",页码:"+pagenum+",条数:"+pagesize);
		}
		List<T> data = new ArrayList<>();
		for (int i = pagesize * (pagenum - 1); i < pagesize * pagenum; i++) {
			if (i >= totalCount) {
				break;
			}
			data.add(list.get(i));
		}
		return new PageList<>(data,paginator);
	}
}
