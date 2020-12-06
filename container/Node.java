/**
 * Copyright 2020 jingedawang
 */
package container;

/**
 * <h3>Node class for each element of search tree</h3>
 * <p>
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
	 * Constructor with value and two child nodes.
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
	 * Constructor with value and children.
	 *
	 * @param value    The value of the node.
	 * @param children The children of the node.
	 */
	public Node(int value, Node[] children) {
		this.value = value;
		this.children = children;
	}

	/**
	 * Constructor with color.
	 * @param color The color of the node.
	 */
	public Node(Color color) {
		this.color = color;
	}

	/**
	 * Clone this node.
	 *
	 * Since we are conducting a deep copy, so the entire tree under this node will be cloned.
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
		return node;
	}

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
	 * All the children of the node.
	 */
	public Node[] children;

	/**
	 * The color of the node, either red or black.
	 */
	public Color color;
}