package com.codinggyd.service;

import java.util.List;

import com.codinggyd.bean.KeyWord;

/**
 * 
 * @Title:  IKeyWordService.java
 * @Package: com.codinggyd.service
 * @Description: 关键词管理
 *
 * @author: guoyd
 * @Date: 2017年11月5日 上午11:40:20
 *
 * Copyright @ 2017 Corpration Name
 */
public interface IKeyWordService {
	public List<KeyWord> getKeyWords(List<Integer> ids);
	public void deleteKeyWords(Integer id);
	public void updateKeyWords(KeyWord key);
	public void insertKeyWords(KeyWord key);
	public KeyWord queryKeyWords(Integer id);

}
