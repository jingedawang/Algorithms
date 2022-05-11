/**
 * Copyright 2022 jingedawang
 */
package divideandconquer;

import utils.ArrayPrinter;
import utils.ArrayGenerator;
import utils.Values3;

/**
 * Find maximum subarray problem.
 *
 * Find a subarray of an array whose elements have the biggest value when summed up.
 */
public class FindMaximumSubarray {

	/**
	 * Demo code.
	 */
	public static void main(String[] args) {
		int[] arr = ArrayGenerator.randomArray(-10, 10, 20);
		System.out.println("Find the maximum subarray of this array:");
		ArrayPrinter.print(arr);

		FindMaximumSubarray findMaximumSubarray = new FindMaximumSubarray();
		Values3<Integer, Integer, Integer> values = findMaximumSubarray.findMaximumSubarray(arr, 0, arr.length - 1);
		System.out.println();
		System.out.println("The maximum subarray is:");
		ArrayPrinter.print(arr, values.value1, values.value2 - values.value1 + 1);
		System.out.println("Values in this subarray sum up to " + values.value3 + ".");
	}

	/**
	 * Find the maximum subarray in the given range.
	 * <p>
	 * This method uses the divided and conquer strategy and implemented in a recursive way.
	 *
	 * @param arr  The sequence to be handled.
	 * @param low  The start index of the range.
	 * @param high The end index of the range.
	 * @return A three value tuple, in which the values represent the start index, end index and the sum of the maximum
	 * subarray.
	 */
	public Values3<Integer, Integer, Integer> findMaximumSubarray(int[] arr, int low, int high) {
		// Recursive termination condition.
		if (high == low) {
			return new Values3<>(low, high, arr[low]);
		}
		// Divide
		int mid = (low + high) / 2;
		Values3<Integer, Integer, Integer> leftResult = findMaximumSubarray(arr, low, mid);
		Values3<Integer, Integer, Integer> rightResult = findMaximumSubarray(arr, mid + 1, high);
		Values3<Integer, Integer, Integer> crossResult = findMaxCrossingSubarray(arr, low, mid, high);
		if (leftResult.value3 >= rightResult.value3 && leftResult.value3 >= crossResult.value3) {
			// The maximum subarray is located on the left side.
			return leftResult;
		} else if (rightResult.value3 >= leftResult.value3 && rightResult.value3 >= crossResult.value3) {
			// The maximum subarray is located on the right side.
			return rightResult;
		} else {
			// The maximum subarray is crossing the middle point.
			return crossResult;
		}
	}

	/**
	 * Find the maximum subarray which crossing the middle point.
	 *
	 * @param arr  The sequence to be handled.
	 * @param low  The start index of the range.
	 * @param mid  The index of the middle point.
	 * @param high The end index of the range.
	 * @return A three value tuple, in which the values represent the start index, end index and the sum of the maximum
	 * subarray which crossing the middle point.
	 */
	private Values3<Integer, Integer, Integer> findMaxCrossingSubarray(int[] arr, int low, int mid, int high) {
		int leftSum = Integer.MIN_VALUE;
		int sum = 0;
		int maxLeft = mid;
		for (int i = mid; i >= low; i--) {
			sum += arr[i];
			if (sum > leftSum) {
				leftSum = sum;
				maxLeft = i;
			}
		}
		int rightSum = Integer.MIN_VALUE;
		sum = 0;
		int maxRight = mid + 1;
		for (int j = mid + 1; j <= high; j++) {
			sum += arr[j];
			if (sum > rightSum) {
				rightSum = sum;
				maxRight = j;
			}
		}
		return new Values3<>(maxLeft, maxRight, leftSum + rightSum);
	}

}