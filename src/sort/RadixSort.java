/**
 * Copyright 2022 jingedawang
 */
package sort;

import utils.ArrayPrinter;
import utils.ArrayGenerator;

/**
 * Radix sort algorithm.
 */
public class RadixSort implements Sort {

	/**
	 * Demo code.
	 */
	public static void main(String[] args) {
		// The maximum number of digits of the elements.
		int d = 3;
		// The upper limit of each digit.
		int k = 10;
		int[] arr = ArrayGenerator.randomArray((int) Math.pow(k, d));
		System.out.println("Original array:");
		ArrayPrinter.print(arr);

		Sort sort = new RadixSort(d, k);
		sort.sort(arr);
		System.out.println();
		System.out.println("Sorted by radix sort:");
		ArrayPrinter.print(arr);
	}

	/**
	 * Constructor with {@code d} and {@code k} specified.
	 *
	 * @param d The maximum number of digits of the elements.
	 * @param k The upper limit of each digit.
	 */
	public RadixSort(int d, int k) {
		this.d = d;
		this.k = k;
	}

	/**
	 * Radix sort.
	 *
	 * @param arr Integer array to be sorted.
	 */
	@Override
	public void sort(int[] arr) {
		radixSort(arr, d, k);
	}

	/**
	 * Radix sort an array.
	 *
	 * @param arr The array to be sorted.
	 * @param d   The maximum number of digits of the elements.
	 * @param k   The upper limit of each digit.
	 */
	public void radixSort(int[] arr, int d, int k) {
		int[] digitArr = new int[arr.length];
		CountingSort countingSort = new CountingSort(k);
		for (int i = 0; i < d; i++) {
			for (int j = 0; j < arr.length; j++) {
				digitArr[j] = arr[j];
				for (int l = 0; l < i; l++) {
					digitArr[j] /= k;
				}
				digitArr[j] %= k;
			}
			int[] indices = new int[digitArr.length];
			countingSort.countingSortWithIndices(digitArr, new int[digitArr.length], k, indices);
			int[] temp = new int[arr.length];
			for (int j = 0; j < arr.length; j++) {
				temp[j] = arr[indices[j]];
			}
			System.arraycopy(temp, 0, arr, 0, temp.length);
		}
	}

	// The maximum number of digits of the elements.
	private final int d;

	// The upper limit of each digit.
	private final int k;
}