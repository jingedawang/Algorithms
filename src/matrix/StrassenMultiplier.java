/**
 * Copyright 2020 jingedawang
 */
package matrix;

/**
 * <h3>Strassen matrix multiplier</h3>
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
	 * @return The product of the matrix multiplication.
	 */
	@Override
	public Matrix multiply(Matrix A, Matrix B) {
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
			Matrix C = new Matrix(1, 1);
			C.value()[0][0] = A.value()[0][0] * B.value()[0][0];
			return C;
		}

		Matrix[] ASplit = A.split();
		Matrix[] BSplit = B.split();
		Matrix A11 = ASplit[0];
		Matrix A12 = ASplit[1];
		Matrix A21 = ASplit[2];
		Matrix A22 = ASplit[3];
		Matrix B11 = BSplit[0];
		Matrix B12 = BSplit[1];
		Matrix B21 = BSplit[2];
		Matrix B22 = BSplit[3];

		Matrix S1 = B12.subtract(B22);
		Matrix S2 = A11.add(A12);
		Matrix S3 = A21.add(A22);
		Matrix S4 = B21.subtract(B11);
		Matrix S5 = A11.add(A22);
		Matrix S6 = B11.add(B22);
		Matrix S7 = A12.subtract(A22);
		Matrix S8 = B21.add(B22);
		Matrix S9 = A11.subtract(A21);
		Matrix S10 = B11.add(B12);

		Matrix P1 = A11.multiply(S1);
		Matrix P2 = S2.multiply(B22);
		Matrix P3 = S3.multiply(B11);
		Matrix P4 = A22.multiply(S4);
		Matrix P5 = S5.multiply(S6);
		Matrix P6 = S7.multiply(S8);
		Matrix P7 = S9.multiply(S10);

		Matrix C11 = P5.add(P4).subtract(P2).add(P6);
		Matrix C12 = P1.add(P2);
		Matrix C21 = P3.add(P4);
		Matrix C22 = P5.add(P1).subtract(P3).subtract(P7);

		return Matrix.merge(C11, C12, C21, C22);
	}

}