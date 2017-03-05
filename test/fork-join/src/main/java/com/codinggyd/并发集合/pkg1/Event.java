package com.codinggyd.并发集合.pkg1;

public class Event implements Comparable<Event>{

	private Integer priority;
	
	private Integer thread;
	
	public Event (Integer priority, Integer thread) {
		this.priority = priority;
		this.thread = thread;
	}
	
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	
	public Integer getPriority() {
		return priority;
	}
	public void setThread(Integer thread) {
		this.thread = thread;
	}
	public Integer getThread() {
		return thread;
	}
	
	@Override
	public int compareTo(Event event) {
		if (this.getPriority() > event.getPriority()) {
			return -1;
		} else if (this.getPriority() < event.getPriority()) {
			return 1;
		} else {
			return 0;
		}
	}

}
