package com.mustang.tool;

import java.util.concurrent.TimeUnit;

/**
 * project: Java
 * class: SleepTools
 * author: zhaokl
 * creationTime: 2018-05-14 16:34:11
 * version: 1.0
 * desc: 休眠工具类
 * <p>
 **/

public class SleepTools {

	/**
	 * author: zhaokl
	 * creationTime: 2018/5/14 16:37:55
	 * params:
	 * return:
	 * desc:  按秒休眠
	 */
	public static void seconds(int seconds) {
		try {
			TimeUnit.SECONDS.sleep(seconds);
		} catch (InterruptedException e) {
		}
	}

	/**
	 * author: zhaokl
	 * creationTime: 2018/5/14 16:38:07
	 * params:
	 * return:
	 * desc:  按毫秒休眠
	 */
	public static void milliseconds(int milliseconds) {
		try {
			TimeUnit.MILLISECONDS.sleep(milliseconds);
		} catch (InterruptedException e) {
		}
	}


}
