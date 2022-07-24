/**
 * Copyright 2022 jingedawang
 */
package container;

import utils.ArrayGenerator;
import utils.ArrayPrinter;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * FibonacciHeap is a kind of heap which is fast for some kinds of operations in view of amortized complexity.
 *
 * Specifically, inserting node and decreasing value can be finished in amortized constant-time.
 * It is really helpful in many graph algorithms who need to decrease the weight of the edges frequently.
 */
public class FibonacciHeap implements Heap {

	/**
	 * Demo code.
	 */
	public static void main(String[] args) {
		int[] arr = ArrayGenerator.fixedArray();
		System.out.println("Array used to build fibonacci heap:");
		ArrayPrinter.print(arr);
		FibonacciHeap heap = new FibonacciHeap(arr);

		System.out.println("The top value of the fibonacci heap is:");
		System.out.println(heap.top());

		System.out.println("Pop values from the fibonacci heap:");
		while (heap.size() > 1) {
			System.out.print(heap.pop() + ", ");
		}
		System.out.println(heap.pop());
	}

	/**
	 * Default constructor.
	 */
	public FibonacciHeap() {
	}

	/**
	 * Construct a fibonacci heap with given array.
	 *
	 * @param arr The data used for constructing the fibonacci heap.
	 */
	public FibonacciHeap(int[] arr) {
		for (int value : arr) {
			insert(new Node(value));
		}
	}

	/**
	 * Get the top value of the heap.
	 *
	 * For fibonacci heap, the top value is the minimum value.
	 * @return The top value of the heap.
	 */
	@Override
	public int top() {
		return minimum.value;
	}

	/**
	 * Get and remove the top value of the heap.
	 *
	 * @return The top value of the heap.
	 */
	@Override
	public int pop() {
		if (size <= 0) {
			throw new ArrayIndexOutOfBoundsException("Can not pop a value from an empty heap.");
		}
		for (Node child : minimum.childrenList)
		{
			rootList.add(child);
			child.parent = null;
		}
		rootList.remove(minimum);
		int minimumValue = minimum.value;
		if (rootList.isEmpty()) {
			minimum = null;
		}
		else {
			minimum = rootList.getFirst();
			consolidate();
		}
		size--;
		return minimumValue;
	}

	/**
	 * Insert a node into the heap.
	 *
	 * @param newNode The node to be inserted.
	 */
	@Override
	public void insert(Node newNode) {
		newNode.degree = 0;
		newNode.parent = null;
		newNode.childrenList = new LinkedList<>();
		newNode.marked = false;

		// Directly insert the new node into root list.
		// Postpone the consolidating procedure to pop method.
		if (minimum == null) {
			rootList = new LinkedList<>();
			rootList.add(newNode);
			minimum = newNode;
		}
		else {
			rootList.add(newNode);
			if (newNode.value < minimum.value) {
				minimum = newNode;
			}
		}
		size++;
	}

	/**
	 * Delete a node from the heap.
	 *
	 * @param node The node to be deleted.
	 */
	@Override
	public void delete(Node node) {
		// We firstly decrease its value to the minimum integer value.
		// Then pop method will remove it because it's minimum now.
		decreaseValue(node, Integer.MIN_VALUE);
		pop();
	}

	/**
	 * Check if the heap has no elements.
	 *
	 * @return {@code true} if the heap has no elements, {@code false} otherwise.
	 */
	@Override
	public boolean empty() {
		return size == 0;
	}

	/**
	 * Get the size of the heap.
	 *
	 * @return The size of the heap.
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Decrease the value of the specified node.
	 *
	 * @param node The node whose value will be decreased.
	 * @param newValue The new value for the node. It should be smaller than original value of the node.
	 */
	public void decreaseValue(Node node, int newValue) {
		if (newValue > node.value) {
			throw new IllegalArgumentException("New value should be smaller than current value.");
		}
		node.value = newValue;
		Node parent = node.parent;
		if (parent != null && node.value < parent.value) {
			// The new value violate the minimum heap order.
			// Simply cut the node and move it into the root list.
			cut(node, parent);

			// But, there is one more important thing to obtain the constant-time complexity.
			// That is, once a node loses its second child, it should also be moved into the root list.
			// This is used to keep each heap compact, which is necessary for maintaining an exponential relationship
			// between the largest degree and heap size.
			// And this check should be recursively upwards to root, because cutting current node will have effect on
			// whether to cut its parent.
			cascadingCut(parent);
		}
		if (node.value < minimum.value) {
			minimum = node;
		}
	}

	/**
	 * Consolidate the root list of the fibonacci heap to make sure each root have different degrees.
	 *
	 * This can be achieved by recursively merging roots with the same degree.
	 */
	private void consolidate() {
		// Use a HashMap to track the node of each degree.
		// The initial capacity is set to the log of heap size, because the largest degree is expected to be smaller
		// than log(n).
		HashMap<Integer, Node> degreeMap = new HashMap<>((int)Math.log(size));

		// Since the iterator of LinkedList is fail-fast, we cannot modify it during traversing.
		// Instead, we traverse another copy while modifying the original root list.
		Node[] rootArr = rootList.toArray(new Node[0]);
		for (Node node : rootArr) {
			int degree = node.degree;
			while (degreeMap.containsKey(degree)) {
				Node sameDegreeNode = degreeMap.get(degree);
				if (node.value > sameDegreeNode.value) {
					Node temp = node;
					node = sameDegreeNode;
					sameDegreeNode = temp;
				}
				link(sameDegreeNode, node);
				degreeMap.remove(degree);
				degree++;
			}
			degreeMap.put(degree, node);
		}

		// Reset the root list according to degreeMap.
		minimum = null;
		rootList.clear();
		for (Node node : degreeMap.values()) {
			rootList.add(node);
			if (minimum == null || node.value < minimum.value) {
				minimum = node;
			}
		}
	}

	/**
	 * Link the child node to the given parent node.
	 *
	 * @param child The node to be linked as a child of the parent node.
	 * @param parent The node where the child node to be linked to.
	 */
	private void link(Node child, Node parent) {
		rootList.remove(child);
		parent.childrenList.add(child);
		child.parent = parent;
		parent.degree++;
		child.marked = false;
	}

	/**
	 * Cut the child from the parent node and move it to root list.
	 *
	 * @param child The node to be cut from its parent node.
	 * @param parent The node where the child to be cut from.
	 */
	private void cut(Node child, Node parent) {
		parent.childrenList.remove(child);
		parent.degree--;
		rootList.add(child);
		child.parent = null;
		child.marked = false;
	}

	/**
	 * Cascading cut the nodes.
	 *
	 * This method recursively cuts the node upwards to the root, until reach to an un-marked node.
	 *
	 * @param node The node where the cascading cut started.
	 */
	private void cascadingCut(Node node) {
		Node parent = node.parent;
		if (parent != null) {
			if (!node.marked) {
				// If the node is not marked, it hasn't lost child so far.
				// Mark it to indicate it lost a child now.
				node.marked = true;
			}
			else {
				// If the node is already marked, it means it's losing the second child now.
				// Cut it and continue check if its parent should be cut.
				cut(node, parent);
				cascadingCut(parent);
			}
		}
	}

	// The number of the nodes in this heap.
	private int size;

	// The minimum node of this heap.
	private Node minimum;

	// The root list of this fibonacci heap.
	private LinkedList<Node> rootList;

}