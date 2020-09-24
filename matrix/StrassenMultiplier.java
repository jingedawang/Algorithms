package matrix;

import math.Matrix;

/**
 * 该类采用Strassen算法实现矩阵乘法。只适用于n x n矩阵且n为2<sup>m</sup>的情况。
 * @author wjg
 *
 */
public class StrassenMultiplier implements MatrixMultiplier {

	@Override
	public Matrix multiply(Matrix A, Matrix B) {
		
		if (A.row() != A.column() || B.row() != B.column()) {
			throw new IllegalArgumentException("A and B must be both n x n matrix");
		}
		if (A.row() != B.row()) {
			throw new IllegalArgumentException("A and B must have same row number.");
		}
		if ((A.row() & -A.row()) != A.row() || (B.row() & -B.row()) != B.row()) {
			throw new IllegalArgumentException("When using Strassen Multiplier, the dimension of matrices must be a power of 2.");
		}
		if (A.row() == 1) {
			Matrix C = new Matrix(1, 1);
			C.value()[0][0] = A.value()[0][0] * B.value()[0][0];
			return C;
		}
		
	/*
	 * 
	 * 
	 * 
	 * 
	 */
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
		
		Matrix C = MatrixFactory.merge(C11, C12, C21, C22);
		
		return C;
	}

}
