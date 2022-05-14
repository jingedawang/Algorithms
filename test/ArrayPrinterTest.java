/**
 * Copyright 2022 jingedawang
 */

import org.junit.jupiter.api.Test;
import utils.ArrayGenerator;
import utils.ArrayPrinter;

/**
 * Test class for {@link ArrayPrinter}.
 */
public class ArrayPrinterTest {

	@Test
	void print() {
		int[] integerArr = ArrayGenerator.randomArray(100, 20);
		double[] doubleArr = ArrayGenerator.randomDoubleArray(10);
		char[] charArr = "How are you there?".toCharArray();
		String[] stringArr = {"How", "are", "you", "there", "?"};
		Class[] classArr = {Integer.class, Long.class, Double.class, String.class, ArrayPrinter.class, Object.class};
		ArrayPrinter.print(integerArr);
		ArrayPrinter.print(doubleArr);
		ArrayPrinter.print(charArr);
		ArrayPrinter.print(stringArr);
		ArrayPrinter.print(classArr);
	}

}