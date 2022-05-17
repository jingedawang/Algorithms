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
		Matrix matrix = MatrixGenerator.generateRandomMatrix(4, 5);
		MatrixPrinter.print(matrix);
	}

}