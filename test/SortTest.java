/**
 * Copyright 2022 jingedawang
 */
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import sort.*;
import utils.ArrayGenerator;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Test class for all classes implemented {@link Sort} interface.
 */
public class SortTest {

	@Test
	void main() {
		BucketSort.main(new String[]{});
		CountingSort.main(new String[]{});
		HeapSort.main(new String[]{});
		HybridSort.main(new String[]{});
		InsertionSort.main(new String[]{});
		MergeSort.main(new String[]{});
		QuickSort.main(new String[]{});
		RadixSort.main(new String[]{});
		RandomizedQuickSort.main(new String[]{});
	}

	@Test
	void sort() {
		int upperLimit = 173;
		int[] arr = ArrayGenerator.randomArray(upperLimit, 103);
		ArrayList<Sort> sortAlgorithms = new ArrayList<>();
		sortAlgorithms.add(new BucketSort(upperLimit));
		sortAlgorithms.add(new CountingSort(upperLimit));
		sortAlgorithms.add(new HeapSort());
		sortAlgorithms.add(new HybridSort());
		sortAlgorithms.add(new InsertionSort());
		sortAlgorithms.add(new MergeSort());
		sortAlgorithms.add(new QuickSort());
		sortAlgorithms.add(new RadixSort(3, upperLimit));
		sortAlgorithms.add(new RandomizedQuickSort());

		int[] sortedArr = arr.clone();
		Arrays.sort(sortedArr);
		for (Sort sortAlgorithm : sortAlgorithms) {
			int[] clonedArr = arr.clone();
			sortAlgorithm.sort(clonedArr);
			Assertions.assertArrayEquals(sortedArr, clonedArr);
		}
	}

}