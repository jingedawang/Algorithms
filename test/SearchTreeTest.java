/**
 * Copyright 2020 jingedawang
 */
package test;

import container.BTree;
import container.BinarySearchTree;
import container.Node;
import container.RedBlackTree;
import container.SearchTree;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.ArrayGenerator;

import java.util.Arrays;

/**
 * <h3>Test class for {@link SearchTree}</h3>
 */
public class SearchTreeTest {

	@Test
	void searchTree() {
//		int[] arr = ArrayGenerator.fixedArray();
		int[] arr = ArrayGenerator.randomArray(20, 100);

		BinarySearchTree binarySearchTree = new BinarySearchTree(arr);
		RedBlackTree redBlackTree = new RedBlackTree(arr);
		BTree bTree = new BTree(arr);

		Arrays.sort(arr);
		int[] uniqueArr = new int[arr.length];
		int count = 1;
		uniqueArr[0] = arr[0];
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] != uniqueArr[count - 1]) {
				uniqueArr[count++] = arr[i];
			}
		}
		arr = new int[count];
		System.arraycopy(uniqueArr, 0, arr, 0, count);

		Assertions.assertEquals(arr[0], binarySearchTree.minimum().value);
		Assertions.assertEquals(arr[0], redBlackTree.minimum().value);
		Assertions.assertEquals(arr[0], bTree.minimum().values[bTree.minimum().index]);

		Assertions.assertEquals(arr[arr.length - 1], binarySearchTree.maximum().value);
		Assertions.assertEquals(arr[arr.length - 1], redBlackTree.maximum().value);
		Assertions.assertEquals(arr[arr.length - 1], bTree.maximum().values[bTree.maximum().index]);

		for (int i = 0; i < arr.length; i++) {
			Node binarySearchTreeNode = binarySearchTree.search(arr[i]);
			Node redBlackTreeNode = redBlackTree.search(arr[i]);
			Node bTreeNode = bTree.search(arr[i]);
			Assertions.assertEquals(arr[i], binarySearchTreeNode.value);
			Assertions.assertEquals(arr[i], redBlackTreeNode.value);
			Assertions.assertEquals(arr[i], bTreeNode.values[bTreeNode.index]);

			if (i > 0) {
				Node binarySearchTreePredecessor = binarySearchTree.predecessor(binarySearchTreeNode);
				Node redBlackTreePredecessor = redBlackTree.predecessor(redBlackTreeNode);
				Node bTreePredecessor = bTree.predecessor(bTreeNode);
				Assertions.assertEquals(arr[i - 1], binarySearchTreePredecessor.value);
				Assertions.assertEquals(arr[i - 1], redBlackTreePredecessor.value);
				Assertions.assertEquals(arr[i - 1], bTreePredecessor.values[bTreePredecessor.index]);
			}

			// Re-search the node to fix the selected index because the predecessor operation may modify bTreeNode.
			bTreeNode = bTree.search(arr[i]);

			if (i < arr.length - 1) {
				Node binarySearchTreeSuccessor = binarySearchTree.successor(binarySearchTreeNode);
				Node redBlackTreeSuccessor = redBlackTree.successor(redBlackTreeNode);
				Node bTreeSuccessor = bTree.successor(bTreeNode);
				Assertions.assertEquals(arr[i + 1], binarySearchTreeSuccessor.value);
				Assertions.assertEquals(arr[i + 1], redBlackTreeSuccessor.value);
				Assertions.assertEquals(arr[i + 1], bTreeSuccessor.values[bTreeSuccessor.index]);
			}

		}
	}

}
