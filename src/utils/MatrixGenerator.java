/**
 * Copyright 2022 jingedawang
 */
package utils;

import java.util.Random;

import matrix.Matrix;

/**
 * Matrix generator.
 * <p>
 * This class provides different matrix generators.
 */
public class MatrixGenerator {

	/**
	 * Generate a random square integer matrix with side length specified.
	 *
	 * @param sideLength The side length of the square matrix.
	 * @return The generated random integer matrix.
	 */
	public static Matrix<Integer> generateRandomIntegerMatrix(int sideLength) {
		return generateRandomIntegerMatrix(sideLength, sideLength, 10);
	}

	/**
	 * Generate a random integer matrix with rows, columns and upper limit specified.
	 *
	 * @param rows       The number of the rows of the matrix.
	 * @param columns    The number of the columns of the matrix.
	 * @param upperLimit The upper limit of the elements.
	 * @return The generated random integer matrix.
	 */
	public static Matrix<Integer> generateRandomIntegerMatrix(int rows, int columns, int upperLimit) {
		Matrix<Integer> matrix = new Matrix<>(rows, columns);
		Random random = new Random(Seed.next());
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				matrix.set(random.nextInt(upperLimit), i, j);
			}
		}
		return matrix;
	}

	/**
	 * Generate a random square double matrix with side length specified.
	 *
	 * @param sideLength The side length of the square matrix.
	 * @return The generated random double matrix.
	 */
	public static Matrix<Double> generateRandomDoubleMatrix(int sideLength) {
		return generateRandomDoubleMatrix(sideLength, sideLength, 10);
	}

	/**
	 * Generate a random double matrix with rows and columns specified.
	 *
	 * @param rows    The number of the rows of the matrix.
	 * @param columns The number of columns of the matrix.
	 * @return The generated random double matrix.
	 */
	public static Matrix<Double> generateRandomDoubleMatrix(int rows, int columns) {
		return generateRandomDoubleMatrix(rows, columns, 10);
	}

	/**
	 * Generate a random double matrix with rows, columns and upper limit specified.
	 *
	 * @param rows       The number of the rows of the matrix.
	 * @param columns    The number of the columns of the matrix.
	 * @param upperLimit The upper limit of the elements.
	 * @return The generated random double matrix.
	 */
	public static Matrix<Double> generateRandomDoubleMatrix(int rows, int columns, int upperLimit) {
		Matrix<Double> matrix = new Matrix<>(rows, columns);
		Random random = new Random(Seed.next());
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				matrix.set(random.nextDouble() * upperLimit, i, j);
			}
		}
		return matrix;
	}

}