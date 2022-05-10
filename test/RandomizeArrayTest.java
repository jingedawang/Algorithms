/**
 * Copyright 2022 jingedawang
 */

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import randomize.RandomizeArray;
import utils.ArrayGenerator;

import java.util.Arrays;

/**
 * Test class for {@link RandomizeArray}.
 */
public class RandomizeArrayTest {

	@Test
	void main() {
		RandomizeArray.main(new String[]{});
	}

	@Test
	void randomize() {
		int[] arr = ArrayGenerator.fixedArray();

		RandomizeArray.randomizeInPlace(arr);
		Assertions.assertFalse(Arrays.equals(ArrayGenerator.fixedArray(), arr));
	}

}