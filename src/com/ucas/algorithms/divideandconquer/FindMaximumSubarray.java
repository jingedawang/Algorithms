/**
 * 
 */
package com.ucas.algorithms.divideandconquer;

import com.ucas.algorithms.utils.Values3;

/**
 * @author jinge
 *
 */
public class FindMaximumSubarray {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public Values3<Integer, Integer, Integer> findMaximumSubarray(int[] arr, int low, int high) {
		if (high == low) {
			return new Values3<Integer, Integer, Integer>(low, high, arr[low]);
		}
		int mid = (low + high) / 2;
		Values3<Integer, Integer, Integer> leftResult = findMaximumSubarray(arr, low, mid);
		Values3<Integer, Integer, Integer> rightResult = findMaximumSubarray(arr, mid + 1, high);
		Values3<Integer, Integer, Integer> crossResult = findMaxCrossingSubarray(arr, low, mid, high);
		if (leftResult.value3 >= rightResult.value3 && leftResult.value3 >= crossResult.value3) {
			return leftResult;
		}
		else if (rightResult.value3 >= leftResult.value3 && rightResult.value3 >= crossResult.value3) {
			return rightResult;
		}
		return crossResult;
	}

	public Values3<Integer, Integer, Integer> findMaxCrossingSubarray(
			int[] arr, int low, int mid, int high) {
		int leftSum = Integer.MIN_VALUE;
		int sum = 0;
		int maxLeft = mid;
		for (int i=mid; i >= low; i--) {
			sum += arr[i];
			if (sum > leftSum) {
				leftSum = sum;
				maxLeft = i;
			}
		}
		int rightSum = Integer.MIN_VALUE;
		sum = 0;
		int maxRight = mid + 1;
		for (int j=mid+1; j <= high; j++) {
			sum += arr[j];
			if (sum > rightSum) {
				rightSum = sum;
				maxRight = j;
			}
		}
		return new Values3<Integer, Integer, Integer>(maxLeft, maxRight, leftSum + rightSum);
	}
	
}
