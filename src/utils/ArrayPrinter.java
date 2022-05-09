/**
 * Copyright 2022 jingedawang
 */
package utils;

/**
 * Array printer.
 */
public class ArrayPrinter {

	/**
	 * Print all the elements in the array.
	 *
	 * @param arr The integer array to be printed.
	 */
	public static void print(int[] arr) {
		System.out.print("[");
		int i = 0;
		for (; i < arr.length - 1; i++) {
			System.out.print(arr[i] + ", ");
		}
		System.out.println(arr[i] + "]");
	}

	/**
	 * Print all the elements in the array.
	 *
	 * @param arr The long array to be printed.
	 */
	public static void print(long[] arr) {
		System.out.print("[");
		int i = 0;
		for (; i < arr.length - 1; i++) {
			System.out.print(arr[i] + ", ");
		}
		System.out.println(arr[i] + "]");
	}

	/**
	 * Print all the elements in the array.
	 *
	 * @param arr The double array to be printed.
	 */
	public static void print(double[] arr) {
		System.out.print("[");
		int i = 0;
		for (; i < arr.length - 1; i++) {
			System.out.print(arr[i] + ", ");
		}
		System.out.println(arr[i] + "]");
	}

	/**
	 * Print all the elements in the array.
	 *
	 * @param arr The char array to be printed.
	 */
	public static void print(char[] arr) {
		System.out.print("[");
		int i = 0;
		for (; i < arr.length - 1; i++) {
			System.out.print(arr[i] + ", ");
		}
		System.out.println(arr[i] + "]");
	}

	/**
	 * Print all the elements in the array.
	 *
	 * @param arr The array to be printed.
	 */
	public static <T> void print(T[] arr) {
		System.out.print("[");
		int i = 0;
		for (; i < arr.length - 1; i++) {
			System.out.print(arr[i] + ", ");
		}
		System.out.println(arr[i] + "]");
	}

	/**
	 * Print the specified part of the array.
	 *
	 * @param arr    The integer array to be printed.
	 * @param index  The index position where starting the print.
	 * @param length The length of the print.
	 */
	public static void print(int[] arr, int index, int length) {
		System.out.print("[");
		int i = 0;
		for (; i < length - 1 && index + i < arr.length - 1; i++) {
			System.out.print(arr[index + i] + ", ");
		}
		System.out.println(arr[index + i] + "]");
	}
}