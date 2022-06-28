/**
 * Copyright 2022 jingedawang
 */
package container;

/**
 * Priority queue implemented by heap.
 */
public class PriorityQueue implements Queue {

	/**
	 * Demo code.
	 */
	public static void main(String[] args) {
		System.out.println("Create a PriorityQueue with initial values [3, 6, 1].");
		PriorityQueue priorityQueue = new PriorityQueue(new int[] {3, 6, 1});
		System.out.println("Push 5 into the queue.");
		priorityQueue.push(5);
		System.out.println("Push 2 into the queue.");
		priorityQueue.push(2);
		System.out.println("Push 4 into the queue.");
		priorityQueue.push(4);
		System.out.println("Pop the front value of the queue: " + priorityQueue.pop());
		System.out.println("Pop the front value of the queue: " + priorityQueue.pop());
		System.out.println("Pop the front value of the queue: " + priorityQueue.pop());
		System.out.println("Push 10 into the queue.");
		priorityQueue.push(10);
		System.out.println("Get the front value of the queue: " + priorityQueue.front());
		System.out.println("Pop the front value of the queue: " + priorityQueue.pop());
		System.out.println("Pop the front value of the queue: " + priorityQueue.pop());
		System.out.println("Pop the front value of the queue: " + priorityQueue.pop());
		System.out.println("Pop the front value of the queue: " + priorityQueue.pop());
	}

	/**
	 * Construct an empty priority queue.
	 */
	public PriorityQueue() {
		heap = new BinaryHeap();
	}

	/**
	 * Construct a priority queue with given array.
	 *
	 * @param arr The data array to be pushed into the priority queue in order.
	 */
	public PriorityQueue(int[] arr) {
		heap = new BinaryHeap(arr, false);
	}

	/**
	 * Push a value into the priority queue.
	 *
	 * @param value The value to be pushed into the priority queue.
	 */
	@Override
	public void push(int value) {
		heap.insert(new Node(value));
	}

	/**
	 * Get the value from the back of the queue.
	 *
	 * This method is not supported because it is based on heap.
	 *
	 * @return The value at the back.
	 */
	@Override
	public int back() {
		throw new UnsupportedOperationException("Get back value is not supported in PriorityQueue.");
	}

	/**
	 * Get the highest priority item of the priority queue.
	 *
	 * @return The highest priority item of the priority queue.
	 */
	@Override
	public int front() {
		if (heap.size() <= 0) {
			throw new ArrayIndexOutOfBoundsException("Can not get a value from an empty priority queue.");
		}
		return heap.top();
	}

	/**
	 * Get and remove the highest priority item in the priority queue.
	 *
	 * @return The highest priority item value.
	 */
	@Override
	public int pop() {
		if (heap.size() <= 0) {
			throw new ArrayIndexOutOfBoundsException("Can not pop a value from an empty priority queue.");
		}
		return heap.pop();
	}

	/**
	 * Check if the queue has no elements.
	 *
	 * @return {@code true} if the queue has no elements, {@code false} otherwise.
	 */
	@Override
	public boolean empty() {
		return heap.empty();
	}

	/**
	 * Get the size of the queue.
	 *
	 * @return The size of the queue.
	 */
	@Override
	public int size() {
		return heap.size();
	}

	private final BinaryHeap heap;

}