/**
 * Copyright 2020 jingedawang
 */
package select;

import utils.ArrayGenerator;
import utils.ArrayPrinter;

import java.util.Arrays;

/**
 * <h3>BFPRT algorithm</h3>
 * <p>
 * This algorithm is also referenced as median of medians algorithm.
 */
public class BFPRT implements Select {

	/**
	 * Test code.
	 */
	public static void main(String[] args) {
		int[] arr = ArrayGenerator.fixedArray();
//		int[] arr = ArrayGenerator.randomArray(20, 20);
		int[] sortedArr = arr.clone();
		Arrays.sort(sortedArr);
		System.out.println("Original array:");
		ArrayPrinter.print(arr);
		System.out.println("Sorted array:");
		ArrayPrinter.print(sortedArr);

		Select select = new BFPRT();
		for (int k = 0; k < arr.length; k++) {
			int result = select.select(arr, k);
			System.out.println("The " + k + "-th smallest number is " + result);
		}
	}

	/**
	 * Select interface for integer array.
	 *
	 * @param arr Integer array from which select.
	 * @param i   The order of the element to be selected.
	 * @return The selected element.
	 */
	@Override
	public int select(int[] arr, int i) {
		return bfprt(arr.clone(), 0, arr.length - 1, i);
	}

	/**
	 * The recursive procedure in BFPRT.
	 *
	 * @param arr The array from which select.
	 * @param p   The start index of the sub-array from which select.
	 * @param r   The end index of the sub-array from which select.
	 * @param i   The order of the element to be selected.
	 * @return The selected element.
	 */
	protected int bfprt(int[] arr, int p, int r, int i) {
		if (p == r) {
			return arr[p];
		}
		int pivot = medianOfMedians(arr, p, r);
		int[] pivotRange = partition(arr, p, r, pivot);
		if (p + i >= pivotRange[0] && p + i <= pivotRange[1]) {
			return arr[pivotRange[0]];
		} else if (p + i < pivotRange[0]) {
			return bfprt(arr, p, pivotRange[0] - 1, i);
		} else {
			return bfprt(arr, pivotRange[1] + 1, r, i + p - pivotRange[1] - 1);
		}
	}

	/**
	 * Compute the median of the medians of the input array.
	 *
	 * @param arr The array to be computed.
	 * @param p   The start index of the sub-array.
	 * @param r   The end index of the sub-array.
	 * @return The median of the medians of the sub-array.
	 */
	protected int medianOfMedians(int[] arr, int p, int r) {
		int num = r - p + 1;
		int extra = num % 5 == 0 ? 0 : 1;
		int[] medians = new int[num / 5 + extra];
		for (int i = 0; i < medians.length; i++) {
			medians[i] = computeMedian(arr, p + i * 5, Math.min(p + i * 5 + 4, r));
		}
		return bfprt(medians, 0, medians.length - 1, medians.length / 2);
	}

	/**
	 * Partition the array into two parts.
	 * <p>
	 * After this method, elements less than pivot are put left, pivots are put middle, others are put right.
	 *
	 * @param arr The array to be sorted.
	 * @param p   The start index of the sub-array to be sorted.
	 * @param r   The end index of the sub-array to be sorted.
	 * @return A two elements array. The first element indicates the index of the first pivot, the second element
	 * indicates the index of the last pivot.
	 */
	protected int[] partition(int[] arr, int p, int r, int pivot) {
		int small = p - 1;
		int equal = 0;
		int temp;
		for (int j = p; j <= r; j++) {
			if (arr[j] < pivot) {
				small++;
				temp = arr[small];
				arr[small] = arr[j];
				if (equal > 0) {
					arr[j] = arr[small + equal];
					arr[small + equal] = temp;
				} else {
					arr[j] = temp;
				}
			} else if (arr[j] == pivot) {
				equal++;
				temp = arr[j];
				arr[j] = arr[small + equal];
				arr[small + equal] = temp;
			}
		}
		return new int[]{small + 1, small + equal};
	}

	/**
	 * Compute the median of the given array.
	 *
	 * @param arr   Array to be computed.
	 * @param begin The begin index of the range to be computed.
	 * @param end   The end index of the range to be computed.
	 * @return The median of the array in the specified range.
	 */
	private int computeMedian(int[] arr, int begin, int end) {
		Arrays.sort(arr, begin, end + 1);
		return arr[begin + (end - begin) / 2];
	}

}