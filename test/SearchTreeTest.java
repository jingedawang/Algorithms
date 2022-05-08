/**
 * Copyright 2022 jingedawang
 */
import container.BTree;
import container.BinarySearchTree;
import container.Node;
import container.RedBlackTree;
import container.SearchTree;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.ArrayGenerator;
import utils.Seed;

import java.util.Arrays;
import java.util.Random;

/**
 * Test class for {@link SearchTree}.
 */
public class SearchTreeTest {

	@Test
	void main() {
		BinarySearchTree.main(new String[]{});
		BTree.main(new String[]{});
		RedBlackTree.main(new String[]{});
	}

	@Test
	void searchTree() {
		for (int loop = 1; loop < 50; loop += 3) {
			int[] arr = ArrayGenerator.randomArray(20, loop);

			BinarySearchTree binarySearchTree = new BinarySearchTree(arr);
			RedBlackTree redBlackTree = new RedBlackTree(arr);
			BTree bTree = new BTree(arr);

			Arrays.sort(arr);
			Random random = new Random(Seed.next());
			for (int k = 0; k < arr.length; k++) {
				int[] uniqueArr = Arrays.stream(arr).distinct().toArray();

				Assertions.assertEquals(uniqueArr[0], binarySearchTree.minimum().value);
				Assertions.assertEquals(uniqueArr[0], redBlackTree.minimum().value);
				Assertions.assertEquals(uniqueArr[0], bTree.minimum().values[bTree.minimum().index]);

				Assertions.assertEquals(uniqueArr[uniqueArr.length - 1], binarySearchTree.maximum().value);
				Assertions.assertEquals(uniqueArr[uniqueArr.length - 1], redBlackTree.maximum().value);
				Assertions.assertEquals(uniqueArr[uniqueArr.length - 1], bTree.maximum().values[bTree.maximum().index]);

				for (int i = 0; i < uniqueArr.length; i++) {
					Node binarySearchTreeNode = binarySearchTree.search(uniqueArr[i]);
					Node redBlackTreeNode = redBlackTree.search(uniqueArr[i]);
					Node bTreeNode = bTree.search(uniqueArr[i]);
					Assertions.assertEquals(uniqueArr[i], binarySearchTreeNode.value);
					Assertions.assertEquals(uniqueArr[i], redBlackTreeNode.value);
					Assertions.assertEquals(uniqueArr[i], bTreeNode.values[bTreeNode.index]);

					if (i > 0) {
						Node binarySearchTreePredecessor = binarySearchTree.predecessor(binarySearchTreeNode);
						Node redBlackTreePredecessor = redBlackTree.predecessor(redBlackTreeNode);
						Node bTreePredecessor = bTree.predecessor(bTreeNode);
						Assertions.assertEquals(uniqueArr[i - 1], binarySearchTreePredecessor.value);
						Assertions.assertEquals(uniqueArr[i - 1], redBlackTreePredecessor.value);
						Assertions.assertEquals(uniqueArr[i - 1], bTreePredecessor.values[bTreePredecessor.index]);
					}

					// Re-search the node to fix the selected index because the predecessor operation may modify bTreeNode.
					bTreeNode = bTree.search(uniqueArr[i]);

					if (i < uniqueArr.length - 1) {
						Node binarySearchTreeSuccessor = binarySearchTree.successor(binarySearchTreeNode);
						Node redBlackTreeSuccessor = redBlackTree.successor(redBlackTreeNode);
						Node bTreeSuccessor = bTree.successor(bTreeNode);
						Assertions.assertEquals(uniqueArr[i + 1], binarySearchTreeSuccessor.value);
						Assertions.assertEquals(uniqueArr[i + 1], redBlackTreeSuccessor.value);
						Assertions.assertEquals(uniqueArr[i + 1], bTreeSuccessor.values[bTreeSuccessor.index]);
					}
				}

				int deleteIndex = random.nextInt(arr.length);
				int deleteValue = arr[deleteIndex];
				int[] newArr = new int[arr.length - 1];
				System.arraycopy(arr, 0, newArr, 0, deleteIndex);
				System.arraycopy(arr, deleteIndex + 1, newArr, deleteIndex, arr.length - deleteIndex - 1);
				arr = newArr;
				Node deleteBinarySearchNode = binarySearchTree.search(deleteValue);
				Node deleteRedBlackNode = redBlackTree.search(deleteValue);
				Node deleteBNode = bTree.search(deleteValue);
				binarySearchTree.delete(deleteBinarySearchNode);
				redBlackTree.delete(deleteRedBlackNode);
				bTree.delete(deleteBNode);
			}
		}
	}

}