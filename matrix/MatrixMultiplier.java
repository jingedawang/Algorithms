/**
 * Copyright 2020 jingedawang
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

		for (int n = 0; n < 12; n++) {
			Matrix A = MatrixGenerator.generateRandomMatrix(1 << n);
			Matrix B = MatrixGenerator.generateRandomMatrix(1 << n);

			TimeRecorder timeRecorder = new TimeRecorder();
			timeRecorder.start();
			Matrix plainC = A.multiply(B, MultiplierType.PLAIN);
			timeRecorder.stop();
			System.out.print(n + " x " + n + ": plain: ");
			timeRecorder.showElapsedTime();

			timeRecorder.start();
			Matrix strassenC = A.multiply(B, MultiplierType.STRASSEN);
			timeRecorder.stop();
			System.out.print(n + " x " + n + ": strassen: ");
			timeRecorder.showElapsedTime();
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