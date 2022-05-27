/**
 * Copyright 2022 jingedawang
 */
package matrix;

/**
 * Strassen matrix multiplier.
 * <p>
 * This multiplier is implemented according to Strassen algorithm. Its time complexity is O(n^lg7).
 * To simplify the implementation, we require the dimension of the input matrices must be a power of 2.
 */
public class StrassenMultiplier implements MatrixMultiplier {

	/**
	 * Multiply two matrices using Strassen algorithm.
	 *
	 * @param A First operand of matrix multiplication.
	 * @param B Second operand of matrix multiplication.
	 * @param <T> The type of the underlying elements of the matrix to be multiplied.
	 * @return The product of the matrix multiplication.
	 */
	@Override
	public <T extends Number> Matrix<T> multiply(Matrix<T> A, Matrix<T> B) {
		assert A.get(0, 0).getClass() == B.get(0, 0).getClass();
		if (A.rows() != A.columns() || B.rows() != B.columns()) {
			throw new IllegalArgumentException("A and B must be both n x n matrix");
		}
		if (A.rows() != B.rows()) {
			throw new IllegalArgumentException("A and B must have same row number.");
		}
		if ((A.rows() & -A.rows()) != A.rows() || (B.rows() & -B.rows()) != B.rows()) {
			throw new IllegalArgumentException("When using Strassen Multiplier, the dimension of matrices must be a power of 2.");
		}
		if (A.rows() == 1) {
			Matrix<T> C = new Matrix<>(1, 1);
			Number product = null;
			if (A.value()[0][0] instanceof Integer) {
				product = A.value()[0][0].intValue() * B.value()[0][0].intValue();
			}
			else if (A.value()[0][0] instanceof Double) {
				product = A.value()[0][0].doubleValue() * B.value()[0][0].doubleValue();
			}
			else {
				throw new IllegalArgumentException("Number type " + A.value()[0][0].getClass().getName() + " is not supported in PlainMultiplier.");
			}
			C.value()[0][0] = (T) product;
			return C;
		}

		Matrix<T>[] ASplit = A.split();
		Matrix<T>[] BSplit = B.split();
		Matrix<T> A11 = ASplit[0];
		Matrix<T> A12 = ASplit[1];
		Matrix<T> A21 = ASplit[2];
		Matrix<T> A22 = ASplit[3];
		Matrix<T> B11 = BSplit[0];
		Matrix<T> B12 = BSplit[1];
		Matrix<T> B21 = BSplit[2];
		Matrix<T> B22 = BSplit[3];

		Matrix<T> S1 = B12.subtract(B22);
		Matrix<T> S2 = A11.add(A12);
		Matrix<T> S3 = A21.add(A22);
		Matrix<T> S4 = B21.subtract(B11);
		Matrix<T> S5 = A11.add(A22);
		Matrix<T> S6 = B11.add(B22);
		Matrix<T> S7 = A12.subtract(A22);
		Matrix<T> S8 = B21.add(B22);
		Matrix<T> S9 = A11.subtract(A21);
		Matrix<T> S10 = B11.add(B12);

		Matrix<T> P1 = A11.multiply(S1);
		Matrix<T> P2 = S2.multiply(B22);
		Matrix<T> P3 = S3.multiply(B11);
		Matrix<T> P4 = A22.multiply(S4);
		Matrix<T> P5 = S5.multiply(S6);
		Matrix<T> P6 = S7.multiply(S8);
		Matrix<T> P7 = S9.multiply(S10);

		Matrix<T> C11 = P5.add(P4).subtract(P2).add(P6);
		Matrix<T> C12 = P1.add(P2);
		Matrix<T> C21 = P3.add(P4);
		Matrix<T> C22 = P5.add(P1).subtract(P3).subtract(P7);

		return Matrix.merge(C11, C12, C21, C22);
	}

}