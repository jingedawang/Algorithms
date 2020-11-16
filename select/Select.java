/**
 * Copyright 2020 jingedawang
 */
package select;

/**
 * <h3>Interface for all select classes</h3>
 * <p>
 * A select algorithm can select the i-th smallest number in an array.
 */
public interface Select {

	/**
	 * Select interface for integer array.
	 *
	 * @param arr Integer array from which select.
	 * @param i   The order of the element to be selected.
	 * @return The selected element.
	 */
	int select(int[] arr, int i);

}