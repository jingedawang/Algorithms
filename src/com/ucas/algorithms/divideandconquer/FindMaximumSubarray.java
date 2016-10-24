/**
 * 
 */
package com.ucas.algorithms.divideandconquer;

import com.ucas.algorithms.utils.ArrayPrint;
import com.ucas.algorithms.utils.IntegerArrayGenerator;
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
		
		int[] arr = IntegerArrayGenerator.randomArray(-10, 10, 20);
		ArrayPrint.print(arr);
		FindMaximumSubarray findMaximumSubarray = new FindMaximumSubarray();
		Values3<Integer, Integer, Integer> values = findMaximumSubarray.findMaximumSubarray(arr, 0, arr.length - 1);
		ArrayPrint.print(arr, values.value1, values.value2 - values.value1 + 1);
		System.out.println(values.value3);
	}

	/**
	 * 使用分治法求解给定数组中给定范围的最大子数组问题。
	 * @param arr 待求解的数组
	 * @param low 起始下标
	 * @param high 结束下标
	 * @return 三个返回值，依次是最大子数组的起始下标、最大子数组的结束下标、最大子数组的和
	 */
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
