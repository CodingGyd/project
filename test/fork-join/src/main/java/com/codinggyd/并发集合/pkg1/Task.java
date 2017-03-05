package com.codinggyd.并发集合.pkg1;

import java.util.concurrent.PriorityBlockingQueue;

public class Task implements Runnable{

	private Integer id;
	
	private PriorityBlockingQueue<Event> events;
	
	public Task (Integer id, PriorityBlockingQueue<Event> events) {
		this.id = id;
		this.events = events;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			Event event = new Event(id,i);
			events.add(event);
		}
	}

}
