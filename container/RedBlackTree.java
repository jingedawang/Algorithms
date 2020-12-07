/**
 * Copyright 2020 jingedawang
 */
package container;

import utils.ArrayGenerator;
import utils.ArrayPrinter;
import utils.TreePrinter;

/**
 * <h3>Red black tree</h3>
 * <p>
 * Red black tree is a balanced binary search tree. No search path is twice as long as the others.
 */
public class RedBlackTree extends BinarySearchTree {

	/**
	 * Test code.
	 */
	public static void main(String[] args) {
		int[] arr = ArrayGenerator.fixedArray();
//		int[] arr = ArrayGenerator.randomArray(20, 20);

		ArrayPrinter.print(arr);
		RedBlackTree tree = new RedBlackTree(arr);
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
	public RedBlackTree() {
		super();
	}

	/**
	 * Constructor with underlying values.
	 * <p>
	 * This constructor will construct the red black tree according to the order of the input array.
	 *
	 * @param values The values used to constructing the tree.
	 */
	public RedBlackTree(int[] values) {
		super(values);
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
		// Find the insert point
		while (node != nil) {
			parent = node;
			if (newNode.value < node.value) {
				node = node.left;
			} else {
				node = node.right;
			}
		}
		// Link new node to its parent
		newNode.parent = parent;
		if (parent == nil) {
			root = newNode;
		} else if (newNode.value < parent.value) {
			parent.left = newNode;
		} else {
			parent.right = newNode;
		}
		newNode.left = nil;
		newNode.right = nil;
		newNode.color = Node.Color.RED;
		insertFixUp(newNode);
	}

	/**
	 * Delete a node from the tree.
	 *
	 * @param node The node to be deleted.
	 */
	@Override
	public void delete(Node node) {
		Node y = node;
		Node.Color yOriginalColor = y.color;
		Node x;
		if (node.left == nil) {
			x = node.right;
			transplant(node.right, node);
		} else if (node.right == nil) {
			x = node.left;
			transplant(node.left, node);
		} else {
			y = minimum(node.right);
			yOriginalColor = y.color;
			x = y.right;
			if (y.parent == node) {
				// Note that this operation is useful when x is nil.
				// We should fix-up from this nil node upwards in deleteFixUp method.
				x.parent = y;
			} else {
				transplant(y.right, y);
				y.right = node.right;
				y.right.parent = y;
			}
			transplant(y, node);
			y.left = node.left;
			y.left.parent = y;
			y.color = node.color;
		}
		if (yOriginalColor == Node.Color.BLACK) {
			deleteFixUp(x);
		}
	}

	/**
	 * Left rotate a node with its right child.
	 *
	 * @param node The parent node to be rotated.
	 */
	private void leftRotate(Node node) {
		Node rightChild = node.right;
		node.right = rightChild.left;
		if (rightChild.left != nil) {
			rightChild.left.parent = node;
		}
		rightChild.parent = node.parent;
		if (node.parent == nil) {
			root = rightChild;
		} else if (node == node.parent.left) {
			node.parent.left = rightChild;
		} else {
			node.parent.right = rightChild;
		}
		rightChild.left = node;
		node.parent = rightChild;
	}

	/**
	 * Right rotate a node with its left child.
	 *
	 * @param node The parent node to be rotated.
	 */
	private void rightRotate(Node node) {
		Node leftChild = node.left;
		node.left = leftChild.right;
		if (leftChild.right != nil) {
			leftChild.right.parent = node;
		}
		leftChild.parent = node.parent;
		if (node.parent == nil) {
			root = leftChild;
		} else if (node == node.parent.left) {
			node.parent.left = leftChild;
		} else {
			node.parent.right = leftChild;
		}
		leftChild.right = node;
		node.parent = leftChild;
	}

	/**
	 * Fix up the red black tree after inserting.
	 * <p>
	 * The new node may lead to successive red nodes. So this method is used to adjust the colors and positions among
	 * the surrounding nodes to recover the red black properties.
	 *
	 * @param newNode The new inserted node.
	 */
	private void insertFixUp(Node newNode) {
		while (newNode.parent.color == Node.Color.RED) {
			if (newNode.parent == newNode.parent.parent.left) {
				Node uncle = newNode.parent.parent.right;
				if (uncle.color == Node.Color.RED) {
					newNode.parent.color = Node.Color.BLACK;
					uncle.color = Node.Color.BLACK;
					newNode.parent.parent.color = Node.Color.RED;
					newNode = newNode.parent.parent;
				} else {
					if (newNode == newNode.parent.right) {
						newNode = newNode.parent;
						leftRotate(newNode);
					}
					newNode.parent.color = Node.Color.BLACK;
					newNode.parent.parent.color = Node.Color.RED;
					rightRotate(newNode.parent.parent);
				}
			} else {
				Node uncle = newNode.parent.parent.left;
				if (uncle.color == Node.Color.RED) {
					newNode.parent.color = Node.Color.BLACK;
					uncle.color = Node.Color.BLACK;
					newNode.parent.parent.color = Node.Color.RED;
					newNode = newNode.parent.parent;
				} else {
					if (newNode == newNode.parent.left) {
						newNode = newNode.parent;
						rightRotate(newNode);
					}
					newNode.parent.color = Node.Color.BLACK;
					newNode.parent.parent.color = Node.Color.RED;
					leftRotate(newNode.parent.parent);
				}
			}
		}
		root.color = Node.Color.BLACK;
	}

	/**
	 * Fix up the red black tree after deleting.
	 * <p>
	 * The x node embodies an extra color to maintain the black height after deleting. So this method is aiming to
	 * distribute the extra color to other nodes while holding the red black properties.
	 *
	 * @param x The node that destroys the red black properties.
	 */
	private void deleteFixUp(Node x) {
		while (x != root && x.color == Node.Color.BLACK) {
			if (x == x.parent.left) {
				Node brother = x.parent.right;
				if (brother.color == Node.Color.RED) {
					brother.color = Node.Color.BLACK;
					x.parent.color = Node.Color.RED;
					leftRotate(x.parent);
					brother = x.parent.right;
				}
				if (brother.left.color == Node.Color.BLACK && brother.right.color == Node.Color.BLACK) {
					brother.color = Node.Color.RED;
					x = x.parent;
				} else {
					if (brother.right.color == Node.Color.BLACK) {
						brother.left.color = Node.Color.BLACK;
						brother.color = Node.Color.RED;
						rightRotate(brother);
						brother = x.parent.right;
					}
					brother.color = x.parent.color;
					x.parent.color = Node.Color.BLACK;
					brother.right.color = Node.Color.BLACK;
					leftRotate(x.parent);
					x = root;
				}
			} else {
				Node brother = x.parent.left;
				if (brother.color == Node.Color.RED) {
					brother.color = Node.Color.BLACK;
					x.parent.color = Node.Color.RED;
					rightRotate(x.parent);
					brother = x.parent.left;
				}
				if (brother.left.color == Node.Color.BLACK && brother.right.color == Node.Color.BLACK) {
					brother.color = Node.Color.RED;
					x = x.parent;
				} else {
					if (brother.left.color == Node.Color.BLACK) {
						brother.right.color = Node.Color.BLACK;
						brother.color = Node.Color.RED;
						leftRotate(brother);
						brother = x.parent.left;
					}
					brother.color = x.parent.color;
					x.parent.color = Node.Color.BLACK;
					brother.left.color = Node.Color.BLACK;
					rightRotate(x.parent);
					x = root;
				}
			}
		}
		x.color = Node.Color.BLACK;
	}

}