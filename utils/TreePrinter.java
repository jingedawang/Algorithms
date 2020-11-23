package utils;

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
	 */
	public static void print(BinaryTree tree) {
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
					nextLayer.add(node.left);
					nextLayer.add(node.right);
					stringBuilder.append(repeatedChars(width * cellSize / 4, ' '));
					if (depth == height) {
						stringBuilder.append(repeatedChars(width * cellSize / 4 - 1, ' '));
					} else {
						stringBuilder.append('+');
						stringBuilder.append(repeatedChars(width * cellSize / 4 - 2, '-'));
					}
					stringBuilder.append(node.value);
					if (depth == height) {
						stringBuilder.append(repeatedChars(width * cellSize / 4, ' '));
					} else {
						stringBuilder.append(repeatedChars(width * cellSize / 4 - 1, '-'));
						stringBuilder.append('+');
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