package jikeshijian.queue;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 并发循环队列，数组实现
 * @author danyubin
 *
 */
public class ArrayBlockCircleQueue {

	private int[] data;
	private final AtomicInteger tail;
	private final AtomicInteger head;
	private int capacity;

	public ArrayBlockCircleQueue(int capacity) {
		this.data = new int[capacity];
		this.tail = new AtomicInteger(0);
		this.head = new AtomicInteger(0);
		this.capacity = capacity;

	}

	public void print() {
		System.out.println(Arrays.toString(data));
	}

	/**
	 * 队尾入队
	 * 
	 * @param value
	 * @return
	 */
	public boolean enqueue(int value) {
		// 检查队列是否已满
		if ((tail.get() + 1) % capacity == head.get()) {
			System.out.println("队列已满");
			return false;
		}

		int tailValue = tail.get();
		int updValue = (tailValue + 1) % capacity;

		if (tail.compareAndSet(tailValue, updValue)) {
			this.data[tailValue] = value;
			return true;
		}

		return false;
	}

	/**
	 * 队首出队
	 * 
	 * @return 队首元素，如果队列为空返回-1
	 */
	public int dequeue() {
		// 首先检查队列是否为空
		if (tail.get() == head.get()) {
			System.out.println("队列为空");
			return -1;
		}

		int dequeueBefore = head.get();
		int deuqueAfter = (dequeueBefore + 1) % capacity;

		if (head.compareAndSet(dequeueBefore, deuqueAfter)) {
			int value = this.data[dequeueBefore];
			return value;
		}

		return -1;
	}
	
	/**
	 * 队列是否为空，头指针和尾指针相等
	 * @return true 空队列
	 */
	public boolean isEmpty() {
		return tail.get() == head.get();
	}

	public static void main(String[] args) {
		ArrayBlockCircleQueue abcq = new ArrayBlockCircleQueue(100);
		for (int i = 1; i < 100; i++) {
			abcq.enqueue(i);
		}
		abcq.print();

		ExecutorService executors = Executors.newFixedThreadPool(3);

		executors.execute(new Runnable() {

			@Override
			public void run() {
				while (!abcq.isEmpty()) {
					System.out.println(Thread.currentThread().getName() + ":" + abcq.dequeue());
				}
			}
		});
		executors.execute(new Runnable() {

			@Override
			public void run() {
				while (!abcq.isEmpty()) {
					System.out.println(Thread.currentThread().getName() + ":" + abcq.dequeue());
				}
			}
		});
		executors.execute(new Runnable() {

			@Override
			public void run() {
				while (!abcq.isEmpty()) {
					System.out.println(Thread.currentThread().getName() + ":" + abcq.dequeue());
				}
			}
		});

	}
}
