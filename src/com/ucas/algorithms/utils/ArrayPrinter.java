package com.ucas.algorithms.utils;

/**
 * @author wjg
 * @version 0.0.1
 *
 */
public class ArrayPrinter {

	/**
	 * 打印数组中的全部元素。
	 * @param arr 待打印的数组
	 */
	public static void print(int[] arr) {
		System.out.print("[");
		int i=0;
		for (; i<arr.length-1; i++) {
			System.out.print(arr[i] + ", ");
		}
		System.out.println(arr[i] + "]");
	}
	
	/**
	 * 打印数组中的全部元素。
	 * @param arr 待打印的数组
	 */
	public static void print(long[] arr) {
		System.out.print("[");
		int i=0;
		for (; i<arr.length-1; i++) {
			System.out.print(arr[i] + ", ");
		}
		System.out.println(arr[i] + "]");
	}
	
	/**
	 * 打印数组中的全部元素。
	 * @param arr 待打印的数组
	 */
	public static void print(double[] arr) {
		System.out.print("[");
		int i=0;
		for (; i<arr.length-1; i++) {
			System.out.print(arr[i] + ", ");
		}
		System.out.println(arr[i] + "]");
	}
	
	/**
	 * 打印数组中的全部元素。
	 * @param arr 待打印的数组
	 */
	public static <T> void print(T[] arr) {
		System.out.print("[");
		int i=0;
		for (; i<arr.length-1; i++) {
			System.out.print(arr[i] + ", ");
		}
		System.out.println(arr[i] + "]");
	}
	
	/**
	 * 打印数组中指定下标开始，指定长度的子数组。
	 * @param arr 待打印的数组
	 * @param index 起始下标
	 * @param length 打印的长度
	 */
	public static void print(int[] arr, int index, int length) {
		System.out.print("[");
		int i=0;
		for (; i < length - 1 && index + i < arr.length - 1; i++) {
			System.out.print(arr[index + i] + ", ");
		}
		System.out.println(arr[index + i] + "]");
	}
	
}
