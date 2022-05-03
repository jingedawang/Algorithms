package container;

/**
 * Base class of all containers.
 */
public interface Container {

	/**
	 * Check if the container has no elements.
	 *
	 * @return {@code true} if the container has no elements, {@code false} otherwise.
	 */
	boolean empty();

	/**
	 * Get the size of the container.
	 *
	 * @return The size of the container.
	 */
	int size();

}