/**
 * Copyright 2022 jingedawang
 */
package matrix;

import utils.ArrayUtils;

/**
 * Plain matrix multiplier.
 * <p>
 * This multiplier calculates matrix multiplication according to the definition.
 */
public class PlainMultiplier implements MatrixMultiplier {

	/**
	 * Multiply two matrices.
	 *
	 * @param A First operand of matrix multiplication.
	 * @param B Second operand of matrix multiplication.
	 * @param <T> The type of the underlying elements of the matrix to be multiplied.
	 * @return The product of the matrix multiplication.
	 */
	@Override
	public <T extends Number> Matrix<T> multiply(Matrix<T> A, Matrix<T> B) {
		assert A.get(0, 0).getClass() == B.get(0, 0).getClass();
		Matrix<T> C = new Matrix<T>(A.rows(), B.columns());
		if (A.get(0, 0) instanceof Integer) {
			ArrayUtils.fill(C.value(), 0);
		}
		else if (A.get(0 ,0) instanceof Double) {
			ArrayUtils.fill(C.value(), 0.0);
		}
		for (int i = 0; i < A.rows(); i++) {
			for (int j = 0; j < B.columns(); j++) {
				for (int k = 0; k < A.columns(); k++) {
					Number tempResult;
					if (A.get(i, j) instanceof Integer) {
						tempResult = C.get(i, j).intValue() + A.get(i, k).intValue() * B.get(k, j).intValue();
					}
					else if (A.get(i, j) instanceof Double) {
						tempResult = C.get(i, j).doubleValue() + A.get(i, k).doubleValue() * B.get(k, j).doubleValue();
					}
					else {
						throw new IllegalArgumentException("Number type " + A.get(i, j).getClass().getName() + " is not supported in PlainMultiplier.");
					}
					C.set((T) tempResult, i, j);
				}
			}
		}
		return C;
	}

}