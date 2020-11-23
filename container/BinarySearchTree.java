/**
 * Copyright 2020 jingedawang
 */
package container;

import utils.ArrayGenerator;
import utils.ArrayPrinter;
import utils.TreePrinter;

/**
 * <h3>Binary search tree</h3>
 */
public class BinarySearchTree extends AbstractTree implements SearchTree, BinaryTree {

	/**
	 * Test code.
	 */
	public static void main(String[] args) {
		int[] arr = ArrayGenerator.fixedArray();
//		int[] arr = ArrayGenerator.randomArray(20, 20);

		ArrayPrinter.print(arr);
		BinarySearchTree tree = new BinarySearchTree(arr);
		TreePrinter.print(tree);
		Node searchResult = tree.search(arr[4]);
		System.out.println("The 4-th value of arr is " + searchResult.value);
		Node minimum = tree.minimum();
		System.out.println("The minimum value of the tree is " + minimum.value);
		Node successor = tree.successor(minimum);
		System.out.println("The successor of the minimum is " + successor.value);
		tree.delete(tree.root);
		TreePrinter.print(tree);
	}

	/**
	 * Default constructor.
	 */
	public BinarySearchTree() {
	}

	/**
	 * Constructor with underlying values.
	 * <p>
	 * This constructor will construct the binary search tree according to the order of the input array.
	 *
	 * @param values The values used to constructing the tree.
	 */
	public BinarySearchTree(int[] values) {
		for (int value : values) {
			insert(new Node(value));
		}
	}

	/**
	 * Find a node with value k.
	 *
	 * @param k The value searched in this tree.
	 * @return The node with value k.
	 */
	@Override
	public Node search(int k) {
		return search(root, k);
	}

	/**
	 * Find a node with value k in the subtree specified by {@code root}.
	 *
	 * @param root The root node of the subtree.
	 * @param k    The value searched in this tree.
	 * @return The node with value k.
	 */
	@Override
	public Node search(Node root, int k) {
		while (root != null && k != root.value) {
			if (k < root.value) {
				root = root.left;
			} else {
				root = root.right;
			}
		}
		return root;
	}

	/**
	 * Find the node with minimum value in the tree.
	 *
	 * @return The node with minimum value in the tree.
	 */
	@Override
	public Node minimum() {
		return minimum(root);
	}

	/**
	 * Find the node with minimum value in the subtree specified by {@code root}.
	 *
	 * @param root The root node of the subtree.
	 * @return The node with minimum value in the tree.
	 */
	@Override
	public Node minimum(Node root) {
		while (root.left != null) {
			root = root.left;
		}
		return root;
	}

	/**
	 * Find the node with maximum value in the tree.
	 *
	 * @return The node with maximum value in the tree.
	 */
	@Override
	public Node maximum() {
		return maximum(root);
	}

	/**
	 * Find the node with maximum value in the subtree specified by @{code root}.
	 *
	 * @param root The root node of the subtree.
	 * @return The node with maximum value in the tree.
	 */
	@Override
	public Node maximum(Node root) {
		while (root.right != null) {
			root = root.right;
		}
		return root;
	}

	/**
	 * Find the predecessor node of the given node.
	 *
	 * @param node The node whose predecessor will be found.
	 * @return The predecessor node of the given node.
	 */
	@Override
	public Node predecessor(Node node) {
		if (node.left != null) {
			return maximum(node.left);
		}
		Node parent = node.parent;
		while (parent != null && node == parent.left) {
			node = parent;
			parent = parent.parent;
		}
		return parent;
	}

	/**
	 * Find the successor node of the given node.
	 *
	 * @param node The node whose successor will be found.
	 * @return The successor node of the given node.
	 */
	@Override
	public Node successor(Node node) {
		if (node.right != null) {
			return minimum(node.right);
		}
		Node parent = node.parent;
		while (parent != null && node == parent.right) {
			node = parent;
			parent = parent.parent;
		}
		return parent;
	}

	/**
	 * Insert a node into the tree.
	 *
	 * @param newNode The node to be inserted.
	 */
	@Override
	public void insert(Node newNode) {
		Node parent = null;
		Node node = root;
		while (node != null) {
			parent = node;
			if (newNode.value < node.value) {
				node = node.left;
			} else {
				node = node.right;
			}
		}
		newNode.parent = parent;
		if (parent == null) {
			root = newNode;
		} else if (newNode.value < parent.value) {
			parent.left = newNode;
		} else {
			parent.right = newNode;
		}
	}

	/**
	 * Delete a node from the tree.
	 *
	 * @param node The node to be deleted.
	 */
	@Override
	public void delete(Node node) {
		if (node.left == null) {
			transplant(node.right, node);
		} else if (node.right == null) {
			transplant(node.left, node);
		} else {
			Node y = minimum(node.right);
			if (y.parent != node) {
				transplant(y.right, y);
				y.right = node.right;
				y.right.parent = y;
			}
			transplant(y, node);
			y.left = node.left;
			y.left.parent = y;
		}
	}

	/**
	 * Substitute the target tree by the source tree.
	 *
	 * @param source The root node of the source tree.
	 * @param target The root node of the target tree.
	 */
	private void transplant(Node source, Node target) {
		if (target.parent == null) {
			root = source;
		} else if (target == target.parent.left) {
			target.parent.left = source;
		} else {
			target.parent.right = source;
		}
		if (source != null) {
			source.parent = target.parent;
		}
	}

}