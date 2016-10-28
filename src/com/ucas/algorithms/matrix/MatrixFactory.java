package com.ucas.algorithms.matrix;

import com.ucas.algorithms.enums.MultiplierType;
import com.ucas.algorithms.math.Matrix;

public class MatrixFactory {

	public static MatrixMultiplier getMutiplier() {
		return new PlainMultiplier();
	}
	
	public static MatrixMultiplier getMutiplier(MultiplierType type) {
		switch (type) {
		case PLAIN:
			return new PlainMultiplier();
		case STRASSEN:
			return new StrassenMultiplier();
		default :
			return getMutiplier();
		}
	}
	
	public static Matrix merge(Matrix A11, Matrix A12, Matrix A21, Matrix A22) {
		if (!A11.isSquare() || !A12.isSquare() || !A21.isSquare() || !A22.isSquare()) {
			throw new IllegalArgumentException("Matrices to be merged must be square matrices.");
		}
		if (!(A11.row() == A12.row() && A11.row() == A21.row() && A11.row() == A22.row())) {
			throw new IllegalArgumentException("Matrices to be merged must have the same row number and column number");
		}
		Matrix A = new Matrix(A11.row() * 2, A11.row() * 2);
		for (int i=0; i<A11.row(); i++) {
			for (int j=0; j<A11.column(); j++) {
				A.value()[i][j] = A11.value()[i][j];
				A.value()[i][j + A11.column()] = A12.value()[i][j];
				A.value()[i + A11.row()][j] = A21.value()[i][j];
				A.value()[i + A11.row()][j + A11.column()] = A22.value()[i][j];
			}
		}
		return A;
	}

	
	
}
