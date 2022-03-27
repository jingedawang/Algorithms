/**
 * Copyright 2020 jingedawang
 */
package utils;

import java.util.Random;

/**
 * <h3>Array generator</h3>
 */
public class ArrayGenerator {

	/**
	 * Get the built-in fixed array of length 10.
	 *
	 * @return The fixed array.
	 */
	public static int[] fixedArray() {
		return A.clone();
	}

	/**
	 * Get a randomly generated array of length 10 with upper limit 10.
	 *
	 * @return The random array.
	 */
	public static int[] randomArray() {
		return randomArray(10);
	}

	/**
	 * Get a randomly generated array of length 10 with specified upper limit.
	 *
	 * @param upperLimit The upper limit of elements.
	 * @return The random array.
	 */
	public static int[] randomArray(int upperLimit) {
		return randomArray(upperLimit, 10);
	}

	/**
	 * Get a randomly generated array of specified length with specified upper limit.
	 *
	 * @param upperLimit The upper limit of elements.
	 * @param size       The length of the array.
	 * @return The random array.
	 */
	public static int[] randomArray(int upperLimit, int size) {
		Random random = new Random(Seed.next());
		int[] randomArray = new int[size];
		for (int i = 0; i < randomArray.length; i++) {
			randomArray[i] = random.nextInt(upperLimit);
		}
		return randomArray;
	}

	/**
	 * Get a randomly generated array of specified length with specified upper limit and lower limit.
	 *
	 * @param lowerLimit The lower limit of elements.
	 * @param upperLimit The upper limit of elements.
	 * @param size       The length of the array.
	 * @return The random array.
	 */
	public static int[] randomArray(int lowerLimit, int upperLimit, int size) {
		int[] arr = randomArray(upperLimit - lowerLimit, size);
		for (int i = 0; i < arr.length; i++) {
			arr[i] += lowerLimit;
		}
		return arr;
	}

	/**
	 * Get a randomly generated double array of length 10 between 0 and 1.
	 *
	 * @return The random double array.
	 */
	public static double[] randomArrayDouble() {
		int[] arr = randomArray();
		double[] arrDouble = new double[arr.length];
		for (int i = 0; i < arr.length; i++) {
			arrDouble[i] = arr[i] / 10.0;
		}
		return arrDouble;
	}

	// Built-in fixed array
	private static int[] A = new int[]{4, 8, 1, 2, 0, 6, 5, 1, 9, 3};

}