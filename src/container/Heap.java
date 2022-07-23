/**
 * Copyright 2022 jingedawang
 */
package container;

/**
 * Interface for heap.
 *
 * A heap is a data structure which could easily access and extract values from its top.
 */
public interface Heap extends Container {

	/**
	 * Get the top value of the heap.
	 *
	 * @return The top value of the heap.
	 */
	int top();

	/**
	 * Get and remove the top value of the heap.
	 *
	 * @return The top value of the heap.
	 */
	int pop();

	/**
	 * Insert a node into the heap.
	 *
	 * @param newNode The node to be inserted.
	 */
	void insert(Node newNode);

	/**
	 * Delete a node from the heap.
	 *
	 * @param node The node to be deleted.
	 */
	void delete(Node node);

}