package com.mustang;

import com.mustang.tool.SleepTools;

/**
 * project: Java
 * class: TestThreadLocal
 * author: zhaokl
 * creationTime: 2018-05-14 17:00:28
 * version: 1.0
 * desc: ThreadLocal 类
 * <p>
 **/

public class TestThreadLocal implements Runnable{

	static ThreadLocal<Integer> threadLocal;

	int local;

	TestThreadLocal(int i) {
		threadLocal = ThreadLocal.withInitial(() -> i);
		this.local = i;
	}


	public static void startThreadArray(int size) {
		for (int i = 0; i < size; ++i) {
			TestThreadLocal testThreadLocal = new TestThreadLocal(i);
			new Thread(testThreadLocal, i + "").start();
		}
	}


	public static void main(String[] args) {

		startThreadArray(3);

	}

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + " is running ... " + threadLocal.get() + " " + local);
	}
}
