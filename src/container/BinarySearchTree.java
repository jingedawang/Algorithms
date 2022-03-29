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
		root = nil;
	}

	/**
	 * Constructor with underlying values.
	 * <p>
	 * This constructor will construct the binary search tree according to the order of the input array.
	 *
	 * @param values The values used to constructing the tree.
	 */
	public BinarySearchTree(int[] values) {
		root = nil;
		for (int value : values) {
			insert(new Node(value, nil, nil));
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
		while (root != nil && k != root.value) {
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
		while (root.left != nil) {
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
		while (root.right != nil) {
			root = root.right;
		}
		return root;
	}

	/**
	 * Find the real predecessor of the given node.
	 * <p>
	 * The node found must have different value with the given node.
	 *
	 * @param node The node whose predecessor will be found.
	 * @return The predecessor node of the given node.
	 */
	@Override
	public Node predecessor(Node node) {
		Node predecessor = predecessorNode(node);
		while (predecessor != nil && predecessor.value == node.value) {
			predecessor = predecessorNode(predecessor);
		}
		return predecessor;
	}

	/**
	 * Find the predecessor node of the given node.
	 *
	 * @param node The node whose predecessor will be found.
	 * @return The predecessor node of the given node.
	 */
	public Node predecessorNode(Node node) {
		if (node.left != nil) {
			return maximum(node.left);
		}
		Node parent = node.parent;
		while (parent != nil && node == parent.left) {
			node = parent;
			parent = parent.parent;
		}
		return parent;
	}

	/**
	 * Find the real successor of the given node.
	 * <p>
	 * The node found must have different value with the given node.
	 *
	 * @param node The node whose successor will be found.
	 * @return The successor node of the given node.
	 */
	@Override
	public Node successor(Node node) {
		Node successor = successorNode(node);
		while (successor != nil && successor.value == node.value) {
			successor = successorNode(successor);
		}
		return successor;
	}

	/**
	 * Find the successor node of the given node.
	 *
	 * @param node The node whose successor will be found.
	 * @return The successor node of the given node.
	 */
	public Node successorNode(Node node) {
		if (node.right != nil) {
			return minimum(node.right);
		}
		Node parent = node.parent;
		while (parent != nil && node == parent.right) {
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
		Node parent = nil;
		Node node = root;
		while (node != nil) {
			parent = node;
			if (newNode.value < node.value) {
				node = node.left;
			} else {
				node = node.right;
			}
		}
		newNode.parent = parent;
		if (parent == nil) {
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
		if (node.left == nil) {
			transplant(node.right, node);
		} else if (node.right == nil) {
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
	 * Get the nil node.
	 *
	 * @return The nil node.
	 */
	public Node getNil() {
		return nil;
	}

	/**
	 * Clone this tree.
	 *
	 * @return A copy of this tree.
	 */
	@Override
	protected BinarySearchTree clone() {
		nil.parent = null;
		nil.left = null;
		nil.right = null;
		BinarySearchTree tree = (BinarySearchTree) super.clone();
		if (tree.root.left == null && tree.root.right == null) {
			tree.root = nil;
		} else {
			useNil(tree.root);
		}
		return tree;
	}

	/**
	 * Use nil sentinel instead of extra leaf nodes.
	 * <p>
	 * This method is used to fix up the cloned tree.
	 *
	 * @param node The root of the current subtree.
	 */
	private void useNil(Node node) {
		if (node.left.parent == null) {
			node.left = nil;
		} else {
			useNil(node.left);
		}
		if (node.right.parent == null) {
			node.right = nil;
		} else {
			useNil(node.right);
		}
	}

	/**
	 * Remove nil sentinel and return as a binary tree.
	 *
	 * @return A binary tree without nil.
	 */
	public BinaryTree toBinaryTree() {
		BinaryTree tree = (BinaryTree) clone();
		removeNil(tree.getRoot());
		return tree;
	}

	/**
	 * Remove nil sentinel recursively.
	 *
	 * @param node The root node of current subtree.
	 */
	private void removeNil(Node node) {
		if (node.left == nil) {
			node.left = null;
		} else {
			removeNil(node.left);
		}
		if (node.right == nil) {
			node.right = null;
		} else {
			removeNil(node.right);
		}
	}

	/**
	 * Substitute the target tree by the source tree.
	 *
	 * @param source The root node of the source tree.
	 * @param target The root node of the target tree.
	 */
	protected void transplant(Node source, Node target) {
		if (target.parent == nil) {
			root = source;
		} else if (target == target.parent.left) {
			target.parent.left = source;
		} else {
			target.parent.right = source;
		}
		source.parent = target.parent;
	}

	/**
	 * A sentinel node indicting all the external nodes.
	 */
	protected final Node nil = new Node(Node.Color.BLACK);

}