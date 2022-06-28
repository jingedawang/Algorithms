/**
 * Copyright 2022 jingedawang
 */
package sort;

import container.BinaryHeap;
import utils.ArrayPrinter;
import utils.ArrayGenerator;

/**
 * Heap sort algorithm.
 *
 * This sort algorithm is implemented with {@link BinaryHeap}.
 */
public class HeapSort implements Sort {

	/**
	 * Demo code.
	 */
	public static void main(String[] args) {
		int[] arr = ArrayGenerator.fixedArray();
		System.out.println("Original array:");
		ArrayPrinter.print(arr);

		Sort sort = new HeapSort();
		sort.sort(arr);
		System.out.println();
		System.out.println("Sorted by heap sort:");
		ArrayPrinter.print(arr);
	}

	/**
	 * Heap sort.
	 *
	 * @param arr Integer array to be sorted.
	 */
	@Override
	public void sort(int[] arr) {
		BinaryHeap heap = new BinaryHeap(arr, true);
		for (int i = 0; i < arr.length; i++) {
			arr[i] = heap.pop();
		}
	}

}