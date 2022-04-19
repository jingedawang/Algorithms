/**
 * Copyright 2022 jingedawang
 */
package matrix;

import enums.MultiplierType;
import utils.MatrixGenerator;
import utils.TimeRecorder;

/**
 * <h3>Matrix multiplier interface</h3>
 */
public interface MatrixMultiplier {

	/**
	 * Test code.
	 */
	static void main(String[] args) {

		for (int n = 0; n < 11; n++) {
			Matrix A = MatrixGenerator.generateRandomMatrix(1 << n);
			Matrix B = MatrixGenerator.generateRandomMatrix(1 << n);

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
	 * @return The product of the matrix multiplication.
	 */
	public Matrix multiply(Matrix A, Matrix B);

}