/**
 * Copyright 2022 jingedawang
 */
package utils;

import java.util.Random;

/**
 * Array generator.
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
	 * @param upperLimit The upper limit of elements (exclusive).
	 * @return The random array.
	 */
	public static int[] randomArray(int upperLimit) {
		return randomArray(upperLimit, 10);
	}

	/**
	 * Get a randomly generated array of specified length with specified upper limit.
	 *
	 * @param upperLimit The upper limit of elements (exclusive).
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
	 * @param lowerLimit The lower limit of elements (inclusive).
	 * @param upperLimit The upper limit of elements (exclusive).
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
	 * Get a randomly generated double array of specified length with values between 0.0 (inclusive) and 1.0 (exclusive).
	 *
	 * @return The random double array.
	 */
	public static double[] randomDoubleArray(int size) {
		Random random = new Random(Seed.next());
		double[] arr = new double[size];
		for (int i = 0; i < size; i++) {
			arr[i] = random.nextDouble();
		}
		return arr;
	}

	// Built-in fixed array
	private static final int[] A = new int[]{4, 8, 1, 2, 0, 6, 5, 1, 9, 3};

}