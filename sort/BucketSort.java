/**
 * 
 */
package sort;

import utils.ArrayPrinter;
import utils.ArrayGenerator;

/**
 * @author wjg
 *
 */
public class BucketSort {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		double[] arr = ArrayGenerator.randomArrayDouble();
		ArrayPrinter.print(arr);
		
		BucketSort sort = new BucketSort();
		sort.bucketSort(arr);
		
		ArrayPrinter.print(arr);
	}
	
	/**
	 * 桶排序中使用的链表数据结构
	 * @author wjg
	 *
	 */
	class Node {
		double value;
		Node next;
	}
	
	/**
	 * 使用桶排序算法对给定的数组排序
	 * @param arr 需要排序的数组
	 */
	public void bucketSort(double[] arr) {
		int n = arr.length;
		Node[] B = new Node[n];
		for (int i=0; i<n; i++) {
			B[i] = new Node();
		}
		for (int i=0; i<n; i++) {
			Node node = new Node();
			node.value = arr[i];
			int index = (int) (n * arr[i]);
			node.next = B[index].next;
			B[index].next = node;
		}
		InsertionSort insertionSort = new InsertionSort();
		int arrIndex = 0;
		for (int i=0; i<n; i++) {
			Node head = B[i];
			int length = 0;
			while (head.next != null) {
				head = head.next;
				length++;
			}
			double[] bucketArr = new double[length];
			head = B[i];
			for (int j=0; j<length; j++) {
				head = head.next;
				bucketArr[j] = head.value;
			}
			insertionSort.insertionSort(bucketArr);
			for (int j=0; j<bucketArr.length; j++) {
				arr[arrIndex++] = bucketArr[j];
			}
		}
		
	}

}
