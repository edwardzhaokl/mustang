package com.mustang.pcmodel;

/**
 * project: Java
 * class: Depot
 * author: zhaokl
 * creationTime: 2018-05-15 15:53:47
 * version: 1.0
 * desc: 仓库
 * <p>
 **/

public class ProducerConsumerModel {
	public static void main(String[] args) {

		Depot depot = new Depot(100);
		Producer producer = new Producer(depot);
		Consumer consumer = new Consumer(depot);

		producer.produce(60);
		producer.produce(120);
		consumer.consume(90);
		consumer.consume(150);
		producer.produce(110);
	}
}


class Depot {
	// 仓库容量
	private int capacity;
	// 当前库存
	private int size;

	public Depot(int capacity) {
		this.capacity = capacity;
		this.size = 0;
	}

	public synchronized void produce(final int amount) {
		int left = amount;
		try {
			while (left > 0) {
				while (size >= capacity) {
					wait();
				}

				int inc = capacity >= (size + left) ? left : (capacity - size);

				size += inc;
				left -= inc;

				System.out.printf("%s produce(%3d) --> left=%3d, inc=%3d, size=%3d\n",
								  Thread.currentThread().getName(), amount, left, inc, size);

				notifyAll();
			}
		} catch (InterruptedException e) {
		}

	}

	public synchronized void consume(final int amount) {
		int left = amount;
		try {
			while (left > 0) {
				while (size <= 0) {
					wait();
				}

				int desc = size > left ? left : size;

				size -= desc;
				left -= desc;

				System.out.printf("%s consume(%3d) <-- left=%3d, dec=%3d, size=%3d\n",
								  Thread.currentThread().getName(), amount, left, desc, size);
				notifyAll();
			}
		} catch (InterruptedException e) {
		}
	}

}

class Producer {
	private Depot depot;

	public Producer(Depot depot) {
		this.depot = depot;
	}

	public void produce(final int amount) {
		new Thread(() -> depot.produce(amount)).start();
	}
}


class Consumer {
	private Depot depot;

	public Consumer(Depot depot) {
		this.depot = depot;
	}

	public void consume(final int amount) {
		new Thread(() -> depot.consume(amount)).start();
	}
}




