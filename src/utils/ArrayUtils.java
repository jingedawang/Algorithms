/**
 * Copyright 2022 jingedawang
 */
package utils;

import java.util.Arrays;

/**
 * Utility class for arrays.
 */
public class ArrayUtils {

	/**
	 * Box the 2-dimensional integer array.
	 *
	 * @param arr The 2-dimensional integer array to be boxed.
	 * @return The boxed {@code Integer[][]} array.
	 */
	public static Integer[][] box(int[][] arr) {
		Integer[][] boxedArr = new Integer[arr.length][];
		for (int i = 0; i < arr.length; i++) {
			boxedArr[i] = Arrays.stream(arr[i]).boxed().toArray(Integer[]::new);
		}
		return boxedArr;
	}

	/**
	 * Box the 2-dimensional double array.
	 *
	 * @param arr The 2-dimensional double array to be boxed.
	 * @return The boxed {@code Double[][]} array.
	 */
	public static Double[][] box(double[][] arr) {
		Double[][] boxedArr = new Double[arr.length][];
		for (int i = 0; i < arr.length; i++) {
			boxedArr[i] = Arrays.stream(arr[i]).boxed().toArray(Double[]::new);
		}
		return boxedArr;
	}

	/**
	 * Convert {@code Number[][]} array to {@code Double[][]} array.
	 *
	 * @param arr The array to be be converted.
	 * @return The converted {@code Double[][]} array.
	 */
	public static Double[][] toDouble(Number[][] arr) {
		Double[][] doubleArr = new Double[arr.length][];
		for (int i = 0; i < arr.length; i++) {
			doubleArr[i] = Arrays.stream(arr[i]).mapToDouble(Number::doubleValue).boxed().toArray(Double[]::new);
		}
		return doubleArr;
	}

	/**
	 * Fill the 2-dimensional array with the given value.
	 *
	 * @param arr The 2-dimensional array.
	 * @param value The value used to fill the array.
	 * @param <T> The type of the underlying elements of the array.
	 */
	public static <T> void fill(Number[][] arr, T value) {
		for (Number[] row : arr) {
			Arrays.fill(row, value);
		}
	}

}