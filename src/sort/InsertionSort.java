/**
 * Copyright 2022 jingedawang
 */
package sort;

import utils.ArrayGenerator;
import utils.ArrayPrinter;

/**
 * Insertion sort algorithm.
 */
public class InsertionSort implements Sort {

	/**
	 * Demo code.
	 */
	public static void main(String[] args) {
		int[] arr = ArrayGenerator.fixedArray();
		System.out.println("Original array:");
		ArrayPrinter.print(arr);

		Sort sort = new InsertionSort();
		sort.sort(arr);
		System.out.println();
		System.out.println("Sorted by insertion sort:");
		ArrayPrinter.print(arr);
	}

	/**
	 * Insertion sort.
	 *
	 * @param arr Integer array to be sorted.
	 */
	@Override
	public void sort(int[] arr) {
		for (int j = 1; j < arr.length; j++) {
			int key = arr[j];
			// Insert arr[j] into the sorted sequence arr[0..j-1].
			int i = j - 1;
			while (i >= 0 && arr[i] > key) {
				arr[i + 1] = arr[i];
				i--;
			}
			arr[i + 1] = key;
		}
	}

}