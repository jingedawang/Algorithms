/**
 * Copyright 2020 jingedawang
 */
package test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import select.BFPRT;
import select.QuickSelect;
import select.Select;
import utils.ArrayGenerator;

import java.util.Arrays;

/**
 * <h3>Test class for {@link Select}</h3>
 */
public class SelectTest {

	@Test
	void quickSelect() {
		for (int i = 0; i < 200; i++) {
			int[] arr = ArrayGenerator.randomArray(20, 20);
			int[] sortedArr = arr.clone();
			Arrays.sort(sortedArr);
			QuickSelect select = new QuickSelect();
			for (int j = 0; j < 20; j++) {
				int selected = select.select(arr, j);
				Assertions.assertEquals(sortedArr[j], selected);
			}
		}
	}

	@Test
	void bfprt() {
		for (int i = 0; i < 200; i++) {
			int[] arr = ArrayGenerator.randomArray(20, 20);
			int[] sortedArr = arr.clone();
			Arrays.sort(sortedArr);
			BFPRT bfprt = new BFPRT();
			for (int j = 0; j < 20; j++) {
				int selected = bfprt.select(arr, j);
				Assertions.assertEquals(sortedArr[j], selected);
			}
		}
	}

}