package com.ucas.algorithms.sort.utils;

/**
 * @author wjg
 * @version 0.0.1
 *
 */
public class ArrayPrint {

	public static void print(int[] arr) {
		System.out.print("[");
		int i=0;
		for (; i<arr.length-1; i++) {
			System.out.print(arr[i] + ", ");
		}
		System.out.println(arr[i] + "]");
	}
	
}
