package com.codinggyd.util;

import java.util.ArrayList;
import java.util.List;

import com.codinggyd.bean.Paginator;

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
}
