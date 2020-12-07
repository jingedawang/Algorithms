/**
 * Copyright 2020 jingedawang
 */
package test;

import container.Node;
import container.RedBlackTree;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.ArrayGenerator;
import utils.Seed;

import java.util.Random;

/**
 * <h3>Test class for {@link RedBlackTree}</h3>
 */
public class RedBlackTreeTest {

	@Test
	void insert() {
		for (int i = 0; i < 100; i++) {
			int[] arr = ArrayGenerator.randomArray(20, 20);
			RedBlackTree tree = new RedBlackTree(arr);
			checkTree(tree);
		}
	}

	@Test
	void delete() {
		for (int i = 0; i < 100; i++) {
			int[] arr = ArrayGenerator.randomArray(20, 20);
			RedBlackTree tree = new RedBlackTree(arr);
			Random random = new Random(Seed.next());
			int deleteIndex = random.nextInt(20);
			Node deleteNode = tree.search(arr[deleteIndex]);
			tree.delete(deleteNode);
			checkTree(tree);
		}
	}

	void checkTree(RedBlackTree tree) {
		Assertions.assertEquals(tree.getRoot().color, Node.Color.BLACK);
		Assertions.assertEquals(tree.getNil().color, Node.Color.BLACK);
		checkNodeAndReturnBlackHeight(tree.getRoot(), tree.getNil());
	}

	int checkNodeAndReturnBlackHeight(Node node, Node nil) {
		if (node == nil) {
			return 1;
		}
		if (node.color == Node.Color.RED) {
			Assertions.assertEquals(node.left.color, Node.Color.BLACK);
			Assertions.assertEquals(node.right.color, Node.Color.BLACK);
		}
		int leftBlackHeight = checkNodeAndReturnBlackHeight(node.left, nil);
		int rightBlackHeight = checkNodeAndReturnBlackHeight(node.right, nil);
		Assertions.assertEquals(leftBlackHeight, rightBlackHeight);
		return node.color == Node.Color.BLACK ? leftBlackHeight + 1 : leftBlackHeight;
	}

}