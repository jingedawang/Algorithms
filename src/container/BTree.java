/**
 * Copyright 2022 jingedawang
 */
package container;

import utils.ArrayGenerator;
import utils.ArrayPrinter;
import utils.TreePrinter;

/**
 * <h3>B-Tree</h3>
 * <p>
 * B-Tree is a balanced multi-branch search tree. The search path is shorter than Red-Black tree, and it performs better
 * when data is stored on disk.
 */
public class BTree extends AbstractTree implements SearchTree {

	/**
	 * Test code.
	 */
	public static void main(String[] args) {
		int[] arr = ArrayGenerator.fixedArray();
//		int[] arr = ArrayGenerator.randomArray(20, 20);

		ArrayPrinter.print(arr);
		BTree tree = new BTree(arr);
		TreePrinter.print(tree);
		Node searchResult = tree.search(arr[6]);
		System.out.println("The 6-th value of arr is " + searchResult.values[searchResult.index]);
		Node minimum = tree.minimum();
		System.out.println("The minimum value of the tree is " + minimum.values[minimum.index]);
		Node successor = tree.successor(searchResult);
		if (successor == null) {
			System.out.println(searchResult.values[searchResult.index] + " has no successor.");
		} else {
			System.out.println("The successor of " + searchResult.values[searchResult.index] + " is " + successor.values[successor.index] + ".");
		}
		System.out.println("After delete " + successor.values[successor.index]);
		tree.delete(successor);
		TreePrinter.print(tree);
	}

	/**
	 * Default constructor.
	 */
	public BTree() {
		root = null;
	}

	/**
	 * Constructor with underlying values.
	 * <p>
	 * This constructor will construct the B-Tree according to the order of the input array.
	 *
	 * @param values The values used to constructing the tree.
	 */
	public BTree(int[] values) {
		this(values, 2);
	}

	/**
	 * Constructor with underlying values and minimum degree specified.
	 *
	 * @param values        The values used to constructing the tree.
	 * @param minimumDegree Minimum number of children in each inner node.
	 */
	public BTree(int[] values, int minimumDegree) {
		this.minimumDegree = minimumDegree;
		root = new Node(new int[2 * minimumDegree - 1], new Node[2 * minimumDegree]);
		root.isLeaf = true;
		for (int value : values) {
			insert(value);
		}
	}

	/**
	 * Find a node with value k.
	 *
	 * @param k The value searched in this tree.
	 * @return The node with value k.
	 */
	@Override
	public Node search(int k) {
		return search(root, k);
	}

	/**
	 * Find a node with value k in the subtree specified by {@code root}.
	 *
	 * @param root The root node of the subtree.
	 * @param k    The value searched in this tree.
	 * @return The node with value k.
	 */
	@Override
	public Node search(Node root, int k) {
		int index = 0;
		while (index < root.numberOfValues && k > root.values[index]) {
			index++;
		}
		if (index < root.numberOfValues && k == root.values[index]) {
			root.index = index;
			return root;
		}
		if (root.isLeaf) {
			return null;
		}
		return search(root.children[index], k);
	}

	/**
	 * Find the node with minimum value in the tree.
	 *
	 * @return The node with minimum value in the tree.
	 */
	@Override
	public Node minimum() {
		return minimum(root);
	}

	/**
	 * Find the node with minimum value in the subtree specified by {@code root}.
	 *
	 * @param root The root node of the subtree.
	 * @return The node with minimum value in the tree.
	 */
	@Override
	public Node minimum(Node root) {
		while (!root.isLeaf) {
			root = root.children[0];
		}
		root.index = 0;
		return root;
	}

	/**
	 * Find the node with maximum value in the tree.
	 *
	 * @return The node with maximum value in the tree.
	 */
	@Override
	public Node maximum() {
		return maximum(root);
	}

	/**
	 * Find the node with maximum value in the subtree specified by {@code root}.
	 *
	 * @param root The root node of the subtree.
	 * @return The node with maximum value in the tree.
	 */
	@Override
	public Node maximum(Node root) {
		while (!root.isLeaf) {
			root = root.children[root.numberOfValues];
		}
		root.index = root.numberOfValues - 1;
		return root;
	}

	/**
	 * Find the real predecessor of the given node.
	 * <p>
	 * The node found must have different value with the given node.
	 *
	 * @param node The node whose predecessor will be found.
	 * @return The predecessor node of the given node.
	 */
	@Override
	public Node predecessor(Node node) {
		int originalValue = node.values[node.index];
		Node predecessor = predecessorNode(node);
		while (predecessor != null && predecessor.values[predecessor.index] == originalValue) {
			predecessor = predecessorNode(predecessor);
		}
		return predecessor;
	}

	/**
	 * Find the predecessor node of the given node.
	 *
	 * @param node The node whose predecessor will be found.
	 * @return The predecessor node of the given node.
	 */
	public Node predecessorNode(Node node) {
		if (!node.isLeaf) {
			return maximum(node.children[node.index]);
		}
		if (node.index > 0) {
			node.index--;
			return node;
		}
		while (node.parent != null && node.indexOfParent == 0) {
			node = node.parent;
		}
		if (node.parent == null) {
			return null;
		}
		node.parent.index = node.indexOfParent - 1;
		return node.parent;
	}

	/**
	 * Find the real successor of the given node.
	 * <p>
	 * The node found must have different value with the given node.
	 *
	 * @param node The node whose successor will be found.
	 * @return The successor node of the given node.
	 */
	@Override
	public Node successor(Node node) {
		int originalValue = node.values[node.index];
		Node successor = successorNode(node);
		while (successor != null && successor.values[successor.index] == originalValue) {
			successor = successorNode(successor);
		}
		return successor;
	}

	/**
	 * Find the successor node of the given node.
	 *
	 * @param node The node whose successor will be found.
	 * @return The successor node of the given node.
	 */
	public Node successorNode(Node node) {
		if (!node.isLeaf) {
			return minimum(node.children[node.index + 1]);
		}
		if (node.index < node.numberOfValues - 1) {
			node.index++;
			return node;
		}
		while (node.parent != null && node.indexOfParent == node.parent.numberOfValues) {
			node = node.parent;
		}
		if (node.parent == null) {
			return null;
		}
		node.parent.index = node.indexOfParent;
		return node.parent;
	}

	/**
	 * Insert a node into the tree.
	 *
	 * @param newNode The node to be inserted.
	 */
	@Override
	public void insert(Node newNode) {
		insert(newNode.value);
	}

	/**
	 * Insert a key value into the tree.
	 *
	 * @param k The key value to be inserted.
	 */
	public void insert(int k) {
		if (root.numberOfValues == 2 * minimumDegree - 1) {
			// Root node is full, we should set a new root node.
			Node newRoot = new Node(new int[2 * minimumDegree - 1], new Node[2 * minimumDegree]);
			newRoot.children[0] = root;
			root.parent = newRoot;
			root = newRoot;
			splitChild(root, 0);
		}
		insertNonFull(root, k);
	}

	/**
	 * Delete a node from the tree.
	 *
	 * @param node The node to be deleted.
	 */
	@Override
	public void delete(Node node) {
		if (root.numberOfValues == 0) {
			// Root node is empty, we should delete it and make its only child as new root.
			root = root.children[0];
			root.parent = null;
		}
		deleteNonEmpty(root, node.values[node.index]);
	}

	/**
	 * Get the minimum degree of this tree.
	 *
	 * @return The minimum degree of this tree.
	 */
	public int getMinimumDegree() {
		return minimumDegree;
	}

	/**
	 * Insert a key value into a non-full node.
	 *
	 * @param node The non-full node where the key value will be inserted.
	 * @param k    The key value to be inserted.
	 */
	private void insertNonFull(Node node, int k) {
		int i = node.numberOfValues - 1;
		if (node.isLeaf) {
			while (i >= 0 && k < node.values[i]) {
				node.values[i + 1] = node.values[i];
				i--;
			}
			node.values[i + 1] = k;
			node.numberOfValues++;
		} else {
			while (i >= 0 && k < node.values[i]) {
				i--;
			}
			i++;
			if (node.children[i].numberOfValues == 2 * minimumDegree - 1) {
				splitChild(node, i);
				if (k > node.values[i]) {
					i++;
				}
			}
			insertNonFull(node.children[i], k);
		}
	}

	/**
	 * Delete a node from a non-empty node.
	 *
	 * @param node        The non-empty node where the delete value resides.
	 * @param deleteValue The value to be deleted.
	 */
	void deleteNonEmpty(Node node, int deleteValue) {
		int index = 0;
		while (index < node.numberOfValues && deleteValue > node.values[index]) {
			index++;
		}
		if (index < node.numberOfValues && deleteValue == node.values[index]) {
			// The value to be deleted is in current node.
			if (node.isLeaf) {
				for (int i = index; i < node.numberOfValues - 1; i++) {
					node.values[i] = node.values[i + 1];
				}
				node.numberOfValues--;
			} else {
				if (node.children[index].numberOfValues >= minimumDegree) {
					node.index = index;
					Node predecessor = predecessorNode(node);
					node.values[index] = predecessor.values[predecessor.index];
					deleteNonEmpty(node.children[index], predecessor.values[predecessor.index]);
				} else if (node.children[index + 1].numberOfValues >= minimumDegree) {
					node.index = index;
					Node successor = successorNode(node);
					node.values[index] = successor.values[successor.index];
					deleteNonEmpty(node.children[index + 1], successor.values[successor.index]);
				} else {
					mergeChild(node, index);
					// Delete recursively.
					deleteNonEmpty(node.children[index], deleteValue);
				}
			}
		} else {
			// We assume the deleteNode is always the search result to ensure when delete value has multiple nodes, the
			// deleteNode always has the highest height. So that we don't need to consider equal values here.
			if (node.children[index].numberOfValues >= minimumDegree) {
				deleteNonEmpty(node.children[index], deleteValue);
			} else {
				if (index > 0 && node.children[index - 1].numberOfValues >= minimumDegree) {
					// Move the most right value in node.children[index - 1] to node.values[index - 1] and move
					// node.values[index - 1] to the most left value in node.children[index].
					for (int i = minimumDegree - 1; i > 0; i--) {
						node.children[index].values[i] = node.children[index].values[i - 1];
					}
					node.children[index].values[0] = node.values[index - 1];
					node.values[index - 1] =
							node.children[index - 1].values[node.children[index - 1].numberOfValues - 1];
					if (!node.children[index].isLeaf) {
						for (int i = minimumDegree; i > 0; i--) {
							node.children[index].children[i] = node.children[index].children[i - 1];
							node.children[index].children[i].indexOfParent = i;
						}
						node.children[index].children[0] =
								node.children[index - 1].children[node.children[index - 1].numberOfValues];
						node.children[index].children[0].parent = node.children[index];
						node.children[index].children[0].indexOfParent = 0;
					}
					node.children[index - 1].numberOfValues--;
					node.children[index].numberOfValues++;
					deleteNonEmpty(node.children[index], deleteValue);
				} else if (index < node.numberOfValues && node.children[index + 1].numberOfValues >= minimumDegree) {
					// Move the most left value in node.children[index + 1] to node.values[index] and move
					// node.values[index] to the most right value in node.children[index].
					node.children[index].values[node.children[index].numberOfValues] = node.values[index];
					node.values[index] = node.children[index + 1].values[0];
					for (int i = 0; i < node.children[index + 1].numberOfValues - 1; i++) {
						node.children[index + 1].values[i] = node.children[index + 1].values[i + 1];
					}
					if (!node.children[index].isLeaf) {
						node.children[index].children[node.children[index].numberOfValues + 1] =
								node.children[index + 1].children[0];
						node.children[index].children[node.children[index].numberOfValues + 1].parent =
								node.children[index];
						node.children[index].children[node.children[index].numberOfValues + 1].indexOfParent =
								node.children[index].numberOfValues + 1;
						for (int i = 0; i < node.children[index + 1].numberOfValues; i++) {
							node.children[index + 1].children[i] = node.children[index + 1].children[i + 1];
							node.children[index + 1].children[i].indexOfParent = i;
						}
					}
					node.children[index].numberOfValues++;
					node.children[index + 1].numberOfValues--;
					deleteNonEmpty(node.children[index], deleteValue);
				} else {
					if (index < node.numberOfValues) {
						// Merge node.children[index] with node.children[index + 1] whenever the latter exists.
						mergeChild(node, index);
						deleteNonEmpty(node.children[index], deleteValue);
					} else {
						// If node.children[index] is the right most one, we merge it with its left brother.
						mergeChild(node, index - 1);
						deleteNonEmpty(node.children[index - 1], deleteValue);
					}
				}
			}
		}
	}

	/**
	 * Split a full child node and adjust current node.
	 *
	 * @param node  The node whose child node will be split.
	 * @param index The index of the child in current node.
	 */
	private void splitChild(Node node, int index) {
		Node newChild = new Node(new int[2 * minimumDegree - 1], new Node[2 * minimumDegree]);
		Node child = node.children[index];
		newChild.parent = node;
		newChild.isLeaf = child.isLeaf;
		newChild.numberOfValues = minimumDegree - 1;
		newChild.indexOfParent = child.indexOfParent + 1;
		System.arraycopy(child.values, minimumDegree, newChild.values, 0, minimumDegree - 1);
		if (!child.isLeaf) {
			System.arraycopy(child.children, minimumDegree, newChild.children, 0, minimumDegree);
			for (int i = 0; i < minimumDegree; i++) {
				newChild.children[i].parent = newChild;
				newChild.children[i].indexOfParent = i;
			}
		}
		child.numberOfValues = minimumDegree - 1;
		for (int i = node.numberOfValues; i >= index + 1; i--) {
			node.children[i + 1] = node.children[i];
			node.children[i + 1].indexOfParent++;
		}
		node.children[index + 1] = newChild;
		for (int i = node.numberOfValues - 1; i >= index; i--) {
			node.values[i + 1] = node.values[i];
		}
		node.values[index] = child.values[minimumDegree - 1];
		node.numberOfValues++;
	}

	/**
	 * Merge two child nodes into one.
	 *
	 * @param node  The node whose child nodes will be merged.
	 * @param index The index of the left child in current node.
	 */
	private void mergeChild(Node node, int index) {
		Node leftChild = node.children[index];
		Node rightChild = node.children[index + 1];
		// Merge node.values[index] and rightChild into leftChild.
		leftChild.values[leftChild.numberOfValues] = node.values[index];
		for (int i = 0; i < minimumDegree - 1; i++) {
			leftChild.values[minimumDegree + i] = rightChild.values[i];
		}
		for (int i = index; i < node.numberOfValues - 1; i++) {
			node.values[i] = node.values[i + 1];
		}
		for (int i = index + 1; i < node.numberOfValues; i++) {
			node.children[i] = node.children[i + 1];
			node.children[i].indexOfParent = i;
		}
		if (!leftChild.isLeaf) {
			for (int i = 0; i < minimumDegree; i++) {
				leftChild.children[minimumDegree + i] = rightChild.children[i];
				leftChild.children[minimumDegree + i].indexOfParent = minimumDegree + i;
				leftChild.children[minimumDegree + i].parent = leftChild;
			}
		}
		leftChild.numberOfValues = 2 * minimumDegree - 1;
		node.numberOfValues--;
	}

	/**
	 * The minimum number of children in B-Tree.
	 */
	private int minimumDegree;
}
