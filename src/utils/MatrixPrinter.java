/**
 * Copyright 2022 jingedawang
 */
package utils;

import matrix.Matrix;

/**
 * Matrix printer.
 */
public class MatrixPrinter {

	/**
	 * Print the matrix in a readable format.
	 *
	 * @param matrix The matrix to be printed.
	 */
	public static <T extends Number> void print(Matrix<T> matrix) {
		double maxValue = MatrixUtils.max(matrix).doubleValue();
		double minValue = MatrixUtils.min(matrix).doubleValue();
		double maxAbsValue = Math.max(Math.abs(maxValue), Math.abs(minValue));
		int digitNumberBeforePoint = String.format("%.0f", maxAbsValue).length();

		for (int i = 0; i < matrix.rows(); i++) {
			if (i == 0) {
				System.out.print('[');
			}
			else {
				System.out.print(' ');
			}
			for (int j = 0; j < matrix.columns(); j++) {
				if (matrix.get(i, j) instanceof Integer) {
					System.out.printf("%" + digitNumberBeforePoint + "d", matrix.get(i, j));
				}
				else {
					System.out.printf("%" + (digitNumberBeforePoint + 3) + ".2f", matrix.get(i, j));
				}
				if (j != matrix.columns() - 1) {
					System.out.print(", ");
				}
			}
			if (i != matrix.rows() - 1) {
				System.out.println();
			}
			else {
				System.out.println(']');
			}
		}
	}

}