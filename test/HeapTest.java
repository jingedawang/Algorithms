/**
 * Copyright 2022 jingedawang
 */

import container.BinaryHeap;
import container.FibonacciHeap;
import container.Node;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.ArrayGenerator;

import java.util.Arrays;

/**
 * Test class for {@link BinaryHeap}.
 */
public class HeapTest {

	@Test
	void maxHeap() {
		BinaryHeap heap = new BinaryHeap();
		Assertions.assertTrue(heap.empty());

		int[] arr = ArrayGenerator.fixedArray();
		for (int value : arr) {
			heap.insert(new Node(value));
		}
		int[] sortedArr = arr.clone();
		Arrays.sort(sortedArr);
		for (int i = 0; i < arr.length; i++) {
			Assertions.assertEquals(sortedArr[sortedArr.length - 1 - i], heap.top());
			Assertions.assertEquals(sortedArr[sortedArr.length - 1 - i], heap.pop());
		}
	}

	@Test
	void minHeap() {
		int[] arr = ArrayGenerator.randomArray(100, 100);
		int[] halfArr = new int[arr.length / 2];
		System.arraycopy(arr, 0, halfArr, 0, halfArr.length);

		BinaryHeap binaryHeap = new BinaryHeap(halfArr.clone(), true);
		FibonacciHeap fibonacciHeap = new FibonacciHeap(halfArr.clone());
		for (int i = halfArr.length; i < arr.length; i++) {
			binaryHeap.insert(new Node(arr[i]));
			fibonacciHeap.insert(new Node(arr[i]));
		}

		int[] sortedRandomArr = arr.clone();
		Arrays.sort(sortedRandomArr);
		for (int value : sortedRandomArr) {
			Assertions.assertEquals(value, binaryHeap.top());
			Assertions.assertEquals(value, binaryHeap.pop());
			Assertions.assertEquals(value, fibonacciHeap.top());
			Assertions.assertEquals(value, fibonacciHeap.pop());
		}
	}

}