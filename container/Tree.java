/**
 * Copyright 2020 jingedawang
 */
package container;

/**
 * <h3>Interface for all kinds of trees</h3>
 */
public interface Tree {

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

}