/**
 * Copyright 2020 jingedawang
 */
package math;

/**
 * <h3>Arithmetic functions</h3>
 */
public class Arithmetic {
	/**
	 * Power operation, only integer to the integer power is supported.
	 *
	 * @param a base number
	 * @param b exponent
	 * @return The a to the bth power
	 */
	public static int pow(int a, int b) {
		if (b < 0) {
			throw new IllegalArgumentException("The second argument of method pow must be positive.");
		}
		int result = 1;
		for (int i = 0; i < b; i++) {
			result *= a;
		}
		return result;
	}

}