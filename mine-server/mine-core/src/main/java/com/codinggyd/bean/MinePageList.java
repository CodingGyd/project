package com.codinggyd.bean;

import java.io.Serializable;
import java.util.List;

public class MinePageList implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5945813306441995281L;
	  /**
     * 分页大小
     */
    private int limit;
    /**
     * 页数
     */
    private int page;
    /**
     * 总记录数
     */
    private int totalCount;
    private List<?> data;
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public List<?> getData() {
		return data;
	}
	public void setData(List<?> data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "MinePageList [limit=" + limit + ", page=" + page + ", totalCount=" + totalCount + ", data=" + data
				+ "]";
	}
	
}
