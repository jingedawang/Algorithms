/**
 * Copyright 2022 jingedawang
 */

import container.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.ArrayGenerator;

/**
 * Test class for {@link Container}.
 */
public class ContainerTest {

	@Test
	void empty() {
		BinarySearchTree binarySearchTree = new BinarySearchTree();
		BTree bTree = new BTree();
		BinaryHeap binaryHeap = new BinaryHeap();
		FibonacciHeap fibonacciHeap = new FibonacciHeap();
		PriorityQueue priorityQueue = new PriorityQueue();
		RedBlackTree redBlackTree = new RedBlackTree();

		Assertions.assertTrue(binarySearchTree.empty());
		Assertions.assertTrue(bTree.empty());
		Assertions.assertTrue(fibonacciHeap.empty());
		Assertions.assertTrue(binaryHeap.empty());
		Assertions.assertTrue(priorityQueue.empty());
		Assertions.assertTrue(redBlackTree.empty());
	}

	@Test
	void size() {
		int[] arr = ArrayGenerator.randomArray(30, 33);
		BinarySearchTree binarySearchTree = new BinarySearchTree(arr.clone());
		BTree bTree = new BTree(arr.clone());
		BinaryHeap binaryHeap = new BinaryHeap(arr.clone(), true);
		FibonacciHeap fibonacciHeap = new FibonacciHeap(arr.clone());
		PriorityQueue priorityQueue = new PriorityQueue(arr.clone());
		RedBlackTree redBlackTree = new RedBlackTree(arr.clone());

		Assertions.assertEquals(arr.length, binarySearchTree.size());
		Assertions.assertEquals(arr.length, bTree.size());
		Assertions.assertEquals(arr.length, binaryHeap.size());
		Assertions.assertEquals(arr.length, fibonacciHeap.size());
		Assertions.assertEquals(arr.length, priorityQueue.size());
		Assertions.assertEquals(arr.length, redBlackTree.size());
	}

}