/**
 * Copyright 2020 jingedawang
 */
package select;

import utils.ArrayGenerator;
import utils.ArrayPrinter;

/**
 * <h3>Quick select algorithm</h3>
 */
public class QuickSelect implements Select {

	/**
	 * Test code.
	 */
	public static void main(String[] args) {
		int[] arr = ArrayGenerator.fixedArray();
//		int[] arr = ArrayGenerator.randomArray(20, 20);
		ArrayPrinter.print(arr);
		int i = 2;

		Select select = new QuickSelect();
		int result = select.select(arr, i);

		System.out.println("The " + i + "-th smallest number is " + result);
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