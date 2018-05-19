package com.mustang.stream;

import sun.security.krb5.internal.ktab.KeyTabInputStream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * project: Java
 * class: TestStream
 * author: zhaokl
 * creationTime: 2018-05-17 11:18:26
 * version: 1.0
 * desc: 测试 JDK8 流式操作
 * <p>
 **/

public class TestStream {

	public static void main(String[] args) {

		List<Integer> list = Arrays.asList(3, 1, 2, 3, 4, 5);

		List<Integer> result = new ArrayList<>();

		list.stream().filter(param -> param % 2 == 1).forEach(System.out::println);

		list.stream().sorted((param1, param2) -> (param1 < param2 ? 1 : -1)).forEach(System.out::println);

		list.stream().map(param -> param % 2 == 0 ? true : false).forEach(System.out::println);

		list.stream().distinct().forEach(System.out::println);

		Object o = list.stream().reduce((param1, param2) -> param1 + param2).get();
		System.out.println(o);

		List<Integer> _list = list.stream().filter(param -> param % 2 == 0).collect(Collectors.toList());
		_list.stream().forEach(System.out::println);

		o = list.stream().max((param1, param2) -> (param1 > param2) ? -1 : 1).get();
		System.out.println(o);
	}
}
