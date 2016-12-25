package com.codinggyd.RookiePalmSpaceServer.util;

public class TextUtils {
	
	public static boolean isEmpty(String data){
		if(null == data || data.length() == 0){
			return true;
		}
		return false;
	}
}
