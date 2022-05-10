/**
 * Copyright 2022 jingedawang
 */
package select;

import utils.ArrayGenerator;
import utils.ArrayPrinter;

/**
 * Quick select algorithm.
 *
 * This select algorithm is a variant of quick sort algorithm by always cutting half of the branch when dividing.
 */
public class QuickSelect implements Select {

	/**
	 * Demo code.
	 */
	public static void main(String[] args) {
		int[] arr = ArrayGenerator.fixedArray();
		System.out.println("Let's select items from this array:");
		ArrayPrinter.print(arr);

		Select select = new QuickSelect();
		System.out.println();
		int result = select.select(arr, 4);
		System.out.println("The 4-th smallest number is " + result + ".");
	}

	/**
	 * Quick select.
	 *
	 * @param arr Integer array from which select.
	 * @param i   The order of the element to be selected.
	 * @return The selected element.
	 */
	@Override
	public int select(int[] arr, int i) {
		return quickSelect(arr, 0, arr.length - 1, i);
	}

	/**
	 * The recursive procedure in quick select.
	 *
	 * @param arr The array from which select.
	 * @param p   The start index of the sub-array from which select.
	 * @param r   The end index of the sub-array from which select.
	 * @param i   The order of the element to be selected.
	 * @return The selected element.
	 */
	protected int quickSelect(int[] arr, int p, int r, int i) {
		if (i < 0 || i >= arr.length) {
			throw new IndexOutOfBoundsException("Selected index " + i + " is out of array bound.");
		}
		if (p == r) {
			return arr[p];
		}
		int q = partition(arr, p, r);
		int k = q - p;
		if (k == i) {
			return arr[q];
		} else if (k > i) {
			return quickSelect(arr, p, q - 1, i);
		} else {
			return quickSelect(arr, q + 1, r, i - k - 1);
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