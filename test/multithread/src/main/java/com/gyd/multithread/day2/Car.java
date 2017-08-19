package com.gyd.multithread.day2;

class Car {
	private boolean waxOn = false; //涂腊-抛光处理的状态

	public synchronized void waxed() {
		waxOn = true;// Ready to buff
		notifyAll();
	}

	public synchronized void buffed() {
		waxOn = false;// Ready for another coat of wax
		notifyAll();
	}

	public synchronized void waitForWaxing() throws InterruptedException {
		while (waxOn == false) {
			wait();
		}
	}

	public synchronized void waitForBuffing() throws InterruptedException {
		while (waxOn == true) {
			wait();
		}
	}
}
