/**
 * Copyright 2020 jingedawang
 */
package matrix;

import enums.MultiplierType;

/**
 * <h3>Matrix factory</h3>
 * <p>
 * This class provides some static methods to construct matrix related objects.
 */
public class MatrixFactory {

	/**
	 * Get a default matrix multiplier.
	 *
	 * @return A default matrix multiplier object.
	 */
	public static MatrixMultiplier getMutiplier() {
		return new PlainMultiplier();
	}

	/**
	 * Get the matrix multiplier specified by the given type.
	 *
	 * @param type The type of the matrix multiplier.
	 * @return A matrix multiplier object.
	 */
	public static MatrixMultiplier getMutiplier(MultiplierType type) {
		switch (type) {
			case PLAIN:
				return new PlainMultiplier();
			case STRASSEN:
				return new StrassenMultiplier();
			default:
				return getMutiplier();
		}
	}

}