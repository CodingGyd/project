package com.codinggyd.并发集合.pkg1;

import java.util.concurrent.PriorityBlockingQueue;

public class Main {
	
	public static void main(String[] args) throws InterruptedException {
		PriorityBlockingQueue<Event> events = new PriorityBlockingQueue<>();
		
		Thread[] threads = new Thread[5];
		for (int i = 0; i < threads.length ; i++) {
			Task task = new Task(i, events);
			threads[i] = new Thread(task);
			threads[i].start();
		}
		
		//等待线程结束
		for (int i = 0; i < threads.length; i++) {
			threads[i].join();
		}
		
		System.out.printf("Main: queue size:%d\n",events.size());
		
		for (int i = 0; i < threads.length * 10; i++) {
			Event event = events.poll();
			System.out.printf("Thread: %d, Priority: %d\n",event.getThread(),event.getPriority());
		}
		System.out.printf("Main: queue size:%d\n",events.size());
		System.out.println("End program");
	}
	
}
