package matrix;

import enums.MultiplierType;
import math.Matrix;
import utils.MatrixGenerator;
import utils.TimeRecorder;

public class MatrixMultiplyTest {

	public static void main(String[] args) {
		
		for (int n=0; n<12; n++) {
			Matrix A = MatrixGenerator.generateRandomMatrix(1 << n);
			Matrix B = MatrixGenerator.generateRandomMatrix(1 << n);
			TimeRecorder time1 = new TimeRecorder();
			time1.start();
			Matrix plainC = A.multiply(B, MultiplierType.PLAIN);
			time1.stop();
			System.out.print(n + "plain: ");
			time1.showElapsedTime();
			TimeRecorder time2 = new TimeRecorder();
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
