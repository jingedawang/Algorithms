/**
 * Copyright 2022 jingedawang
 */
package randomize;

import java.util.Random;

import utils.ArrayPrinter;
import utils.ArrayGenerator;
import utils.Seed;

/**
 * A utility class used for randomizing arrays.
 */
public class RandomizeArray {

	/**
	 * Demo code.
	 */
	public static void main(String[] args) {
		int[] arr = ArrayGenerator.fixedArray();
		System.out.println("Given an array:");
		ArrayPrinter.print(arr);

		randomizeInPlace(arr);
		System.out.println();
		System.out.println("Randomize the array to:");
		ArrayPrinter.print(arr);
	}

	/**
	 * Randomize an array in place.
	 * <p>
	 * This method uses the classic Fisher-Yates shuffle algorithm.
	 *
	 * @param arr The array to be randomized.
	 */
	public static void randomizeInPlace(int[] arr) {
		int n = arr.length;
		Random random = new Random(Seed.next());
		for (int i = 0; i < n; i++) {
			int randomIndex = random.nextInt(n - i) + i;
			int temp = arr[i];
			arr[i] = arr[randomIndex];
			arr[randomIndex] = temp;
		}
	}

}