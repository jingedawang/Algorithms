/**
 * Copyright 2022 jingedawang
 */
package select;

/**
 * Interface for all select classes.
 * <p>
 * Select algorithm is used for selecting the i-th smallest number in an array.
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