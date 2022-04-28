/**
 * Copyright 2020 jingedawang
 */
package container;

/**
 * <h3>Priority queue implemented by heap</h3>
 */
public class PriorityQueue {

	/**
	 * Test code.
	 */
	public static void main(String[] args) {
		PriorityQueue priorityQueue = new PriorityQueue(new int[] {3, 6, 1});
		priorityQueue.push(5);
		priorityQueue.push(2);
		priorityQueue.push(4);
		System.out.println(priorityQueue.pop());
		System.out.println(priorityQueue.pop());
		System.out.println(priorityQueue.pop());
		priorityQueue.push(10);
		System.out.println(priorityQueue.pop());
		System.out.println(priorityQueue.pop());
		System.out.println(priorityQueue.pop());
		System.out.println(priorityQueue.pop());
	}

	/**
	 * Construct an empty priority queue.
	 */
	public PriorityQueue() {
		heap = new Heap();
	}

	/**
	 * Construct a priority queue with given array.
	 *
	 * @param arr The data array to be pushed into the priority queue in order.
	 */
	public PriorityQueue(int[] arr) {
		heap = new Heap(arr, true);
	}

	/**
	 * Push a value into the priority queue.
	 *
	 * @param value The value to be pushed into the priority queue.
	 */
	public void push(int value) {
		heap.insert(new Node(value));
	}

	/**
	 * Get the highest priority item of the priority queue.
	 *
	 * @return The highest priority item of the priority queue.
	 */
	public int head() {
		if (heap.getSize() <= 0) {
			throw new ArrayIndexOutOfBoundsException("Can not get a value from an empty priority queue.");
		}
		return heap.top();
	}

	/**
	 * Get and remove the highest priority item in the priority queue.
	 *
	 * @return The highest priority item value.
	 */
	public int pop() {
		if (heap.getSize() <= 0) {
			throw new ArrayIndexOutOfBoundsException("Can not pop a value from an empty priority queue.");
		}
		return heap.pop();
	}

	private final Heap heap;

}