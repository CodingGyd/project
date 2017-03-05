package com.codinggyd.并发集合;

import java.util.concurrent.ConcurrentLinkedDeque;

public class PollTask implements Runnable{

	private ConcurrentLinkedDeque<String> list;
	
	public PollTask (ConcurrentLinkedDeque<String> list) {
		this.list = list;
	}
	@Override
	public void run() {
		for (int i = 1; i <= 1000; i++) {
			list.pollFirst();
			list.pollLast();
		}
	}

}
