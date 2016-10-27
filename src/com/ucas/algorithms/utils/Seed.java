package com.ucas.algorithms.utils;

import java.util.Random;

public class Seed {

	private static Random random = new Random(System.currentTimeMillis());
	
	public static long next() {
		return random.nextLong();
	}
	
}
