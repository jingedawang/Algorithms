/**
 * Copyright 2022 jingedawang
 */
package sort;

import utils.ArrayPrinter;
import utils.ArrayGenerator;

import java.util.ArrayList;
import java.util.List;

/**
 * Bucket sort algorithm.
 */
public class BucketSort implements Sort {

	/**
	 * Demo code.
	 */
	public static void main(String[] args) {
		// The elements to be sorted by bucket sort must lie in [0, k).
		int k = 10;
		int[] arr = ArrayGenerator.randomArray(k);
		System.out.println("Original array:");
		ArrayPrinter.print(arr);

		Sort sort = new BucketSort(k);
		sort.sort(arr);
		System.out.println();
		System.out.println("Sorted by bucket sort:");
		ArrayPrinter.print(arr);
	}

	/**
	 * Constructor with {@code k} specified.
	 *
	 * @param k The upper limit of the array elements.
	 */
	public BucketSort(int k) {
		this.k = k;
	}

	/**
	 * Bucket sort.
	 *
	 * @param arr Integer array to be sorted.
	 */
	@Override
	public void sort(int[] arr) {
		bucketSort(arr, k);
	}

	/**
	 * Bucket sort.
	 *
	 * @param arr The array to be sorted.
	 * @param k   The upper limit of the array elements.
	 */
	private void bucketSort(int[] arr, int k) {
		List<Integer>[] buckets = new List[arr.length];
		for (int i = 0; i < buckets.length; i++) {
			buckets[i] = new ArrayList<>();
		}
		for (int item : arr) {
			buckets[item * arr.length / k].add(item);
		}
		int index = 0;
		InsertionSort insertionSort = new InsertionSort();
		for (List<Integer> bucket : buckets) {
			int[] bucketArr = bucket.stream().mapToInt(Integer::valueOf).toArray();
			insertionSort.sort(bucketArr);
			System.arraycopy(bucketArr, 0, arr, index, bucketArr.length);
			index += bucketArr.length;
		}
	}

	// The upper limit of the array elements.
	private final int k;

}