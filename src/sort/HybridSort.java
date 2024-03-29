/**
 * Copyright 2022 jingedawang
 */
package sort;

import utils.ArrayGenerator;
import utils.ArrayPrinter;
import utils.TimeRecorder;

/**
 * Hybrid sort algorithm.
 * <p>
 * This algorithm combines merge sort and insertion sort to achieve a faster speed.
 */
public class HybridSort extends MergeSort {

	/**
	 * Demo code.
	 */
	public static void main(String[] args) {
		int[] arrForHybridSort = ArrayGenerator.randomArray(100000, 10000000);
		int[] arrForMergeSort = arrForHybridSort.clone();
		System.out.println("Create a big array with 10000000 elements:");
		ArrayPrinter.print(arrForHybridSort);

		HybridSort sort = new HybridSort();
		TimeRecorder timeRecorder1 = new TimeRecorder();
		timeRecorder1.start();
		sort.sort(arrForHybridSort);
		timeRecorder1.stop();
		System.out.println();
		System.out.println("Sorted by hybrid sort:");
		ArrayPrinter.print(arrForHybridSort);
		System.out.println("Hybrid sort used " + timeRecorder1.getElapsedTime() + "ms.");

		MergeSort mergeSort = new MergeSort();
		TimeRecorder timeRecorder2 = new TimeRecorder();
		timeRecorder2.start();
		mergeSort.sort(arrForMergeSort);
		timeRecorder2.stop();
		System.out.println();
		System.out.println("Sorted by merge sort:");
		ArrayPrinter.print(arrForMergeSort);
		System.out.println("Merge sort used " + timeRecorder2.getElapsedTime() + "ms.");
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