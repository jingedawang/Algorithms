/**
 * Copyright 2022 jingedawang
 */

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import select.BFPRT;
import select.QuickSelect;
import select.Select;
import utils.ArrayGenerator;

import java.util.Arrays;

/**
 * Test class for all select algorithms.
 */
public class SelectTest {

	@Test
	void main() {
		BFPRT.main(new String[]{});
		QuickSelect.main(new String[]{});
	}

	@Test
	void select() {
		Select bfprtSelect = new BFPRT();
		Select quickSelect = new QuickSelect();
		for (int i = 0; i < 200; i++) {
			int[] arr = ArrayGenerator.randomArray(20, 20);
			int[] sortedArr = arr.clone();
			Arrays.sort(sortedArr);
			for (int j = 0; j < 20; j++) {
				int resultByBfprt = bfprtSelect.select(arr, j);
				int resultByQuickSelect = quickSelect.select(arr, j);
				Assertions.assertEquals(sortedArr[j], resultByBfprt);
				Assertions.assertEquals(sortedArr[j], resultByQuickSelect);
			}
		}
	}

}