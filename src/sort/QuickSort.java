/**
 * Copyright 2020 jingedawang
 */
package sort;

import utils.ArrayPrinter;
import utils.ArrayGenerator;

/**
 * <h3>Quick sort algorithm</h3>
 */
public class QuickSort implements Sort {

	public static void main(String[] args) {
		int[] arr = ArrayGenerator.fixedArray();
//		int[] arr = ArrayGenerator.randomArray(20, 20);
		ArrayPrinter.print(arr);

		Sort sort = new QuickSort();
		sort.sort(arr);

		ArrayPrinter.print(arr);
	}

	/**
	 * Quick sort.
	 *
	 * @param arr Integer array to be sorted.
	 */
	@Override
	public void sort(int[] arr) {
		quickSort(arr, 0, arr.length - 1);
	}

	/**
	 * The recursive procedure in quick sort.
	 *
	 * @param arr The array to be sorted.
	 * @param p   The start index of the sub-array to be sorted.
	 * @param r   The end index of the sub-array to be sorted.
	 */
	protected void quickSort(int[] arr, int p, int r) {
		if (p < r) {
			int q = partition(arr, p, r);
			quickSort(arr, p, q - 1);
			quickSort(arr, q + 1, r);
		}
	}

	/**
	 * Partition the array into two parts.
	 * <p>
	 * After this method, elements greater than pivot are put right, others are put left.
	 *
	 * @param arr The array to be sorted.
	 * @param p   The start index of the sub-array to be sorted.
	 * @param r   The end index of the sub-array to be sorted.
	 * @return The index of the pivot after partition.
	 */
	protected int partition(int[] arr, int p, int r) {
		int x = arr[r];
		int i = p - 1;
		int temp;
		for (int j = p; j < r; j++) {
			if (arr[j] <= x) {
				i++;
				temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
			}
		}
		temp = arr[i + 1];
		arr[i + 1] = arr[r];
		arr[r] = temp;
		return i + 1;
	}

}