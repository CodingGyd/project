package com.codinggyd.config;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SpringContextHolder {

	public static ExecutorService executorService;
	static {
		executorService = Executors.newCachedThreadPool(); 
	}
}
