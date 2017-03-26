package com.codinggyd.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public abstract class CollectionsUtils {
	
	public static <T> List<T> setToList(Set<T> set) {
		List<T> result = new ArrayList<>();
		result.addAll(set);
		return result;
	}
}
