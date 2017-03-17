package com.codinggyd.test;

public class Main {
	public static void main(String[] args) {
		int x =5,y=10; //定义两个变量
		   x = x^y;
		   y = x^y;  //y=(x^y)
		   x = x^y;  //x=(x^y)^x
		   System.out.println("x="+x+",y="+y);
		   System.out.println(x&y);
 	}
 
	
 
}
