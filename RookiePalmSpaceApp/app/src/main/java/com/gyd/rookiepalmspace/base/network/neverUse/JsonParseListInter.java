package com.gyd.rookiepalmspace.base.network.neverUse;

import java.io.InputStream;


/**
 * json字符串的解析,根据不同需求解析成不同的实体集合
 * @author gyd
 *
 * 2014-11-9
 */
public interface JsonParseListInter {
	public abstract Object parseToList(InputStream is);
}
