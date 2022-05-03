/**
 * Copyright 2022 jingedawang
 */
package container;

/**
 * Directed queue. Pushing value to the back and popping value from the front are supported.
 */
public interface Queue extends Container {

	/**
	 * Get the value from the back of the queue.
	 *
	 * @return The value at the back.
	 */
	int back();

	/**
	 * Get the value from the front of the queue.
	 *
	 * @return The value at the front.
	 */
	int front();

	/**
	 * Push a value into the queue.
	 *
	 * @param value The value to be pushed into the queue.
	 */
	void push(int value);

	/**
	 * Pop a value from the queue.
	 *
	 * @return The value at the front of the queue.
	 */
	int pop();

}