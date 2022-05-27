/**
 * Copyright 2022 jingedawang
 */

import matrix.Matrix;
import org.junit.jupiter.api.Test;
import utils.MatrixGenerator;
import utils.MatrixPrinter;

/**
 * Test class for {@link MatrixPrinter}.
 */
public class MatrixPrinterTest {

	@Test
	void print() {
		Matrix<Integer> integerMatrix = MatrixGenerator.generateRandomIntegerMatrix(4, 5, 50);
		MatrixPrinter.print(integerMatrix);

		Matrix<Double> doubleMatrix = MatrixGenerator.generateRandomDoubleMatrix(4, 5, 100);
		MatrixPrinter.print(doubleMatrix);
	}

}