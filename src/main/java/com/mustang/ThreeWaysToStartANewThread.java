package com.mustang;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import org.junit.jupiter.api.Test;

/**
 * project: Java
 * class: ThreeWaysToStartANewThread
 * author: zhaokl
 * creationTime: 2018-05-12 15:03:23
 * version: 1.0
 * desc: 3种 启动新线程的方式
 * 1. 继承 Thread 类
 * 2. 实现 Runnable 接口 没有返回值, 不能抛异常
 * 3. 实现 Callable 接口 有返回值, 可以抛异常
 *
 * 最终都交由 Thread 类的 start() 方法启动新线程
 *
 * 直接调用 run() 或者 call() 方法, 该方法只会作为普通方法执行, 不会启动新线程
 **/

public class ThreeWaysToStartANewThread {


	@Test
	public void test1() {
		Thread thread = new StartANewThreadByExtendingClassThread("extendsThread");

		runWithMain(thread);
	}

	@Test
	public void test2() {
		Thread thread = new Thread(new StartANewThreadByImplementingRunnable(), "implementsRunnable");

		runWithMain(thread);
	}

	@Test
	public void test3() {
		FutureTask<String> futureTask = new FutureTask<>(new StartANewThreadByImplementingCallable());

		Thread thread = new Thread(futureTask, "implementsCallable");

		runWithMain(thread);

		try {
			String result = futureTask.get();
			System.out.println("futureTask result is: " + result);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}

	private static void runWithMain(Thread thread) {
		System.out.println("I am in thread " + Thread.currentThread());
		try {
			Thread.sleep(1000);
			thread.start();
			Thread.sleep(1000);
			System.out.println("I am in thread " + Thread.currentThread());
		} catch (InterruptedException e) {

		}
	}

	public static void main(String[] args) {
		System.out.println();
	}

}

class StartANewThreadByExtendingClassThread extends Thread {

	StartANewThreadByExtendingClassThread(String name) {
		super(name);
	}

	@Override
	public void run() {
		System.out.println("I am in thread " + Thread.currentThread());
	}
}

class StartANewThreadByImplementingRunnable implements Runnable {

	@Override
	public void run() {
		System.out.println("I am in thread " + Thread.currentThread());
	}
}

class StartANewThreadByImplementingCallable implements Callable<String> {

	@Override
	public String call() throws Exception {
		System.out.println("I am in thread " + Thread.currentThread());
		return "hello";
	}
}