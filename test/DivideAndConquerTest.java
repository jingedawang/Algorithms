/**
 * Copyright 2022 jingedawang
 */

import divideandconquer.FindMaximumSubarray;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.Values3;

/**
 * Test class for divide-and-conquer algorithms.
 */
public class DivideAndConquerTest {

	@Test
	void main() {
		FindMaximumSubarray.main(new String[]{});
	}

	@Test
	void findMaximumSubarray() {
		int[] arr = {-7, -9, -10, -2, 8, -2, 6, 1, -10, 4, -7, -9, -7, -3, 5, 1, 5, -4, 4, 1};

		FindMaximumSubarray findMaximumSubarray = new FindMaximumSubarray();
		Values3<Integer, Integer, Integer> values = findMaximumSubarray.findMaximumSubarray(arr, 0, arr.length - 1);
		Assertions.assertEquals(4, values.value1);
		Assertions.assertEquals(7, values.value2);
		Assertions.assertEquals(13, values.value3);
	}

}