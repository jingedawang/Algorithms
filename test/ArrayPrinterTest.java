/**
 * Copyright 2022 jingedawang
 */

import org.junit.jupiter.api.Test;
import utils.ArrayGenerator;
import utils.ArrayPrinter;

import java.util.Arrays;

/**
 * Test class for {@link ArrayPrinter}.
 */
public class ArrayPrinterTest {

	@Test
	void print() {
		int[] integerArr = ArrayGenerator.randomArray(100, 20);
		int[] integerArrLargeSize = ArrayGenerator.randomArray(100, 30);
		double[] doubleArr = ArrayGenerator.randomDoubleArray(10);
		char[] charArr = "How are you there?".toCharArray();
		String[] stringArr = {"How", "are", "you", "there", "?"};
		Class[] classArr = {Integer.class, Long.class, Double.class, String.class, ArrayPrinter.class, Object.class};
		ArrayPrinter.print(integerArr);
		ArrayPrinter.print(integerArrLargeSize);
		ArrayPrinter.print(Arrays.stream(integerArrLargeSize).boxed().toArray(Integer[]::new), 0, integerArrLargeSize.length, true);
		ArrayPrinter.print(doubleArr);
		ArrayPrinter.print(charArr);
		ArrayPrinter.print(stringArr);
		ArrayPrinter.print(classArr);
	}

}