package com.ucas.algorithms.matrix;

import com.ucas.algorithms.enums.MultiplierType;
import com.ucas.algorithms.math.Matrix;
import com.ucas.algorithms.utils.MatrixGenerator;
import com.ucas.algorithms.utils.MatrixPrinter;
import com.ucas.algorithms.utils.TimeElapse;

public class MatrixMultiplyTest {

	public static void main(String[] args) {
		
		for (int n=0; n<12; n++) {
		
			Matrix A = MatrixGenerator.generateRandomMatrix(1 << n);
			Matrix B = MatrixGenerator.generateRandomMatrix(1 << n);
			TimeElapse time1 = new TimeElapse();
			time1.start();
			Matrix plainC = A.multiply(B, MultiplierType.PLAIN);
			time1.stop();
			System.out.print(n + "plain: ");
			time1.showElapsedTime();
			TimeElapse time2 = new TimeElapse();
			time2.start();
			Matrix strassenC = A.multiply(B, MultiplierType.STRASSEN);
			time2.stop();
			System.out.print(n + "strassen: ");
			time2.showElapsedTime();
//		MatrixPrinter.print(A);
//		MatrixPrinter.print(B);
//		MatrixPrinter.print(plainC);
//		MatrixPrinter.print(strassenC);
		
		}
		
	}

}
