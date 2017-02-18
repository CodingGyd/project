package com.codinggyd.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.codinggyd.TaskAdd;
 
/**
 * 
 * @Title:  PotisitionServiceImpl.java
 * @Package: com.codinggyd.service.impl
 * @Description: 测试并行计算求和
 *
 * @author: guoyd
 * @Date: 2017年2月16日下午4:09:47
 *
 * Copyright @ 2017 Corpration Name
 */
@Service
public class CalculatorService {
	final Logger logger = LoggerFactory.getLogger(getClass());

	public Integer calculator() {
		long start = System.currentTimeMillis();
		//测试fork-join实现累计求和
		return forkJoin();
	}
	
	private Integer forkJoin(){
//		List<PositionInfo> list = createList(100000);
		List<Integer> list = new ArrayList<Integer>();
		for(int i=1;i<10000;i++){
			list.add(i);
		}
		test(list);

		//使用fork-join框架的并行计算方式进行入库////////
		TaskAdd task= new TaskAdd(list,0, list.size());
		ForkJoinPool forkJoinPool = new ForkJoinPool();
		Future<Integer> result = forkJoinPool.submit(task);
		/////////////////////////////
		try {
			Integer sum = result.get();
			System.out.println(sum);
			return sum;
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		return -1;
//		do {
//			System.out.printf("Main: Thread Count: %d\n",forkJoinPool.getActiveThreadCount());
//			System.out.printf("Main: Thread Steal: %d\n",forkJoinPool.getStealCount());
//			System.out.printf("Main: Parallelism: %d\n",forkJoinPool.getParallelism());
//			System.out.println();
//			try {
//				TimeUnit.MICROSECONDS.sleep(5);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		} while (!task.isDone());
//		
//		forkJoinPool.shutdown();
//		
//		if (task.isCompletedNormally()) {
//			System.out.printf("Main: The process has completed normally.\n");
//		}
	
	}
	private void test(List<Integer> list){
		int sum = 0;
		for(int i=0;i<list.size();i++){
			sum += list.get(i);
		}
		System.out.println("累计求和:"+sum);
	}
 
	 
}
