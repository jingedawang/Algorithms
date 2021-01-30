/**
 * Copyright 2020 jingedawang
 */
package matrix;

/**
 * <h3>Plain matrix multiplier</h3>
 * <p>
 * This multiplier calculates matrix multiplication according to the definition.
 */
public class PlainMultiplier implements MatrixMultiplier {

	/**
	 * Multiply two matrices.
	 *
	 * @param A First operand of matrix multiplication.
	 * @param B Second operand of matrix multiplication.
	 * @return The product of the matrix multiplication.
	 */
	@Override
	public Matrix multiply(Matrix A, Matrix B) {
		Matrix C = new Matrix(A.rows(), B.columns());
		for (int i = 0; i < A.rows(); i++) {
			for (int j = 0; j < B.columns(); j++) {
				for (int k = 0; k < A.columns(); k++) {
					C.value()[i][j] += A.value()[i][k] * B.value()[k][j];
				}
			}
		}
		return C;
	}

}