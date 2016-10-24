package com.ucas.algorithms.sort;

import com.ucas.algorithms.utils.ArrayPrint;
import com.ucas.algorithms.utils.IntegerArrayGenerator;
import com.ucas.algorithms.utils.TimeElapse;

/**
 * @author wjg
 * @version 0.0.1
 *
 */
public class InsertionSort {

	public static void main(String[] args) {
		
//		int[] arr = IntegerArrayGenerator.fixedArray();
		int[] arr = IntegerArrayGenerator.randomArray(10000, 10000);
		ArrayPrint.print(arr);
		
		InsertionSort sort = new InsertionSort();
		TimeElapse timeElapse = new TimeElapse();
		timeElapse.start();
		sort.insertionSort(arr);
		timeElapse.stop();
		timeElapse.showElapsedTime();
		
		ArrayPrint.print(arr);

	}
	
	/**
	 * 插入排序，排序结果仍保存于arr数组中。
	 * @param arr 需要排序的数组
	 */
	public void insertionSort(int[] arr) {
		for (int j = 1; j < arr.length; j++) {
			int key = arr[j];
			//Insert arr[j] into the sorted sequence arr[1..j-1].
			int i = j - 1;
			while (i >= 0 && arr[i] > key) {
				arr[i + 1] = arr[i];
				i--;
			}
			arr[i + 1] = key;
		}
	}

}
