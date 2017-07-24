package com.gyd.multithread.day1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EvenChecker implements Runnable{
	
	private IntGenerator generator;
	private final int id;
	
	public EvenChecker(IntGenerator g, int ident) {
		generator = g;
		id = ident;
	}
	
	public void run() {
		while (!generator.isCanceled()) {
			int val = generator.next();
			System.out.println(val);
			if (val % 2 != 0) {
				System.out.println(val + " not even!");
				generator.cancel();//Cancels all EvenCheckers
			}
		}
	}
	
	public static void test(IntGenerator gp, int count) {
		System.out.println("Press Control-C to exit");
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < count; i++) {
			exec.execute(new EvenChecker(gp, i));
		}
		exec.shutdown();
	}
	
	// Default value for count:
	public static void test(IntGenerator gp) {
		test(gp,10);
	}
}











