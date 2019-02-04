package com.codinggyd.test;


public class Test1 {
	public static void main(String[] args) {
		 new Test1().method1();
		 new Test1().method2();
	}
	
	public void method1() {
		int i = 1;
		int a = i++;
		System.out.println(a);
	}
	
	public void method2() {
		int i = 1;
		int a = ++i;
		System.out.println(a);
	}
}
