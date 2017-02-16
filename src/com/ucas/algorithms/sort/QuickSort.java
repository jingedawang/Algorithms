/**
 * 
 */
package com.ucas.algorithms.sort;

import java.util.Random;

import com.ucas.algorithms.utils.ArrayPrinter;
import com.ucas.algorithms.utils.IntegerArrayGenerator;
import com.ucas.algorithms.utils.Seed;

/**
 * 快速排序算法
 * 
 * @author wjg
 *
 */
public class QuickSort {

	public static void main(String[] args) {
		
		int[] arr = IntegerArrayGenerator.fixedArray();
//		int[] arr = IntegerArrayGenerator.randomArray(20, 20);
		ArrayPrinter.print(arr);
		
		QuickSort sort = new QuickSort();
		sort.quickSort(arr, 0, arr.length - 1);
		
		ArrayPrinter.print(arr);
		
		int[] arr2 = IntegerArrayGenerator.fixedArray();
//		int[] arr2 = IntegerArrayGenerator.randomArray(20, 20);
		ArrayPrinter.print(arr2);
		
		sort.randomizedQuickSort(arr2, 0, arr.length - 1);
		
		ArrayPrinter.print(arr2);
	}
	
	/**
	 * 使用快速排序算法对给定的数组排序
	 * @param arr 需要排序的数组
	 * @param p 指定排序区间的起始下标
	 * @param r 指定排序区间的结束下标
	 */
	public void quickSort(int[] arr, int p, int r) {
		if (p < r) {
			int q = partition(arr, p, r);
			quickSort(arr, p, q - 1);
			quickSort(arr, q + 1, r);
		}
	}
	
	/**
	 * 快速排序中的一趟划分
	 * @param arr 需要排序的数组
	 * @param p 指定排序区间的起始下标
	 * @param r 指定排序区间的结束下标
	 * @return 划分后主元的位置
	 */
	public int partition(int[] arr, int p, int r) {
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
	
	/**
	 * 使用随机化快速排序算法对给定的数组排序
	 * @param arr 需要排序的数组
	 * @param p 指定排序区间的起始下标
	 * @param r 指定排序区间的结束下标
	 */
	public void randomizedQuickSort(int[] arr, int p, int r) {
		if (p < r) {
			int q = randomizedPartition(arr, p, r);
			randomizedQuickSort(arr, p, q - 1);
			randomizedQuickSort(arr, q + 1, r);
		}
	}
	
	/**
	 * 随机化快速排序中的一趟划分
	 * @param arr 需要排序的数组
	 * @param p 指定排序区间的起始下标
	 * @param r 指定排序区间的结束下标
	 * @return 划分后主元的位置
	 */
	public int randomizedPartition(int[] arr, int p, int r) {
		int i = new Random(Seed.next()).nextInt(r - p + 1) + p;
		int temp = arr[r];
		arr[r] = arr[i];
		arr[i] = temp;
		return partition(arr, p, r);
	}

}
