package com.codinggyd.redis;

import java.io.Serializable;

/**
 * 
 * 
 * @Title:  ICacheKey.java
 * @Package: com.codinggyd.redis
 * @Description: 
 *
 * @author: guoyd
 * @Date: 2017年11月1日 下午10:03:31
 *
 * Copyright @ 2017 Corpration Name
 */
public interface ICacheKey<T extends Serializable> {
	public String getCacheKey(T t);
}
