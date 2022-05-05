/**
 * Copyright 2022 jingedawang
 */
package container;

/**
 * Interface for all kinds of trees.
 */
public interface Tree extends Container {

	/**
	 * Get the root node of the tree.
	 *
	 * @return The root node of the tree.
	 */
	Node getRoot();

	/**
	 * Get the height of the tree.
	 *
	 * @return The height of the tree.
	 */
	int getHeight();

	/**
	 * Insert a node into the tree.
	 *
	 * @param newNode The node to be inserted.
	 */
	void insert(Node newNode);

	/**
	 * Delete a node from the tree.
	 *
	 * @param node The node to be deleted.
	 */
	void delete(Node node);

}