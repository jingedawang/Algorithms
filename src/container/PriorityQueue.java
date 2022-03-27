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
		PriorityQueue priorityQueue = new PriorityQueue();
		priorityQueue.insert(3);
		priorityQueue.insert(6);
		priorityQueue.insert(1);
		priorityQueue.insert(5);
		priorityQueue.insert(2);
		priorityQueue.insert(4);
		System.out.println(priorityQueue.extractMax());
		System.out.println(priorityQueue.extractMax());
		System.out.println(priorityQueue.extractMax());
		priorityQueue.insert(10);
		System.out.println(priorityQueue.extractMax());
		System.out.println(priorityQueue.extractMax());
		System.out.println(priorityQueue.extractMax());
		System.out.println(priorityQueue.extractMax());
	}

	/**
	 * Insert a value into priority queue.
	 *
	 * @param x The value to be inserted.
	 */
	public void insert(int x) {
		if (heap.size >= heap.capacity) {
			int[] newArr;
			if (heap.capacity > 0) {
				newArr = new int[heap.capacity * 2];
				System.arraycopy(heap.data, 0, newArr, 0, heap.size);
			} else {
				newArr = new int[8];
			}
			heap.data = newArr;
			heap.capacity = newArr.length;
		}
		heap.size++;
		heap.data[heap.size - 1] = Integer.MIN_VALUE;
		increaseKey(heap.size - 1, x);
	}

	/**
	 * Get the maximum value of the priority queue.
	 *
	 * @return The maximum value of the priority queue.
	 */
	public int maximum() {
		if (heap.size <= 0) {
			throw new ArrayIndexOutOfBoundsException("Can not get a value from an empty priority queue.");
		}
		return heap.data[0];
	}

	/**
	 * Extract the maximum value of the priority queue.
	 * <p>
	 * After the maximum value is extracted, the heap will shrink and reorganize.
	 *
	 * @return The maximum value of the priority queue.
	 */
	public int extractMax() {
		if (heap.size <= 0) {
			throw new ArrayIndexOutOfBoundsException("Can not extract a value from an empty priority queue.");
		}
		int max = heap.data[0];
		heap.data[0] = heap.data[heap.size - 1];
		heap.size--;
		heap.maxHeapify(0);
		return max;
	}

	/**
	 * Increase the value of the specified item.
	 * <p>
	 * The new value must be greater than the original value, or this method would raise an error.
	 *
	 * @param i The index of the specified item.
	 * @param k The new value of the specified item.
	 */
	public void increaseKey(int i, int k) {
		if (k < heap.data[i]) {
			throw new IllegalArgumentException("k must be greater than the item of index i.");
		}
		heap.data[i] = k;
		while (i > 0 && heap.data[i] > heap.data[heap.parent(i)]) {
			int temp = heap.data[i];
			heap.data[i] = heap.data[heap.parent(i)];
			heap.data[heap.parent(i)] = temp;
			i = heap.parent(i);
		}
	}

	private final Heap heap = new Heap();

}