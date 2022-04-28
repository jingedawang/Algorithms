/**
 * Copyright 2022 jingedawang
 */
package sort;

import container.Heap;
import utils.ArrayPrinter;
import utils.ArrayGenerator;

/**
 * Heap sort algorithm.
 *
 * This sort algorithm is implemented with {@link Heap}.
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
		Heap heap = new Heap(arr, true);
		for (int i = 0; i < arr.length; i++) {
			arr[i] = heap.pop();
		}
	}

}