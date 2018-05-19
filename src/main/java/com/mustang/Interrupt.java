package com.mustang;

import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * project: Java
 * class: Interrupt
 * author: zhaokl
 * creationTime: 2018-05-14 13:59:08
 * version: 1.0
 * desc: 测试3个中断方法, 及中断标准写法
 * <p>
 **/

public class Interrupt {
	// 不要用这种方式测试多线程代码
	@Test
	public void test1() throws InterruptedException {
		Thread thread = new InterruptedThread("threadB");
		thread.start();
	}

	public static void main(String[] args) throws InterruptedException {
		Thread runnable = new Thread(new InterruptedRunnable(), "Runnable");
		Thread thread = new InterruptedThread("Thread");

		runnable.start();
		thread.start();

		System.out.println("currentThread is: " + Thread.currentThread());
		Thread.sleep(10);

		runnable.interrupt();
		System.out.println("runnable: " + runnable.isInterrupted());

		thread.interrupt();
		System.out.println("thread: " + thread.isInterrupted());

	}

}

class InterruptedRunnable implements Runnable {
	private static AtomicInteger counter = new AtomicInteger(0);

	@Override
	public void run() {
		try {
			// 1. isInterrupted()保证，只要中断标记为true就终止线程。
			while (!Thread.currentThread().isInterrupted()) {
				// 执行业务逻辑
				System.out.println(Thread.currentThread().getName() + " is running ... " + counter.incrementAndGet());
				Thread.sleep(1);
			}
		} catch (InterruptedException interruptedException) {
			// 2. InterruptedException 异常保证，当 InterruptedException 异常产生时，线程被终止。
			System.out.println(Thread.currentThread().getName() + " is in Exception " + counter.get()
							   + " " + Thread.currentThread().isInterrupted());
		}

		System.out.println("!!!" + Thread.currentThread().getName() + " is running ... " + counter.get()
						   + " " + Thread.currentThread().isInterrupted());
	}
}


class InterruptedThread extends Thread {
	private static AtomicInteger counter = new AtomicInteger(0);

	InterruptedThread(String name) {
		super(name);
	}

	@Override
	public void run() {

		try {
			// 1. isInterrupted()保证，只要中断标记为true就终止线程。
			while (!isInterrupted()) {
				// 执行业务逻辑
				System.out.println(getName() + " is running ... " + counter.incrementAndGet());
				sleep(1);
			}
		} catch (InterruptedException interruptedException) {
			// 2. InterruptedException异常保证，当InterruptedException异常产生时，线程被终止。
			System.out.println(getName() + " is in Exception " + counter.get()
							   + " " + isInterrupted());
		}
	}
}