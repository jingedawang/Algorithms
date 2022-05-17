/**
 * Copyright 2022 jingedawang
 */

import enums.MultiplierType;
import matrix.MatrixFactory;
import matrix.PlainMultiplier;
import matrix.StrassenMultiplier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test class for {@link MatrixFactory}.
 */
public class MatrixFactoryTest {

	@Test
	void getMultiplier() {
		Assertions.assertTrue(MatrixFactory.getMutiplier() instanceof PlainMultiplier);
		Assertions.assertTrue(MatrixFactory.getMutiplier(MultiplierType.PLAIN) instanceof PlainMultiplier);
		Assertions.assertTrue(MatrixFactory.getMutiplier(MultiplierType.STRASSEN) instanceof StrassenMultiplier);
	}

}