/**
 * Copyright 2022 jingedawang
 */

import container.BTree;
import container.Node;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.ArrayGenerator;
import utils.Seed;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Test class for {@link BTree}.
 */
public class BTreeTest {

	@Test
	void main() {
		BTree.main(new String[]{});
	}

	@Test
	void getHeight() {
		int[] arr = ArrayGenerator.fixedArray();
		BTree bTree = new BTree(arr);
		Assertions.assertEquals(3, bTree.getHeight());
	}

	@Test
	void nodeAssociate() {
		for (int scale = 1; scale < 10; scale++) {
			int[] arr = ArrayGenerator.randomArray(20 * scale, 20 * scale);
			BTree bTree = new BTree(arr, scale + 1);
			checkNode(bTree.getRoot());
			Random random = new Random(Seed.next());
			List<Integer> arrayRemains = Arrays.stream(arr).boxed().collect(Collectors.toList());
			for (int i = 0; i < arr.length; i++) {
				int deleteIndex = random.nextInt(arrayRemains.size());
				Node node = bTree.search(arrayRemains.get(deleteIndex));
				bTree.delete(node);
				arrayRemains.remove(deleteIndex);
				checkNode(bTree.getRoot());
			}
		}
	}

	void checkNode(Node node) {
		if (node.isLeaf) {
			return;
		}
		for (int i = 0; i <= node.numberOfValues; i++) {
			Assertions.assertEquals(i, node.children[i].indexOfParent);
			Assertions.assertEquals(node, node.children[i].parent);
			checkNode(node.children[i]);
		}
	}

}