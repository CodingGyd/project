package com.codinggyd;

import java.util.List;
import java.util.concurrent.RecursiveTask;
public class TaskAdd extends RecursiveTask<Integer> {


    private static final int THRESHOLD = 5000;
    private int start;
    private int end;
    private List<Integer> list;

    public TaskAdd(List<Integer> list,int start, int end) {
        this.start = start;
        this.end = end;
        this.list = list;
    }


    @Override
    protected Integer compute() {
        int sum = 0;
        if((end-start)  < THRESHOLD){
        	list = list.subList(start, end);
            for(int i = 0; i< list.size();i++){
                sum += list.get(i);
            }
        }else{
            int middle = (start + end) /2;
            System.out.println("middle:"+middle);
            TaskAdd left = new TaskAdd(list,start, middle);
            TaskAdd right = new TaskAdd(list,middle, end);
            left.fork();
            right.fork();


            sum = left.join() + right.join();
        }
        return sum;
    }
}