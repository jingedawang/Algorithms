/**
 * Copyright 2022 jingedawang
 */
package container;

/**
 * Heap is basically a binary tree, whose each sub-tree keeps the max or min value in the root node.
 *
 * The underlying data is stored in an array, which is good for quick access.
 * After the heap is built, the values are stored layer by layer in the array. For example, an array
 * [4, 8, 1, 2, 0, 6, 5, 1, 9, 3]
 * represents a heap like
 * TODO
 */
public class Heap extends AbstractTree implements BinaryTree {

	/**
	 * Construct an empty max heap.
	 */
	public Heap() {
		this(new int[0], false);
	}

	/**
	 * Construct a heap with given array.
	 *
	 * @param arr The data used for constructing the heap.
	 * @param isMinHeap A flag indicating whether to create a min heap or a max heap.
	 */
	public Heap(int[] arr, boolean isMinHeap) {
		this.isMinHeap = isMinHeap;
		data = arr.clone();
		capacity = data.length;
		size = data.length;
		for (int i = size / 2 - 1; i >= 0; i--) {
			if (isMinHeap) {
				minHeapify(i);
			} else {
				maxHeapify(i);
			}
		}
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
	 * @return The element count of the heap.
	 */
	public int size() {
		return size;
	}

	/**
	 * Get the top value of the heap.
	 *
	 * If this is a max heap, the return value is the maximum.
	 * If this is a min heap, the return value is the minimum.
	 *
	 * @return The top value of the heap.
	 */
	public int top() {
		return data[0];
	}

	/**
	 * Insert a node into the heap.
	 *
	 * The node will be inserted to a proper location in the heap.
	 * @param newNode The node to be inserted.
	 */
	@Override
	public void insert(Node newNode) {
		if (size >= capacity) {
			int[] newArr;
			if (capacity > 0) {
				newArr = new int[capacity * 2];
				System.arraycopy(data, 0, newArr, 0, size);
			} else {
				newArr = new int[8];
			}
			data = newArr;
			capacity = newArr.length;
		}
		size++;
		if (isMinHeap) {
			data[size - 1] = Integer.MAX_VALUE;
			decreaseValue(size - 1, newNode.value);
		}
		else {
			data[size - 1] = Integer.MIN_VALUE;
			increaseValue(size - 1, newNode.value);
		}
	}

	/**
	 * Get and remove the top value of the heap.
	 *
	 * If this is a max heap, the return value is the maximum.
	 * If this is a min heap, the return value is the minimum.
	 * The top node will be removed and the heap will be maintained.
	 *
	 * @return The top value of the priority queue.
	 */
	public int pop() {
		if (size <= 0) {
			throw new ArrayIndexOutOfBoundsException("Can not pop a value from an empty heap.");
		}
		int top = data[0];
		data[0] = data[size - 1];
		size--;
		if (isMinHeap) {
			minHeapify(0);
		}
		else {
			maxHeapify(0);
		}
		return top;
	}

	/**
	 * Adjust the maximum sub-heap to maintain its properties.
	 *
	 * @param index The root index of the sub-heap.
	 */
	private void maxHeapify(int index) {
		int l = left(index);
		int r = right(index);
		int largest;
		if (l < size && data[l] > data[index]) {
			largest = l;
		} else {
			largest = index;
		}
		if (r < size && data[r] > data[largest]) {
			largest = r;
		}
		if (largest != index) {
			int temp = data[index];
			data[index] = data[largest];
			data[largest] = temp;
			maxHeapify(largest);
		}
	}

	/**
	 * Adjust the minimum sub-heap to maintain its properties.
	 *
	 * @param index The root index of the sub-heap.
	 */
	private void minHeapify(int index) {
		int l = left(index);
		int r = right(index);
		int smallest;
		if (l < size && data[l] < data[index]) {
			smallest = l;
		} else {
			smallest = index;
		}
		if (r < size && data[r] < data[smallest]) {
			smallest = r;
		}
		if (smallest != index) {
			int temp = data[index];
			data[index] = data[smallest];
			data[smallest] = temp;
			minHeapify(smallest);
		}
	}

	/**
	 * Increase the value of the specified item and adjust the heap to keep its properties.
	 * <p>
	 * The new value must be greater than the original value, or this method would raise an error.
	 * This method should be called from a max heap.
	 *
	 * @param index The index of the specified item.
	 * @param newValue The new value of the specified item.
	 */
	private void increaseValue(int index, int newValue) {
		if (newValue < data[index]) {
			throw new IllegalArgumentException("Only increasing value is allowed.");
		}
		data[index] = newValue;
		while (index > 0 && data[index] > data[parent(index)]) {
			int temp = data[index];
			data[index] = data[parent(index)];
			data[parent(index)] = temp;
			index = parent(index);
		}
	}

	/**
	 * Decrease the value of the specified item and adjust the heap to keep its properties.
	 * <p>
	 * The new value must be smaller than the original value, or this method would raise an error.
	 * This method should be called from a min heap.
	 *
	 * @param index The index of the specified item.
	 * @param newValue The new value of the specified item.
	 */
	private void decreaseValue(int index, int newValue) {
		if (newValue > data[index]) {
			throw new IllegalArgumentException("Only decreasing value is allowed.");
		}
		data[index] = newValue;
		while (index > 0 && data[index] < data[parent(index)]) {
			int temp = data[index];
			data[index] = data[parent(index)];
			data[parent(index)] = temp;
			index = parent(index);
		}
	}

	/**
	 * Get the index of the parent node.
	 *
	 * @param i The index of the given node.
	 * @return The index of the parent node.
	 */
	private int parent(int i) {
		return (i - 1) / 2;
	}

	/**
	 * Get the index of the left child node.
	 *
	 * @param i The index of the given node.
	 * @return The index of the left child node.
	 */
	private int left(int i) {
		return 2 * i + 1;
	}

	/**
	 * Get the index of the right child node.
	 *
	 * @param i The index of the given node.
	 * @return The index of the right child node.
	 */
	private int right(int i) {
		return 2 * (i + 1);
	}

	// Flag to indicate whether this heap is a min heap or a max heap.
	private final boolean isMinHeap;

	// Underlying array data.
	private int[] data;

	// The number of elements in this heap.
	private int size;

	// The capacity of this heap.
	public int capacity;

}