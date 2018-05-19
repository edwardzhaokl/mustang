package com.mustang;

import lombok.extern.slf4j.Slf4j;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * project: Java
 * class: OnlyMain
 * author: zhaokl
 * creationTime: 2018-05-12 12:12:46
 * version: 1.0
 * desc: 只有主线程时, 虚拟机会启动几个线程?
 * <p>
 **/

//@Slf4j
public class OnlyMain {

	public static void main(String[] args) throws InterruptedException {
		int sum = 0;
		for (int i = 0; i < 100; ++i) {
			Thread.sleep(100);
			sum += i;
		}
		// 虚拟机线程管理接口
		ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();

		ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
		for (ThreadInfo ti : threadInfos) {
			System.out.println("[" + ti.getThreadId() + "] " + ti.getThreadName());
		}


//		long[] ids = threadMXBean.getAllThreadIds();
//
//		for (long l : ids) {
//			ThreadInfo threadInfo = threadMXBean.getThreadInfo(l);
//			System.out.println("[" + l + "] " + threadInfo.getThreadName());
//			// System.out.println("thread "+ l + " info is: " + threadInfo);
//		}
	}
}
