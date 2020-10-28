/**
 * Copyright 2020 jingedawang
 */
package sort;

import container.Heap;
import utils.ArrayPrinter;
import utils.ArrayGenerator;

/**
 * <h3>Heap sort algorithm</h3>
 */
public class HeapSort implements Sort {

	/**
	 * Test code.
	 */
	public static void main(String[] args) {

		int[] arr = ArrayGenerator.fixedArray();
//		int[] arr = ArrayGenerator.randomArray(20, 20);
		ArrayPrinter.print(arr);

		Sort sort = new HeapSort();
		sort.sort(arr);

		ArrayPrinter.print(arr);
	}

	/**
	 * Heap sort.
	 *
	 * @param arr Integer array to be sorted.
	 */
	@Override
	public void sort(int[] arr) {
		Heap heap = Heap.buildMaxHeap(arr);
		for (int i = heap.data.length - 1; i >= 1; i--) {
			int temp = heap.data[i];
			heap.data[i] = heap.data[0];
			heap.data[0] = temp;
			heap.size--;
			heap.maxHeapify(0);
		}
	}

}