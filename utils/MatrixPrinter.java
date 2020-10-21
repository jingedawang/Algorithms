/**
 * Copyright 2020 jingedawang
 */
package utils;

import matrix.Matrix;

/**
 * <h3>Matrix printer</h3>
 */
public class MatrixPrinter {

	/**
	 * Print the matrix in a readable format.
	 *
	 * @param m The matrix to be printed.
	 */
	public static void print(Matrix m) {
		System.out.print("[");
		for (int i = 0; i < m.rows(); i++) {
			for (int j = 0; j < m.columns() - 1; j++) {
				System.out.print(m.value()[i][j] + ",");
			}
			System.out.println(m.value()[i][m.columns() - 1] + (i == m.rows() - 1 ? "]" : ""));
		}
	}

}