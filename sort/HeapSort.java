package sort;

import math.Heap;
import utils.ArrayPrinter;
import utils.ArrayGenerator;

/**
 * 堆排序算法
 * @author wjg
 * @version 0.0.1
 */
public class HeapSort {

	public static void main(String[] args) {

		int[] arr = ArrayGenerator.fixedArray();
//		int[] arr = IntegerArrayGenerator.randomArray(100, 100);
		ArrayPrinter.print(arr);
		
		HeapSort sort = new HeapSort();
		sort.heapSort(arr);
		
		ArrayPrinter.print(arr);
	}
	
	/**
	 * 使用堆排序算法对给定的数组排序
	 * @param arr 需要排序的数组
	 */
	public void heapSort(int[] arr) {
		Heap heap = Heap.buildMaxHeap(arr);
		for (int i = heap.arr.length - 1; i >= 1; i--) {
			int temp = heap.arr[i];
			heap.arr[i] = heap.arr[0];
			heap.arr[0] = temp;
			heap.size --;
			heap.maxHeapify(0);
		}
	}

}
