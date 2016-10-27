package com.ucas.algorithms.matrix;

import com.ucas.algorithms.math.Matrix;

public class PlainMultiplier implements MatrixMultiplier {

	@Override
	public Matrix multiply(Matrix A, Matrix B) {
		if (A.row() != B.column() || A.column() != B.row()) {
			throw new IllegalArgumentException("A multiply B is applicable only when A is an m x n matrix while B is an n x m matrix.");
		}
		Matrix C = new Matrix(A.row(), B.column());
		for (int i=0; i<A.row(); i++) {
			for (int j=0; j<B.column(); j++) {
				for (int k=0; k<A.column(); k++) {
					C.value()[i][j] += A.value()[i][k] * B.value()[k][j];
				}
			}
		}
		return C;
	}

}
