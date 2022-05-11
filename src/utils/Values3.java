/**
 * Copyright 2022 jingedawang
 */
package utils;

/**
 * A value entity that represents three values with different types.
 * <p>
 * This class can be used as the return type of the method when three return values are needed.
 */
public class Values3<T, U, V> {

	public T value1;
	public U value2;
	public V value3;

	/**
	 * Constructor.
	 *
	 * @param value1 The first value of the entity.
	 * @param value2 The second value of the entity.
	 * @param value3 The third value of the entity.
	 */
	public Values3(T value1, U value2, V value3) {
		this.value1 = value1;
		this.value2 = value2;
		this.value3 = value3;
	}

}