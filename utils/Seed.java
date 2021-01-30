/**
 * Copyright 2021 jingedawang
 */
package utils;

import java.util.Random;

/**
 * <h3>Seed generator</h3>
 * <p>
 * Generate random seed for other random generators.
 */
public class Seed {

    /**
     * Get next randomly generated seed.
     *
     * @return A long number that can be used as seed.
     */
    public static long next() {
        return random.nextLong();
    }

    private static Random random = new Random(System.currentTimeMillis());
}