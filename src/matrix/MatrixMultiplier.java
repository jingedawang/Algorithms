/**
 * Copyright 2022 jingedawang
 */
package matrix;

import enums.MultiplierType;
import utils.MatrixGenerator;
import utils.TimeRecorder;

/**
 * Matrix multiplier interface
 */
public interface MatrixMultiplier {

	/**
	 * Test code.
	 */
	static void main(String[] args) {

		for (int n = 0; n < 11; n++) {
			Matrix<Double> A = MatrixGenerator.generateRandomDoubleMatrix(1 << n);
			Matrix<Double> B = MatrixGenerator.generateRandomDoubleMatrix(1 << n);

			TimeRecorder timeRecorderPlain = new TimeRecorder(n + " x " + n + " plain");
			timeRecorderPlain.start();
			A.multiply(B, MultiplierType.PLAIN);
			timeRecorderPlain.stop();
			timeRecorderPlain.print();

			TimeRecorder timeRecorderStrassen = new TimeRecorder(n + " x " + n + " strassen");
			timeRecorderStrassen.start();
			A.multiply(B, MultiplierType.STRASSEN);
			timeRecorderStrassen.stop();
			timeRecorderStrassen.print();
		}

	}

	/**
	 * Implementation method for matrix multiplication.
	 *
	 * @param A First operand of matrix multiplication.
	 * @param B Second operand of matrix multiplication.
	 * @param <T> The type of the underlying elements of the matrix to be multiplied.
	 * @return The product of the matrix multiplication.
	 */
	<T extends Number> Matrix<T> multiply(Matrix<T> A, Matrix<T> B);

}