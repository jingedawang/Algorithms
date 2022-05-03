/**
 * Copyright 2022 jingedawang
 */
package container;

/**
 * Base class for all kinds of trees.
 * <p>
 * Some general methods of trees are provided here.
 */
public class AbstractTree implements Tree, Cloneable {

	/**
	 * Checks if the tree is empty.
	 *
	 * @return {@code true} if the tree has no elements, {@code false} otherwise.
	 */
	@Override
	public boolean empty() {
		return root == null;
	}

	/**
	 * Get the size of the tree.
	 *
	 * @return The element count of the tree.
	 */
	@Override
	public int size() {
		if (root == null) {
			return 0;
		}
		return subtreeSize(root);
	}

	/**
	 * Get the size of the subtree.
	 *
	 * @param root The root of the subtree.
	 * @return The size of the subtree.
	 */
	protected int subtreeSize(Node root) {
		if (root == null) {
			return 0;
		}
		int size = 1;
		if (root.values != null) {
			size = root.numberOfValues;
		}
		size += subtreeSize(root.left);
		size += subtreeSize(root.right);
		if (root.children != null) {
			for (int i = 0; i <= root.numberOfValues; i++) {
				size += subtreeSize(root.children[i]);
			}
		}
		return size;
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
	 * Insert a node into the tree.
	 *
	 * @param newNode The node to be inserted.
	 */
	@Override
	public void insert(Node newNode) {

	}

	/**
	 * Delete a node from the tree.
	 *
	 * @param node The node to be deleted.
	 */
	@Override
	public void delete(Node node) {
		throw new UnsupportedOperationException("'delete' method not supported for object: " + this);
	}

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
	 * Compute the height of the tree in a recursive way.
	 *
	 * @param root The root of the tree.
	 * @return The height of the tree.
	 */
	private int computeHeight(Node root) {
		if (root == null) {
			return 0;
		}

		// For binary tree
		int maxSubtreeHeight = Math.max(computeHeight(root.left), computeHeight(root.right));

		// For B-tree
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