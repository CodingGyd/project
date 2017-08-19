package com.gyd.multithread.day2;

import java.util.concurrent.TimeUnit;

class WaxOn implements Runnable {

	private Car car;

	public WaxOn(Car c) {
		car = c;
	}

	@Override
	public void run() {

		try {
			while (!Thread.interrupted()) {
				System.out.println("Wax On! ");
				TimeUnit.MILLISECONDS.sleep(200);
				car.waxed();
				car.waitForBuffing();
			}
		} catch (InterruptedException e) {
			System.out.println("Exiting via interrupt");
		}
		System.out.println("Ending Wax On task");
	}
}
