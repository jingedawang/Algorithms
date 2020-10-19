/**
 * Copyright 2020 jingedawang
 */
package sort;

import utils.ArrayGenerator;
import utils.TimeRecorder;

public class HybridSort extends MergeSort {

	/**
	 * Test code.
	 */
	public static void main(String[] args) {
		int[] arr = ArrayGenerator.randomArray(100000, 10000000);
		int[] arr2 = arr.clone();
//		ArrayPrinter.print(arr);

		HybridSort sort = new HybridSort();
		TimeRecorder timeRecorder = new TimeRecorder();
		timeRecorder.start();
		sort.sort(arr);
		timeRecorder.stop();
		timeRecorder.showElapsedTime();

		MergeSort mergeSort = new MergeSort();
		timeRecorder.start();
		mergeSort.sort(arr2);
		timeRecorder.stop();
		timeRecorder.showElapsedTime();

//		ArrayPrinter.print(arr);
	}

	/**
	 * Default constructor.
	 */
	public HybridSort() {
		this(200);
	}

	/**
	 * Constructor with a switching threshold.
	 *
	 * @param k The threshold for switching from merge sort to insertion sort.
	 */
	public HybridSort(int k) {
		this.k = k;
	}

	/**
	 * Hybrid sort implemented by merge sort and insertion sort.
	 *
	 * @param arr Integer array to be sorted.
	 */
	@Override
	public void sort(int[] arr) {
		mergeInsertionSort(arr, 0, arr.length - 1);
	}

	/**
	 * Merge sort a subarray with the help of insertion sort.
	 *
	 * @param arr The integer array where the subarray resides.
	 * @param p   The start index of the subarray to be sorted.
	 * @param r   The end index(included) of the subarray to be sorted.
	 */
	private void mergeInsertionSort(int[] arr, int p, int r) {
		if (p < r) {
			if (r - p < k) {
				// To simplify our implementation, we just copy arr to another array, insertion sort it and copy it
				// back.
				int[] arrCopy = new int[r - p + 1];
				System.arraycopy(arr, p, arrCopy, 0, arrCopy.length);
				insertionSort.sort(arrCopy);
				System.arraycopy(arrCopy, 0, arr, p, arrCopy.length);
			} else {
				int q = (p + r) / 2;
				mergeInsertionSort(arr, p, q);
				mergeInsertionSort(arr, q + 1, r);
				merge(arr, p, q, r);
			}
		}
	}

	// Switch merge sort to insertion sort when the length of subarray is below k.
	private final int k;

	// Inner insertion sort object.
	private final Sort insertionSort = new InsertionSort();
}