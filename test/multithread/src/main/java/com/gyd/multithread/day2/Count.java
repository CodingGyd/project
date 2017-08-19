package com.gyd.multithread.day2;

import java.util.Random;

public class Count {
	private int count = 0;
	private Random rand = new Random(47);
	// Remove the synchronized keyword to see counting fail:
	public synchronized int  increment(){
		int temp = count;
		if (rand.nextBoolean()) { //Yield half the time
			Thread.yield();
		}
		return (count = ++temp);
	}
	
	public synchronized int value() {
		return count;
	}
}
