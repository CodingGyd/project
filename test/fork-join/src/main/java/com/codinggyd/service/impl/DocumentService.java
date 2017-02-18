package com.codinggyd.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

@Service
public class DocumentService {
	
	public String calWords(){
		long start = System.currentTimeMillis();
		//创建文档对象
		Docoment document = new Docoment();
		String[][] documentObject = document.createDucument(400000, 1000, "CC");
		
		//创建fork-join线程对象
		CalTask calTask = new CalTask(documentObject, 0, documentObject.length, "CC");
		ForkJoinPool forkJoinPool = new ForkJoinPool();
		forkJoinPool.execute(calTask);
		Integer count = 0;
		do {
//			try {
//				TimeUnit.SECONDS.sleep(1);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
		} while (!calTask.isDone());
		
		forkJoinPool.shutdown();
		
		//调用awaitTermination等待task结束
		try {
			forkJoinPool.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		try {
			count = calTask.get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("统计出的次数: "+count);
		System.out.println("实际次数: "+document.getCount());
		if(document.getCount() != count){
			return "failure";
		}
		System.out.println("统计耗时:"+(System.currentTimeMillis()-start));
 		return "success";
	}
}

/**
 * 文档对象
 * @Title:  DocumentService.java
 * @Package: com.codinggyd.service.impl
 * @Description: 
 *
 * @author: guoyd
 * @Date: 2017年2月17日下午2:50:32
 *
 * Copyright @ 2017 Corpration Name
 */
class Docoment {
	/**单词列表*/
	private String[] words = {"AA","BB","CC","DD","EE","FF","GG","HH","II","JJ","KK"};
	private int count;//用来最后进行统计结果的验证
	/**
	 * 生成文档对象
	 * @param numRows 行数
	 * @param numCols 每行单词个数
	 * @param word 要查找的单词
	 * @return
	 */
	public String[][] createDucument(int numRows,int numCols,String word){
		
		String[][] document = new String[numRows][numCols];
		Random random = new Random();
		count = 0;
		
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCols; j++) {
				int rand = random.nextInt(words.length);
				String temp = words[rand];
				document[i][j] = temp;
				
				if(temp.equals(word)){
					count++;
				}
			}
		}
		
		return document;
	}
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}

	public String[] getWords() {
		return words;
	}

	public void setWords(String[] words) {
		this.words = words;
	}
	
}
	/**
	 * 
	 * @Title:  DocumentService.java
	 * @Package: com.codinggyd.service.impl
	 * @Description: 统计多行中单词出现的次数
	 *
	 * @author: guoyd
	 * @Date: 2017年2月17日下午2:51:29
	 *
	 * Copyright @ 2017 Corpration Name
	 */
class CalTask extends RecursiveTask<Integer> {
	
	/**
		 * 
		 */
	private static final long serialVersionUID = 7483066304467688758L;
	private String[][] document;
	private int start;
	private int end;
	private String word;
	private static final int THRESHOLD = 10;
	public CalTask(String[][] document, int start, int end, String word) {
		this.document = document;
		this.start = start;
		this.end = end;
		this.word = word;
	}
	
	@Override
	protected Integer compute() {
		int result = 0;
		if (end - start < THRESHOLD){
			//统计多行
			result = processLines(document, start,end, word);
		} else {
			int middle = (start + end) / 2;
			CalTask leftTask = new CalTask(document, start, middle, word);
			CalTask rightTask = new CalTask(document, middle, end, word);
			
			invokeAll(leftTask, rightTask);
			try {
				result = groupResults(leftTask.get(), rightTask.get());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return new Integer(result);
	}
	
	public int groupResults (int left, int right) {
		int result = left + right;
		return result;
	}
	
	/**
	 * 统计多行中word出现的次数
	 * @param document
	 * @param start
	 * @param end
	 * @param word
	 * @return
	 */
	private int processLines(String[][] document, int start, int end, String word){
		
		List<LineTask> tasks = new ArrayList<>();
		for (int i = start; i < end; i++) {
			String[] wordLine = document[i];
			LineTask lineTask = new LineTask(wordLine, 0, wordLine.length, word);
			tasks.add(lineTask);
		}
		
		invokeAll(tasks);
		
		int count = 0;
		for (int i = 0; i < tasks.size() ; i++) {
			LineTask task = tasks.get(i);
			try {
				count += task.get();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return count;
	}
}

/**
 * 
 * @Title:  DocumentService.java
 * @Package: com.codinggyd.service.impl
 * @Description: 统计单行中某单词出现的次数
 *
 * @author: guoyd
 * @Date: 2017年2月17日下午3:09:59
 *
 * Copyright @ 2017 Corpration Name
 */
class LineTask extends RecursiveTask<Integer> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3438681037852774719L;
	private static final int THRESHOLD = 100;
	private String[] line;
	private int start;
	private int end;
	private String word;
	public LineTask(String[] line, int start, int end, String word){
		this.line = line;
		this.start = start;
		this.end = end;
		this.word = word;
	}
	
	@Override
	protected Integer compute() {
		int count = 0;
		if ((end -start) <= THRESHOLD){
			count = counter(line, start, end, word);
		} else{
			int middle = (start+end)/2;
			LineTask leftTask = new LineTask(line, start, middle, word);
			LineTask rightTask = new LineTask(line, middle, end, word);
			
			invokeAll(leftTask, rightTask);
			
			try {
				count = groupResults(leftTask.get(), rightTask.get());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return new Integer(count);
	}
	public int groupResults (int left, int right) {
		int result = left + right;
		return result;
	}
	private Integer counter(String[] line, int start, int end, String word){
		int count = 0;
		for (int i = start; i < end; i++) {
			if (line[i].equals(word)) {
				count++;
			}
		}
		return new Integer(count);
	}
}

