/**
 * 
 */
package com.ucas.algorithms.sort.utils;

import java.util.Random;

/**
 * @author wjg
 * @version 0.0.1
 *
 */
public class IntegerArrayGenerator {

	private static int[] A = new int[] {4, 8, 1, 2, 0, 6, 5, 1, 9, 3};
	
	public static int[] fixedArray() {
		return A.clone();
	}
	
	public static int[] randomArray() {
		return randomArray(10);
	}
	
	public static int[] randomArray(int upperLimit) {
		return randomArray(upperLimit, 10);
	}
	
	public static int[] randomArray(int upperLimit, int size) {
		Random random = new Random(System.currentTimeMillis());
		int[] randomArray = new int[size];
		for (int i=0; i<randomArray.length; i++) {
			randomArray[i] = random.nextInt(upperLimit);
		}
		return randomArray;
	}
	
}
