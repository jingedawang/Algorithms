/**
 * Copyright 2022 jingedawang
 */

import container.BTree;
import container.BinarySearchTree;
import container.Heap;
import container.RedBlackTree;
import org.junit.jupiter.api.Test;
import utils.ArrayGenerator;
import utils.TreePrinter;

/**
 * Test class for {@link TreePrinter}.
 */
public class TreePrinterTest {

	@Test
	void print() {
		for (int i = 0; i < 100; i += 20) {
			int[] arr = ArrayGenerator.randomArray(i * 2, i);
			BinarySearchTree binarySearchTree = new BinarySearchTree(arr);
			BTree btree = new BTree(arr);
			Heap heap = new Heap(arr, true);
			RedBlackTree redBlackTree = new RedBlackTree(arr);

			TreePrinter.print(binarySearchTree);
			TreePrinter.print(btree);
			TreePrinter.print(heap);
			TreePrinter.print(redBlackTree);
		}
	}

}