/**
 * Copyright 2022 jingedawang
 */

import container.BinaryHeap;
import container.FibonacciHeap;
import container.Node;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.ArrayGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Test class for {@link BinaryHeap}.
 */
public class HeapTest {

	@Test
	void main() {
		BinaryHeap.main(new String[]{});
		FibonacciHeap.main(new String[]{});
	}

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

	@Test
	void delete() {
		int[] arr = ArrayGenerator.randomArray(100, 100);
		FibonacciHeap fibonacciHeap = new FibonacciHeap();

		ArrayList<Node> nodes = new ArrayList<>(arr.length);
		for (int value : arr) {
			Node node = new Node(value);
			nodes.add(node);
			fibonacciHeap.insert(node);
		}
		Assertions.assertEquals(nodes.size(), fibonacciHeap.size());

		Collections.shuffle(nodes);
		for (Node node : nodes) {
			fibonacciHeap.delete(node);
		}
		Assertions.assertTrue(fibonacciHeap.empty());
	}

	@Test
	void decreaseValue() {
		int[] arr = ArrayGenerator.randomArray(50, 50);
		FibonacciHeap fibonacciHeap = new FibonacciHeap(arr);

		int minimum = Arrays.stream(arr).min().getAsInt();
		Node node = new Node(25);
		fibonacciHeap.insert(node);
		fibonacciHeap.decreaseValue(node, minimum - 1);

		Assertions.assertEquals(minimum - 1, fibonacciHeap.top());
	}

}