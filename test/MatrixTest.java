/**
 * Copyright 2022 jingedawang
 */

import enums.MultiplierType;
import matrix.Matrix;
import matrix.MatrixMultiplier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.MatrixGenerator;
import utils.TimeRecorder;

/**
 * <h3>Test class for {@link Matrix}</h3>
 */
public class MatrixTest {

	@Test
	void main() {
		MatrixMultiplier.main(new String[]{});
	}

	@Test
	void speed() {
		Matrix A = MatrixGenerator.generateRandomMatrix(1 << 10);
		Matrix B = MatrixGenerator.generateRandomMatrix(1 << 10);

		TimeRecorder timeRecorderPlain = new TimeRecorder("Plain");
		timeRecorderPlain.start();
		A.multiply(B, MultiplierType.PLAIN);
		timeRecorderPlain.stop();
		int elapsedTimePlain = timeRecorderPlain.getElapsedTime();

		TimeRecorder timeRecorderStrassen = new TimeRecorder("Strassen");
		timeRecorderStrassen.start();
		A.multiply(B, MultiplierType.STRASSEN);
		timeRecorderStrassen.stop();
		int elapsedTimeStrassen = timeRecorderStrassen.getElapsedTime();

		Assertions.assertTrue(elapsedTimeStrassen <= elapsedTimePlain);
	}

	@Test
	void value() {
		equals(matrix1, data1);
	}

	@Test
	void add() {
		Matrix result = matrix1.add(matrix2);
		equals(result, sum);
	}

	@Test
	void subtract() {
		Matrix result = matrix1.subtract(matrix2);
		equals(result, difference);
	}

	@Test
	void multiply() {
		Matrix result = matrix3.multiply(matrix4);
		equals(result, product);
	}

	@Test
	void multiplyWithStrassen() {
		Matrix result = matrix3.multiply(matrix4, MultiplierType.STRASSEN);
		equals(result, product);
	}

	@Test
	void split() {
		Matrix[] results = matrix1.split();
		equals(results[0], A11);
		equals(results[1], A12);
		equals(results[2], A21);
		equals(results[3], A22);
	}

	@Test
	void isSquare() {
		boolean isSquare = matrix1.rows() == matrix1.columns();
		Assertions.assertEquals(matrix1.isSquare(), isSquare);
	}

	@Test
	void rows() {
		Assertions.assertEquals(matrix1.rows(), matrix1.value().length);
	}

	@Test
	void columns() {
		Assertions.assertEquals(matrix1.columns(), matrix1.value()[0].length);
	}

	private void equals(Matrix matrix, double[][] data) {
		Assertions.assertEquals(matrix.rows(), data.length);
		Assertions.assertEquals(matrix.columns(), data[0].length);
		for (int i = 0; i < matrix.rows(); i++) {
			for (int j = 0; j < matrix.columns(); j++) {
				Assertions.assertEquals(matrix.value()[i][j], data[i][j]);
			}
		}
	}

	private final double[][] data1 = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
	private final double[][] data2 = {{-1, 3, 4, 2}, {12, 1, 0, 8}, {-5, -3, 0, 9}};
	private final double[][] data3 = {{3, 1, -5, 17}, {-13, 1, 6, 11}, {22, -9, -1, 0}, {31, 12, 0, 3}};
	private final double[][] data4 = {{10, -2, 8, 0}, {-11, 12, 1, 6}, {8, 7, -9, -3}, {-12, 4, 7, -7}};
	private final double[][] sum = {{0, 5, 7, 6}, {17, 7, 7, 16}, {4, 7, 11, 21}};
	private final double[][] difference = {{2, -1, -1, 2}, {-7, 5, 7, 0}, {14, 13, 11, 3}};
	private final double[][] product = {{-225, 39, 189, -98}, {-225, 124, -80, -89}, {311, -159, 176, -51}, {142, 94, 281, 51}};
	private final double[][] A11 = {{1, 2}, {5, 6}};
	private final double[][] A12 = {{3, 4}, {7, 8}};
	private final double[][] A21 = {{9, 10}};
	private final double[][] A22 = {{11, 12}};
	private final Matrix matrix1 = new Matrix(data1);
	private final Matrix matrix2 = new Matrix(data2);
	private final Matrix matrix3 = new Matrix(data3);
	private final Matrix matrix4 = new Matrix(data4);
}