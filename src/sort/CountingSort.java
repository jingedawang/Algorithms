/**
 * Copyright 2020 jingedawang
 */
package sort;

import utils.ArrayPrinter;
import utils.ArrayGenerator;

/**
 * <h3>Counting sort algorithm</h3>
 */
public class CountingSort implements Sort {

	/**
	 * Test code.
	 */
	public static void main(String[] args) {
		// The elements to be sorted by counting sort must lies in [0, k).
		int k = 10;

		int[] arr = ArrayGenerator.randomArray(k);
		ArrayPrinter.print(arr);

		Sort sort = new CountingSort(k);
		sort.sort(arr);

		ArrayPrinter.print(arr);
	}

	/**
	 * Constructor with {@code k} specified.
	 *
	 * @param k The upper limit of the array elements.
	 */
	public CountingSort(int k) {
		this.k = k;
	}

	/**
	 * Counting sort.
	 *
	 * @param arr Integer array to be sorted.
	 */
	@Override
	public void sort(int[] arr) {
		int[] sortedArr = new int[arr.length];
		countingSort(arr, sortedArr, k);
		System.arraycopy(sortedArr, 0, arr, 0, sortedArr.length);
	}

	/**
	 * Counting sort.
	 *
	 * @param arr       The array to be sorted.
	 * @param sortedArr The sorted array.
	 * @param k         The upper limit of the array elements.
	 */
	public void countingSort(int[] arr, int[] sortedArr, int k) {
		countingSortWithIndices(arr, sortedArr, k, null);
	}

	/**
	 * Counting sort with indices.
	 *
	 * @param arr       The array to be sorted.
	 * @param sortedArr The sorted array.
	 * @param k         The upper limit of the array elements.
	 * @param indices   The indices of the sorted elements in original array.
	 */
	public void countingSortWithIndices(int[] arr, int[] sortedArr, int k, int[] indices) {
		int[] countingArr = new int[k];
		for (int item : arr) {
			countingArr[item]++;
		}
		// countingArr[i] now contains the number of elements equal to i.
		for (int i = 1; i < k; i++) {
			countingArr[i] += countingArr[i - 1];
		}
		//countingArr[i] now contains the number of elements less than or equal to i.
		for (int i = arr.length - 1; i >= 0; i--) {
			sortedArr[countingArr[arr[i]] - 1] = arr[i];
			if (indices != null) {
				indices[countingArr[arr[i]] - 1] = i;
			}
			countingArr[arr[i]]--;
		}
	}

	// The upper limit of the array elements.
	private final int k;
}