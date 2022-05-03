/**
 * Copyright 2022 jingedawang
 */
package container;

/**
 * Node class for each element of search tree.
 *
 * To support binary search tree and multi-branch search tree simultaneously, this class provides two ways to keep
 * children. The implementation class can either use left and right fields to represent binary search tree, or use
 * children field to represent multi-branch search tree. But they shouldn't be used together.
 */
public class Node implements Cloneable {

	/**
	 * The color enumeration of the node.
	 */
	public enum Color {
		RED,
		BLACK
	}

	/**
	 * Default constructor.
	 */
	public Node() {

	}

	/**
	 * Constructor with value.
	 *
	 * @param value The value of the node.
	 */
	public Node(int value) {
		this.value = value;
	}

	/**
	 * Constructor for binary search tree node.
	 *
	 * @param value The value of the node.
	 * @param left  The left child of the node.
	 * @param right The right child of the node.
	 */
	public Node(int value, Node left, Node right) {
		this.value = value;
		this.left = left;
		this.right = right;
	}

	/**
	 * Constructor for B-Tree node.
	 *
	 * @param values   The values of the node.
	 * @param children The children of the node.
	 */
	public Node(int[] values, Node[] children) {
		this.values = values;
		this.children = children;
	}

	/**
	 * Constructor with color.
	 *
	 * @param color The color of the node.
	 */
	public Node(Color color) {
		this.color = color;
	}

	///
	/// Fields for binary search tree and red-black tree.
	///

	/**
	 * The key value of the node.
	 */
	public int value;

	/**
	 * The parent of the node.
	 */
	public Node parent;

	/**
	 * The left child of the node.
	 */
	public Node left;

	/**
	 * The right child of the node.
	 */
	public Node right;

	/**
	 * The color of the node, either red or black.
	 */
	public Color color;

	///
	/// Fields for B-Tree.
	///

	/**
	 * Key values of the node.
	 */
	public int[] values;

	/**
	 * Number of values.
	 */
	public int numberOfValues;

	/**
	 * The index of the selected value in values.
	 */
	public int index;

	/**
	 * The index of this node in its parent's children.
	 */
	public int indexOfParent;

	/**
	 * All the children of the node.
	 */
	public Node[] children;

	/**
	 * Flag indicating whether this node is leaf node or not.
	 */
	public boolean isLeaf;

	/**
	 * Clone this node.
	 * <p>
	 * Since we are conducting a deep copy, so the entire tree under this node will be cloned.
	 *
	 * @return A copy of this node.
	 */
	@Override
	protected Node clone() {
		Node node;
		try {
			node = (Node) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			throw new AssertionError();
		}
		if (left != null) {
			node.left = left.clone();
			if (left.parent != null) {
				node.left.parent = node;
			}
		}
		if (right != null) {
			node.right = right.clone();
			if (right.parent != null) {
				node.right.parent = node;
			}
		}
		if (values != null) {
			node.values = values.clone();
		}
		if (children != null) {
			node.children = new Node[children.length];
			for (int i = 0; i < children.length; i++) {
				node.children[i] = children[i].clone();
			}
		}
		return node;
	}

}