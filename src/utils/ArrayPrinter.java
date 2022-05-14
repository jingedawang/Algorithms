/**
 * Copyright 2022 jingedawang
 */
package utils;

import java.util.Arrays;

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
		Integer[] integerArr = Arrays.stream(arr).boxed().toArray(Integer[]::new);
		print(integerArr);
	}

	/**
	 * Print all the elements in the array.
	 *
	 * @param arr The long array to be printed.
	 */
	public static void print(long[] arr) {
		Long[] longArr = Arrays.stream(arr).boxed().toArray(Long[]::new);
		print(longArr);
	}

	/**
	 * Print all the elements in the array.
	 *
	 * @param arr The double array to be printed.
	 */
	public static void print(double[] arr) {
		Double[] doubleArr = Arrays.stream(arr).boxed().toArray(Double[]::new);
		print(doubleArr);
	}

	/**
	 * Print all the elements in the array.
	 *
	 * @param arr The char array to be printed.
	 */
	public static void print(char[] arr) {
		Character[] charArr = new Character[arr.length];
		for (int i = 0; i < arr.length; i++) {
			charArr[i] = arr[i];
		}
		print(charArr);
	}

	/**
	 * Print all the elements in the array.
	 *
	 * @param arr The array to be printed.
	 */
	public static <T> void print(T[] arr) {
		print(arr, 0, arr.length);
	}

	/**
	 * Print the specified part of the array.
	 *
	 * @param arr    The integer array to be printed.
	 * @param index  The index where the printing started.
	 * @param length The length of the subarray to be printed.
	 */
	public static void print(int[] arr, int index, int length) {
		Integer[] integerArr = Arrays.stream(arr).boxed().toArray(Integer[]::new);
		print(integerArr, index, length);
	}

	/**
	 * Print the specified part of the array.
	 *
	 * @param arr    The array to be printed.
	 * @param index  The index where the printing started.
	 * @param length The length of the subarray to be printed.
	 */
	public static <T> void print(T[] arr, int index, int length) {
		System.out.print("[");
		int i = 0;
		for (; i < length - 1 && index + i < arr.length - 1; i++) {
			System.out.print(arr[index + i] + ", ");
		}
		System.out.println(arr[index + i] + "]");
	}
}