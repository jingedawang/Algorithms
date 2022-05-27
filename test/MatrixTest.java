/**
 * Copyright 2022 jingedawang
 */

import enums.MultiplierType;
import matrix.Matrix;
import matrix.MatrixMultiplier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.ArrayUtils;
import utils.MatrixGenerator;
import utils.TimeRecorder;

/**
 * Test class for {@link Matrix}.
 */
public class MatrixTest {

	@Test
	void main() {
		MatrixMultiplier.main(new String[]{});
	}

	@Test
	void speed() {
		Matrix<Double> A = MatrixGenerator.generateRandomDoubleMatrix(1 << 10);
		Matrix<Double> B = MatrixGenerator.generateRandomDoubleMatrix(1 << 10);

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

	@Test()
	void constructWithNegativeRows() {
		Assertions.assertThrows(
				IllegalArgumentException.class,
				() -> new Matrix<Double>(-1, 2),
				"Should throw error for negative rows or columns."
		);
	}

	@Test
	void value() {
		equals(matrix1, data1);
	}

	@Test
	void add() {
		equals(matrix1.add(matrix2), sumData1Data2);
		equals(matrix6.add(matrix7), sumData6Data7);
	}

	@Test
	void subtract() {
		equals(matrix1.subtract(matrix2), differenceData1Data2);
		equals(matrix6.subtract(matrix7), differenceData6Data7);
	}

	@Test
	void multiply() {
		equals(matrix3.multiply(matrix4), productData3Data4);
		equals(matrix3.multiply(matrix4, MultiplierType.STRASSEN), productData3Data4);
		equals(matrix6.multiply(matrix8), productData6Data8);

		Matrix<Double> A = MatrixGenerator.generateRandomDoubleMatrix(512);
		Matrix<Double> B = MatrixGenerator.generateRandomDoubleMatrix(512);
		Matrix<Double> product1 = A.multiply(B, MultiplierType.PLAIN);
		Matrix<Double> product2 = A.multiply(B, MultiplierType.STRASSEN);
		Assertions.assertEquals(product1, product2);

		Matrix<Integer> C = MatrixGenerator.generateRandomIntegerMatrix(512);
		Matrix<Integer> D = MatrixGenerator.generateRandomIntegerMatrix(512);
		Matrix<Integer> product3 = C.multiply(D, MultiplierType.PLAIN);
		Matrix<Integer> product4 = C.multiply(D, MultiplierType.STRASSEN);
		Assertions.assertEquals(product3, product4);
	}

	@Test
	void multiplyWithWrongSizeMatrices() {
		Assertions.assertThrows(
				IllegalArgumentException.class,
				() -> matrix3.multiply(matrix2),
				"Should throw error for not corresponding rows and columns."
		);
	}

	@Test
	void split() {
		Matrix<Double>[] results = matrix1.split();
		equals(results[0], A11);
		equals(results[1], A12);
		equals(results[2], A21);
		equals(results[3], A22);

		Matrix<Double>[] resultsTransformed = matrix1.transform().split();
		equals(resultsTransformed[0], ArrayUtils.toDouble(new Matrix<Double>(A11).transform().value()));
		equals(resultsTransformed[1], ArrayUtils.toDouble(new Matrix<Double>(A21).transform().value()));
		equals(resultsTransformed[2], ArrayUtils.toDouble(new Matrix<Double>(A12).transform().value()));
		equals(resultsTransformed[3], ArrayUtils.toDouble(new Matrix<Double>(A22).transform().value()));

		Matrix<Double>[] resultsOddWidth = matrix5.split();
		equals(resultsOddWidth[0], new double[][] {{1, 2}, {4, 5}});
		equals(resultsOddWidth[1], new double[][] {{3}, {6}});
		equals(resultsOddWidth[2], new double[][] {{7, 8}});
		equals(resultsOddWidth[3], new double[][] {{9}});
	}

	@Test
	void splitWithLessRows() {
		Assertions.assertThrows(
				IllegalArgumentException.class,
				() -> MatrixGenerator.generateRandomDoubleMatrix(1, 4).split(),
				"Should throw error when splitting a matrix with only 1 row."
		);
	}

	@Test
	void isSquare() {
		boolean isSquare = matrix1.rows() == matrix1.columns();
		Assertions.assertEquals(matrix1.isSquare(), isSquare);
	}

	@Test
	void rows() {
		Assertions.assertEquals(matrix1.rows(), data1.length);
	}

	@Test
	void columns() {
		Assertions.assertEquals(matrix1.columns(), data1[0].length);
	}

	@Test
	void mergeWithWrongSize() {
		Assertions.assertThrows(
				IllegalArgumentException.class,
				() -> Matrix.merge(matrix1, matrix2, matrix3, matrix4),
				"Should throw error if one of the param is not square matrix."
		);

		Assertions.assertThrows(
				IllegalArgumentException.class,
				() -> Matrix.merge(matrix3, matrix3, matrix4, matrix5),
				"Should throw error if the size of the matrices are not the same."
		);
	}

	private void equals(Matrix<Integer> matrix, int[][] data) {
		equals(matrix, ArrayUtils.box(data));
	}

	private void equals(Matrix<Double> matrix, double[][] data) {
		equals(matrix, ArrayUtils.box(data));
	}

	private <T extends Number> void equals(Matrix<T> matrix, T[][] data) {
		Assertions.assertEquals(matrix.rows(), data.length);
		Assertions.assertEquals(matrix.columns(), data[0].length);
		for (int i = 0; i < matrix.rows(); i++) {
			for (int j = 0; j < matrix.columns(); j++) {
				Assertions.assertEquals(matrix.get(i, j), data[i][j]);
			}
		}
	}

	private final double[][] data1 = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
	private final double[][] data2 = {{-1, 3, 4, 2}, {12, 1, 0, 8}, {-5, -3, 0, 9}};
	private final double[][] data3 = {{3, 1, -5, 17}, {-13, 1, 6, 11}, {22, -9, -1, 0}, {31, 12, 0, 3}};
	private final double[][] data4 = {{10, -2, 8, 0}, {-11, 12, 1, 6}, {8, 7, -9, -3}, {-12, 4, 7, -7}};
	private final double[][] data5 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	private final int[][] data6 = {{1, 1, 2, 3}, {-1, -2, -2, -1}, {5, 0, 0, 1}};
	private final int[][] data7 = {{0, 0, 1, -1}, {-1, 2, 4, -3}, {1, 5, -1, 0}};
	private final int[][] data8 = {{3, -1, 0}, {1, -1, 10}, {0, -3, -5}, {8, 3, 1}};
	private final double[][] sumData1Data2 = {{0, 5, 7, 6}, {17, 7, 7, 16}, {4, 7, 11, 21}};
	private final double[][] differenceData1Data2 = {{2, -1, -1, 2}, {-7, 5, 7, 0}, {14, 13, 11, 3}};
	private final double[][] productData3Data4 = {{-225, 39, 189, -98}, {-225, 124, -80, -89}, {311, -159, 176, -51}, {142, 94, 281, 51}};
	private final int[][] sumData6Data7 = {{1, 1, 3, 2}, {-2, 0, 2, -4}, {6, 5, -1, 1}};
	private final int[][] differenceData6Data7 = {{1, 1, 1, 4}, {0, -4, -6, 2}, {4, -5, 1, 1}};
	private final int[][] productData6Data8 = {{28, 1, 3}, {-13, 6, -11}, {23, -2, 1}};
	private final double[][] A11 = {{1, 2}, {5, 6}};
	private final double[][] A12 = {{3, 4}, {7, 8}};
	private final double[][] A21 = {{9, 10}};
	private final double[][] A22 = {{11, 12}};
	private final Matrix<Double> matrix1 = new Matrix<>(data1);
	private final Matrix<Double> matrix2 = new Matrix<>(data2);
	private final Matrix<Double> matrix3 = new Matrix<>(data3);
	private final Matrix<Double> matrix4 = new Matrix<>(data4);
	private final Matrix<Double> matrix5 = new Matrix<>(data5);
	private final Matrix<Integer> matrix6 = new Matrix<>(data6);
	private final Matrix<Integer> matrix7 = new Matrix<>(data7);
	private final Matrix<Integer> matrix8 = new Matrix<>(data8);

}