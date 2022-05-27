/**
 * Copyright 2022 jingedawang
 */
package utils;

import matrix.Matrix;

/**
 * Utility class for {@link Matrix}.
 */
public class MatrixUtils {

	/**
	 * Get the maximum value in the matrix.
	 *
	 * @param matrix The matrix object.
	 * @param <T> The type of the underlying elements of the matrix.
	 * @return The maximum value in the matrix.
	 */
	public static <T extends Number> T max(Matrix<T> matrix) {
		Number max;
		if (matrix.get(0, 0) instanceof Integer) {
			max = Integer.MIN_VALUE;
		}
		else {
			max = Double.MIN_VALUE;
		}
		for (int i = 0; i < matrix.rows(); i++) {
			for (int j = 0; j < matrix.columns(); j++) {
				if (matrix.get(0, 0) instanceof Integer) {
					max = Math.max((Integer) max, (Integer) matrix.get(i, j));
				}
				else {
					max = Math.max((Double) max, (Double) matrix.get(i, j));
				}
			}
		}
		return (T) max;
	}

	/**
	 * Get the minimum value in the matrix.
	 *
	 * @param matrix The matrix object.
	 * @param <T> The type of the underlying elements of the matrix.
	 * @return The minimum value in the matrix.
	 */
	public static <T extends Number> T min(Matrix<T> matrix) {
		Number min;
		if (matrix.get(0, 0) instanceof Integer) {
			min = Integer.MAX_VALUE;
		}
		else {
			min = Double.MAX_VALUE;
		}
		for (int i = 0; i < matrix.rows(); i++) {
			for (int j = 0; j < matrix.columns(); j++) {
				if (matrix.get(0, 0) instanceof Integer) {
					min = Math.min((Integer) min, (Integer) matrix.get(i, j));
				}
				else {
					min = Math.min((Double) min, (Double) matrix.get(i, j));
				}
			}
		}
		return (T) min;
	}

}