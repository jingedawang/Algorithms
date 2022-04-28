/**
 * Copyright 2020 jingedawang
 */
package container;

/**
 * <h3>Interface for all search tree classes</h3>
 */
public interface SearchTree extends Tree {

	/**
	 * Find a node with value k.
	 *
	 * @param k The value searched in this tree.
	 * @return The node with value k.
	 */
	Node search(int k);

	/**
	 * Find a node with value k in the subtree specified by {@code root}.
	 *
	 * @param root The root node of the subtree.
	 * @param k    The value searched in this tree.
	 * @return The node with value k.
	 */
	Node search(Node root, int k);

	/**
	 * Find the node with minimum value in the tree.
	 *
	 * @return The node with minimum value in the tree.
	 */
	Node minimum();

	/**
	 * Find the node with minimum value in the subtree specified by {@code root}.
	 *
	 * @param root The root node of the subtree.
	 * @return The node with minimum value in the tree.
	 */
	Node minimum(Node root);

	/**
	 * Find the node with maximum value in the tree.
	 *
	 * @return The node with maximum value in the tree.
	 */
	Node maximum();

	/**
	 * Find the node with maximum value in the subtree specified by {@code root}.
	 *
	 * @param root The root node of the subtree.
	 * @return The node with maximum value in the tree.
	 */
	Node maximum(Node root);

	/**
	 * Find the predecessor node of the given node.
	 *
	 * @param node The node whose predecessor will be found.
	 * @return The predecessor node of the given node.
	 */
	Node predecessor(Node node);

	/**
	 * Find the successor node of the given node.
	 *
	 * @param node The node whose successor will be found.
	 * @return The successor node of the given node.
	 */
	Node successor(Node node);

}