/**
 * 
 */
package com.ucas.algorithms.sort;

import com.ucas.algorithms.sort.utils.ArrayPrint;
import com.ucas.algorithms.sort.utils.IntegerArrayGenerator;
import com.ucas.algorithms.sort.utils.TimeElapse;

/**
 * @author wjg
 * @version 0.0.1
 */
public class MergeSort {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		int[] arr = IntegerArrayGenerator.fixedArray();
		int[] arr = IntegerArrayGenerator.randomArray(10000, 10000);
		ArrayPrint.print(arr);
		
		MergeSort sort = new MergeSort();
		TimeElapse timeElapse = new TimeElapse();
		timeElapse.start();
		
		sort.mergeSort(arr);
		timeElapse.stop();
		timeElapse.showElapsedTime();
		
		ArrayPrint.print(arr);
	}
	
	/**
	 * 归并排序，排序结果仍保存于arr数组中。
	 * @param arr 需要排序的数组
	 */
	public void mergeSort(int[] arr) {
		mergeSort(arr, 0, arr.length - 1);
	}
	
	/**
	 * 归并排序，排序arr数组中[p..r]的子数组，排序结果仍保存于arr数组中。
	 * @param arr 需要排序的数组
	 * @param p 待排序数组的起始下标
	 * @param r 待排序数组的终止下标
	 */
	public void mergeSort(int[] arr, int p, int r) {
		if (p < r) {
			int q = (p + r) / 2;
			mergeSort(arr, p, q);
			mergeSort(arr, q + 1, r);
			merge(arr, p, q, r);
		}
	}

	/**
	 * 归并已排序的两个子序列。
	 * @param arr 待归并的序列
	 * @param p 第一个子序列的起始下标
	 * @param q 第一个子序列的结束下标
	 * @param r 第二个子序列的结束下标
	 */
	public void merge(int[] arr, int p, int q, int r) {
		int n1 = q - p + 1;
		int n2 = r - q;
		int[] arrL = new int[n1 + 1];
		int[] arrR = new int[n2 + 1];
		for (int i=0; i<n1; i++) {
			arrL[i] = arr[p + i];
		}
		for (int i=0; i<n2; i++) {
			arrR[i] = arr[q + 1 + i];
		}
		arrL[n1] = Integer.MAX_VALUE;
		arrR[n2] = Integer.MAX_VALUE;
		int i = 0;
		int j = 0;
		for (int k = p; k <= r; k++) {
			if (arrL[i] <= arrR[j]) {
				arr[k] = arrL[i];
				i++;
			}
			else {
				arr[k] = arrR[j];
				j++;
			}
		}
	}

}
