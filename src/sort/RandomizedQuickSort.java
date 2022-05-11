/**
 * Copyright 2022 jingedawang
 */
package sort;

import java.util.Random;

import utils.ArrayGenerator;
import utils.ArrayPrinter;
import utils.Seed;

/**
 * Randomized quick sort algorithm.
 */
public class RandomizedQuickSort extends QuickSort {

	public static void main(String[] args) {
		int[] arr = ArrayGenerator.fixedArray();
		System.out.println("Original array:");
		ArrayPrinter.print(arr);

		Sort sort = new RandomizedQuickSort();
		sort.sort(arr);
		System.out.println();
		System.out.println("Sorted by randomized quick sort:");
		ArrayPrinter.print(arr);
	}

	/**
	 * Partition the array into two parts with randomly selected pivot.
	 * <p>
	 * After this method, elements greater than pivot are put right, others are put left.
	 *
	 * @param arr The array to be sorted.
	 * @param p   The start index of the sub-array to be sorted.
	 * @param r   The end index of the sub-array to be sorted.
	 * @return The index of the pivot after partition.
	 */
	@Override
	protected int partition(int[] arr, int p, int r) {
		int i = new Random(Seed.next()).nextInt(r - p + 1) + p;
		int temp = arr[r];
		arr[r] = arr[i];
		arr[i] = temp;
		return super.partition(arr, p, r);
	}

}