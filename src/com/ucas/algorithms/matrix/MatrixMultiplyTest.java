package com.ucas.algorithms.matrix;

import com.ucas.algorithms.math.Matrix;
import com.ucas.algorithms.utils.MatrixGenerator;
import com.ucas.algorithms.utils.MatrixPrinter;

public class MatrixMultiplyTest {

	public static void main(String[] args) {
		
		Matrix A = MatrixGenerator.generateRandomMatrix(128);
		Matrix B = MatrixGenerator.generateRandomMatrix(128);
		Matrix C = A.multiply(B);
		MatrixPrinter.print(A);
		MatrixPrinter.print(B);
		MatrixPrinter.print(C);
		
	}

}
