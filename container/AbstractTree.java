/**
 * Copyright 2020 jingedawang
 */
package container;

/**
 * <h3>The abstract class of tree</h3>
 * <p>
 * Some general methods are implemented here.
 */
public class AbstractTree implements Tree {

	/**
	 * Get the root node of the tree.
	 *
	 * @return The root node of the tree.
	 */
	@Override
	public Node getRoot() {
		return root;
	}

	/**
	 * Get the height of the tree.
	 *
	 * @return The height of the tree.
	 */
	@Override
	public int getHeight() {
		return computeHeight(root);
	}

	/**
	 * Compute the height of the tree in a recursive way.
	 *
	 * @param root The root of the tree.
	 * @return The height of the tree.
	 */
	private int computeHeight(Node root) {
		if (root == null) {
			return 0;
		}
		return 1 + Math.max(computeHeight(root.left), computeHeight(root.right));
	}

	/**
	 * The root of the tree.
	 */
	protected Node root;

}