package com.codinggyd.并发集合;

import java.util.concurrent.ConcurrentLinkedDeque;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		ConcurrentLinkedDeque<String> list = new ConcurrentLinkedDeque<String>();
		
		Thread[] threads = new Thread[100];
		//创建100个往集合增加元素的任务和对应的线程
		for (int i = 0; i < threads.length; i++){
			AddTask addTask = new AddTask(list);
			threads[i] = new Thread(addTask);
			threads[i].start();
		}
		
		System.out.printf("Main: %d AddTask Threads have been launched\n",threads.length);
		
		//调用线程的join方法, 等待线程执行完毕
		for (int i = 0; i < threads.length; i++) {
			threads[i].join();
		}
		
		System.out.printf("Main: list size is %d\n", list.size());
 
		//创建100个从集合中移除元素的任务和对应的线程
		for (int i = 0; i < threads.length; i++) {
			PollTask pollTask = new PollTask(list);
			threads[i] = new Thread(pollTask);
			threads[i].start();
		}
		
		System.out.printf("Main: %d PollTask Threads have been launched\n",threads.length);

		//调用线程的join方法, 等待线程执行完毕
		for (int i = 0; i < threads.length; i++) {
			threads[i].join();
		}
		
		System.out.printf("Main: list size is %d\n", list.size());

	}
	
}
