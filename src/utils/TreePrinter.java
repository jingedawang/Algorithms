package utils;

import container.BTree;
import container.BinarySearchTree;
import container.BinaryTree;
import container.Node;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * <h3>Tree printer</h3>
 *
 * This class can display a tree in the console.
 */
public class TreePrinter {

	/**
	 * Print a binary tree.
	 *
	 * To show the display pattern, here is a simple example.
	 *                 +--------------4---------------+
	 *         +------1-------+                +------8-------+
	 *     +--0---+        +--2---+        +--6---+        +--9---+
	 *                    1       3       5
	 * @param tree The binary tree to be printed.
	 * @param showColor Choose whether to show the color of each node.
	 */
	public static void print(BinaryTree tree, boolean showColor) {
		Node root = tree.getRoot();
		if (root == null) {
			return;
		}
		final int cellSize = 8;
		int height = tree.getHeight();
		ArrayList<Node> currentLayer = new ArrayList<>();
		ArrayList<Node> nextLayer = new ArrayList<>();
		currentLayer.add(root);
		int depth = 1;
		while (depth <= height) {
			int width = 1 << (height - depth);
			StringBuilder stringBuilder = new StringBuilder();
			for (int i = 0; i < currentLayer.size(); i++) {
				Node node = currentLayer.get(i);
				if (node == null) {
					nextLayer.add(null);
					nextLayer.add(null);
					stringBuilder.append(repeatedChars(width * cellSize, ' '));
				} else {
					char marker = showColor ? (node.color == Node.Color.RED ? 'r' : 'b') : '+';
					nextLayer.add(node.left);
					nextLayer.add(node.right);
					stringBuilder.append(repeatedChars(width * cellSize / 4, ' '));
					if (depth == height) {
						stringBuilder.append(repeatedChars(width * cellSize / 4 - 1, ' '));
					} else {
						stringBuilder.append(marker);
						stringBuilder.append(repeatedChars(width * cellSize / 4 - 2, '-'));
					}
					stringBuilder.append(node.value);
					if (depth == height) {
						stringBuilder.append(repeatedChars(width * cellSize / 4, ' '));
					} else {
						stringBuilder.append(repeatedChars(width * cellSize / 4 - 1, '-'));
						stringBuilder.append(marker);
					}
					stringBuilder.append(repeatedChars(width * cellSize / 4, ' '));
				}
			}
			System.out.println(stringBuilder.toString());
			currentLayer = nextLayer;
			nextLayer = new ArrayList<>();
			nextLayer.ensureCapacity(1 << depth++);
		}
	}

	/**
	 * Print a binary tree.
	 * @param tree The binary tree to be printed.
	 */
	public static void print(BinaryTree tree) {
		print(tree, false);
	}

	/**
	 * Print a binary search tree.
	 * @param tree The binary search tree to be printed.
	 */
	public static void print(BinarySearchTree tree) {
		print(tree.toBinaryTree(), true);
	}

	/**
	 * Print a B-Tree.
	 * @param tree The B-Tree to be printed.
	 */
	public static void print(BTree tree) {
		ArrayList<Node> row = new ArrayList<>();
		ArrayList<Node> nextRow;
		row.add(tree.getRoot());
		int level = 0;
		while (!row.isEmpty()) {
			nextRow = new ArrayList<>((row.size() + 2) * 2 * tree.getMinimumDegree());
			System.out.print("Level " + level++ + ": ");
			StringBuilder stringBuilder = new StringBuilder();
			boolean isLeaf = false;
			for (Node node : row) {
				stringBuilder.append('|');
				nextRow.add(null);
				if (node != null) {
					for (int i = 0; i < node.numberOfValues; i++) {
						stringBuilder.append(node.values[i]);
						stringBuilder.append(',');
					}
					for (int i = 0; i < node.numberOfValues + 1; i++) {
						nextRow.add(node.children[i]);
					}
					stringBuilder.append('|');
					nextRow.add(null);
					isLeaf = node.isLeaf;
				}
			}
			System.out.println(stringBuilder.toString());
			if (isLeaf) {
				nextRow.clear();
			}
			row = nextRow;
		}
	}

	/**
	 * Generate repeated characters.
	 * @param count The count of the repeated characters.
	 * @param c The specified character.
	 * @return A generated character array.
	 */
	private static char[] repeatedChars(int count, char c) {
		char[] chars = new char[count];
		Arrays.fill(chars, c);
		return chars;
	}

}