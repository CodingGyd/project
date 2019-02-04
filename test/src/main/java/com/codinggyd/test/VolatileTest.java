package com.codinggyd.test;

public class VolatileTest {
	private  static volatile int number = 0;
	
	public  static synchronized void increase() {
		number++;
	}
	public static void main(String[] args) {
		for (int i = 1;i<=10;i++) {
			Thread thread = new Thread() {
				public void run() {
					for (int j = 1;j<=10000;j++) {
						increase();
					}
				};
			};
			
			thread.start();
		}
		while(Thread.activeCount() > 1) {
			Thread.yield();
		}
		System.out.println(number);
	}
}
