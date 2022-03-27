/**
 * Copyright 2020 jingedawang
 */
package container;

/**
 * <h3>The abstract class of tree</h3>
 * <p>
 * Some general methods are implemented here.
 */
public class AbstractTree implements Tree, Cloneable {

	/**
	 * Clone this tree.
	 *
	 * @return A copy of this tree.
	 */
	@Override
	protected AbstractTree clone() {
		AbstractTree tree;
		try {
			tree = (AbstractTree) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			throw new AssertionError();
		}
		tree.root = root.clone();
		return tree;
	}

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
		int maxSubtreeHeight = Math.max(computeHeight(root.left), computeHeight(root.right));
		if (root.children != null) {
			for (int i = 0; i < root.children.length; i++) {
				int ithHeight = computeHeight(root.children[i]);
				if (ithHeight > maxSubtreeHeight) {
					maxSubtreeHeight = ithHeight;
				}
			}
		}
		return 1 + maxSubtreeHeight;
	}

	/**
	 * The root of the tree.
	 */
	protected Node root;

}