package com.codinggyd.cache;

import java.io.Serializable;

/**
 * 
 * 
 * @Title:  ICacheKey.java
 * @Package: com.codinggyd.cache
 * @Description: 
 *
 * @author: guoyd
 * @Date: 2017年10月4日 下午11:05:08
 *
 * Copyright @ 2017 Corpration Name
 */
public interface ICacheKey<T extends Serializable> {
	public String getCacheKey(T t);
}
