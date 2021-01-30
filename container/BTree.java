/**
 * Copyright 2021 jingedawang
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
//		tree.delete(tree.root);
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
	 *
	 * The node found must have different value with the given node.
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
	 *
	 * The node found must have different value with the given node.
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
	 * The minimum number of children in B-Tree.
	 */
	private int minimumDegree;
}
