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
public class Node {

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
}