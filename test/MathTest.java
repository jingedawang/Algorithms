/**
 * Copyright 2022 jingedawang
 */

import math.Fibonacci;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

/**
 * Test class for math algorithms.
 */
public class MathTest {

	@Test
	void main() {
		Fibonacci.main(new String[]{});
	}

	@Test
	void fibonacci() {
		List<BigDecimal> fibonacciList = Fibonacci.getInstance().getFibonacciList(10);
		Assertions.assertArrayEquals(
				new int[]{0, 1, 1, 2, 3, 5, 8, 13, 21, 34},
				fibonacciList.stream().mapToInt(BigDecimal::intValue).toArray()
		);
		Assertions.assertEquals(
				new BigDecimal("218922995834555169026"),
				Fibonacci.getInstance().getFibonacci(99)
		);
	}

}