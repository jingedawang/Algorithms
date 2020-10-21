/**
 * Copyright 2020 jingedawang
 */
package utils;

import java.util.Random;

import matrix.Matrix;

/**
 * <h3>Matrix generator</h3>
 * <p>
 * This class provides different matrix generators.
 */
public class MatrixGenerator {

	/**
	 * Generate a random square matrix with side length specified.
	 *
	 * @param sideLength The side length of the square matrix.
	 * @return The generated random matrix.
	 */
	public static Matrix generateRandomMatrix(int sideLength) {
		return generateRandomMatrix(sideLength, sideLength, 10);
	}

	/**
	 * Generate a random matrix with rows and columns specified.
	 *
	 * @param rows    The number of the rows of the matrix.
	 * @param columns The number of columns of the matrix.
	 * @return The generated random matrix.
	 */
	public static Matrix generateRandomMatrix(int rows, int columns) {
		return generateRandomMatrix(rows, columns, 10);
	}

	/**
	 * Generate a random matrix with rows, columns and upper limit specified.
	 *
	 * @param rows       The number of the rows of the matrix.
	 * @param columns    The number of the columns of the matrix.
	 * @param upperLimit The upper limit of the elements.
	 * @return The generated random matrix.
	 */
	public static Matrix generateRandomMatrix(int rows, int columns, int upperLimit) {
		Matrix matrix = new Matrix(rows, columns);
		Random random = new Random(Seed.next());
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				matrix.value()[i][j] = random.nextInt(upperLimit);
			}
		}
		return matrix;
	}

}