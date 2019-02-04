package com.codinggyd.utils;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class MathUtils {
	/**
	 * 获取最大值
	 * @param data
	 * @return
	 */
	public static Double getMax(List<Double> data){
		if (null == data || data.isEmpty()) {
			return null;
		}
		
		Double max = data.get(0);
		for (Double temp : data){
			if (null == temp){
				continue;
			}
			if (max < temp){
				max = temp;
			}
		}
		return max;
	}
	
	/**
	 * 获取最小值
	 * @param data
	 * @return
	 */
	public static Double getMin(List<Double> data){
		if (null == data || data.isEmpty()) {
			return null;
		}
		Double min = data.get(0);
		for (Double temp : data){
			if (null == temp){
				continue;
			}
			if (min > temp){
				min = temp;
			}
		}
		return min;
	}
	
	/**
	 * 获取平均值
	 * @param data
	 * @return
	 */
	public static Double getAvg(List<Double> data){
		if (null == data || data.isEmpty()) {
			return null;
		}
		Double sum = 0d;
		for (Double temp : data){
			if (null == temp){
				continue;
			}
			sum += temp;
		}
		return sum/data.size();
	}
	
	/**
	 * 求和
	 * @param data
	 * @return
	 */
	public static Double getSum(List<Double> data){
		if (null == data || data.isEmpty()) {
			return null;
		}
		Double sum = 0d;
		for (Double temp : data){
			if (null == temp){
				continue;
			}
			sum += temp;
		}
		return sum;
	}
	
	
	
	/**
	 * 标准差
	 * @param data
	 * @return
	 */
	public static Double getSd(List<Double> data) {
		
		Double variance = getVariance(data);
		if (null == variance || variance.isInfinite() || variance.isNaN()){
			 return null;
		}
		return Math.sqrt(variance);
	}
	
	/**
	 * 下行标准差
	 * @param data
	 * @return
	 */
	public static Double getDD(List<Double> data) {
		//获取平均值
		Double avg = getAvg(data);
		if (null == avg) {
			return null;
		}
		//计算下行方差
		Double sum1 = 0.0;
		int count = 0;
		for (Double unitNv : data) {
			count++;
			if ( unitNv == null ){
				continue;
			}
			sum1 += Math.pow(Math.min(unitNv-avg, 0), 2);
		}
		Double variance = sum1/(count-1);
		if (null == variance || variance.isInfinite() || variance.isNaN()){
			 return null;
		}
		return Math.sqrt(variance);
	}
	
	 
	/**
	 * 计算方差
	 * @return
	 */
	public static Double getVariance(List<Double> data) {
		if (null == data || data.isEmpty()) {
			return null;
		}
		
		Double avg = getAvg(data);
		if (null == avg) {
			return null;
		}
		//2.计算方差
		Double sum1 = 0.0;
		int count = 0;
		for (Double unitNv : data) {
			count++;
			if ( unitNv == null ){
				continue;
			}
			sum1 += Math.pow(unitNv-avg, 2);
		}
		return sum1/(count-1);
	}
	
	/**
	 * 中值
	 * 将数据从小到大排序，若n为偶数，则中值等于第n/2个值与第(n+2)/2个值的算术平均数；若n为奇数，则中值等于第(n+1)/1个值。
	 * @param datas
	 * @return
	 */
	public static double getMiddle(List<Double> datas) {
		if (null == datas || datas.isEmpty()) {
			return 0;
		}
		 
		int length = datas.size();
		
		if (length == 1 ){
			return datas.get(0);
		}
		
		//升序排序
		Collections.sort(datas);
		double middle;
		if (length % 2 == 0) {
			//偶数
			middle = (datas.get((length/2)-1)+datas.get((length+2)/2-1))/2;
		} else {
			middle = datas.get((length+1)/2-1);
		}
		return middle;
	}
		
	/** 
	 * 随机指定范围内N个不重复的数 
	 * 利用HashSet的特征，只能存放不同的值 
	 * @param min 指定范围最小值 
	 * @param max 指定范围最大值 
	 * @param sum 随机数个数 
	 * @param n 随机数个数 
	 * @param HashSet<Integer> set 随机数结果集 
	 */  
	  public static void randomSet(int min, int max, int sum, int n, HashSet<Integer> set) {  
	       if (n > (max - min + 1) || max < min) {  
	           return;  
	       }  
	       for (int i = 0; i < n; i++) {  
	           // 调用Math.random()方法  
	           int num = (int) (Math.random() * (max - min)) + min;  
	           set.add(num);// 将不同的数存入HashSet中  
	       }  
	       int setSize = set.size();  
	       // 如果存入的数小于指定生成的个数，则调用递归再生成剩余个数的随机数，如此循环，直到达到指定大小  
	       if (setSize < sum) {  
	        randomSet(min, max, sum,sum - setSize, set);// 递归  
	       }  
	   }  
 
}
