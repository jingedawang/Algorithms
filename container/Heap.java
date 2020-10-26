/**
 * Copyright 2020 jingedawang
 */
package container;

/**
 * <h3>Heap data structure</h3>
 */
public class Heap {

	/**
	 * Build a maximum heap using given array.
	 *
	 * @param arr The array used for initializing the heap.
	 * @return The heap built.
	 */
	public static Heap buildMaxHeap(int[] arr) {
		Heap heap = new Heap();
		heap.data = arr;
		heap.size = arr.length;
		for (int i = heap.size / 2 - 1; i >= 0; i--) {
			heap.maxHeapify(i);
		}
		return heap;
	}

	/**
	 * Build a minimum heap using given array.
	 *
	 * @param arr The array used for initializing the heap.
	 * @return The heap built.
	 */
	public static Heap buildMinHeap(int[] arr) {
		Heap heap = new Heap();
		heap.data = arr;
		heap.size = arr.length;
		for (int i = heap.size / 2 - 1; i >= 0; i--) {
			heap.minHeapify(i);
		}
		return heap;
	}

	/**
	 * Adjust the maximum sub-heap to maintain its properties.
	 *
	 * @param i The root index of the sub-heap.
	 */
	public void maxHeapify(int i) {
		int l = left(i);
		int r = right(i);
		int largest;
		if (l < size && data[l] > data[i]) {
			largest = l;
		} else {
			largest = i;
		}
		if (r < size && data[r] > data[largest]) {
			largest = r;
		}
		if (largest != i) {
			int temp = data[i];
			data[i] = data[largest];
			data[largest] = temp;
			maxHeapify(largest);
		}
	}

	/**
	 * Adjust the minimum sub-heap to maintain its properties.
	 *
	 * @param i The root index of the sub-heap.
	 */
	public void minHeapify(int i) {
		int l = left(i);
		int r = right(i);
		int smallest;
		if (l < size && data[l] < data[i]) {
			smallest = l;
		} else {
			smallest = i;
		}
		if (r < size && data[r] < data[smallest]) {
			smallest = r;
		}
		if (smallest != i) {
			int temp = data[i];
			data[i] = data[smallest];
			data[smallest] = temp;
			minHeapify(smallest);
		}
	}

	/**
	 * Get the index of the parent node.
	 *
	 * @param i The index of the given node.
	 * @return The index of the parent node.
	 */
	public int parent(int i) {
		return (i - 1) / 2;
	}

	/**
	 * Get the index of the left child node.
	 *
	 * @param i The index of the given node.
	 * @return The index of the left child node.
	 */
	public int left(int i) {
		return 2 * i + 1;
	}

	/**
	 * Get the index of the right child node.
	 *
	 * @param i The index of the given node.
	 * @return The index of the right child node.
	 */
	public int right(int i) {
		return 2 * (i + 1);
	}

	/**
	 * Underlying array data
	 */
	public int[] data;

	/**
	 * The number of elements in this heap.
	 */
	public int size;

}