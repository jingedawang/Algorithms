package com.ucas.algorithms.math;

/**
 * 堆数据结构
 * @author wjg
 * @version 0.0.1
 */
public class Heap {
	
	/**
	 * 实际保存数据的数组
	 */
	public int[] arr;
	
	/**
	 * 当前堆中有效元素的个数
	 */
	public int size;

	/**
	 * 使用给定的数组初始化最大堆
	 * @param arr 用于初始化堆的数组
	 * @return 堆对象
	 */
	public static Heap buildMaxHeap(int[] arr) {		
		Heap heap = new Heap();
		heap.arr = arr;
		heap.size = arr.length;
		for (int i = heap.size / 2 - 1; i >= 0; i--) {
			heap.maxHeapify(i);
		}
		return heap;
	}
	
	/**
	 * 使用给定的数组初始化最小堆
	 * @param arr 用于初始化堆的数组
	 * @return 堆对象
	 */
	public static Heap buildMinHeap(int[] arr) {		
		Heap heap = new Heap();
		heap.arr = arr;
		heap.size = arr.length;
		for (int i = heap.size / 2 - 1; i >= 0; i--) {
			heap.minHeapify(i);
		}
		return heap;
	}

	/**
	 * 维护最大堆由于给定结点导致的不稳定性
	 * @param i 给定结点号
	 */
	public void maxHeapify(int i) {
		int l = left(i);
		int r = right(i);
		int largest;
		if (l < size && arr[l] > arr[i]) {
			largest = l;
		}
		else {
			largest = i;
		}
		if (r < size && arr[r] > arr[largest]) {
			largest = r;
		}
		if (largest != i) {
			int temp = arr[i];
			arr[i] = arr[largest];
			arr[largest] = temp;
			maxHeapify(largest);
		}
	}
	
	/**
	 * 维护最小堆由于给定结点导致的不稳定性
	 * @param i 给定结点号
	 */
	public void minHeapify(int i) {
		int l = left(i);
		int r = right(i);
		int smallest;
		if (l < size && arr[l] < arr[i]) {
			smallest = l;
		}
		else {
			smallest = i;
		}
		if (r < size && arr[r] < arr[smallest]) {
			smallest = r;
		}
		if (smallest != i) {
			int temp = arr[i];
			arr[i] = arr[smallest];
			arr[smallest] = temp;
			minHeapify(smallest);
		}
	}
	
	/**
	 * 计算给定结点的父结点号
	 * @param i 给定结点号
	 * @return 父结点号
	 */
	public int parent(int i) {
		return (i - 1) / 2;
	}

	/**
	 * 计算给定结点的左孩子结点号
	 * @param i 给定结点号
	 * @return 左孩子结点号
	 */
	public int left(int i) {
		return 2 * i + 1;
	}
	
	/**
	 * 计算给定结点的右孩子结点号
	 * @param i 给定结点号
	 * @return 右孩子结点号
	 */
	public int right(int i) {
		return 2 * (i + 1);
	}

}
