/**
 * Copyright 2021 jingedawang
 */
package randomize;

import java.util.Random;

import utils.ArrayPrinter;
import utils.ArrayGenerator;
import utils.Seed;

/**
 * <h3>A util class used for randomizing arrays.</h3>
 */
public class RandomizeArray {

	/**
	 * Test code.
	 */
    public static void main(String[] args) {
        int[] arr = ArrayGenerator.fixedArray();
        ArrayPrinter.print(arr);
        randomizeInPlace(arr);
        ArrayPrinter.print(arr);
    }

    /**
     * Randomize an array in place.
     *
	 * This method uses the classic Fisher-Yates shuffle algorithm.
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