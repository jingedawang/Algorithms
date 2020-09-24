/**
 * 
 */
package sort;

import math.Arithmetic;
import utils.ArrayPrinter;
import utils.ArrayGenerator;

/**
 * @author wjg
 *
 */
public class RadixSort {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//进制数
		int k = 10;
		//位数
		int d = 3;
		int[] arr = ArrayGenerator.randomArray(Arithmetic.pow(k, d));
		ArrayPrinter.print(arr);
		
		RadixSort sort = new RadixSort();
		sort.radixSort(arr, d, k);
		
		ArrayPrinter.print(arr);

	}
	
	/**
	 * 使用基数排序算法对给定的数组排序
	 * @param arr 需要排序的数组
	 * @param d 数组中元素的位数
	 */
	public void radixSort(int[] arr, int d) {
		radixSort(arr, d, 10);
	}
	
	/**
	 * 使用基数排序算法对给定的数组排序
	 * @param arr 需要排序的数组
	 * @param d 数组中元素的位数
	 * @param k 数组中元素的进制数，即每一位有多少种取值
	 */
	public void radixSort(int[] arr, int d, int k) {
		int[] digitArr = new int[arr.length];
		CountingSort countingSort = new CountingSort();
		for (int i=0; i<d; i++) {
			for (int j=0; j<arr.length; j++) {
				digitArr[j] = arr[j];
				for (int l=0; l<i; l++) {
					digitArr[j] /= k;
				}
				digitArr[j] %= k;
			}
			int[] order = countingSort.countingSortReturnOrder(digitArr, k);
			int[] temp = new int[arr.length];
			for (int j=0; j<arr.length; j++) {				
				temp[j] = arr[order[j]];
			}
			for (int j=0; j<arr.length; j++) {			
				arr[j] = temp[j];
			}
			
		}
	}

}
