/**
 * Copyright 2022 jingedawang
 */
package dp;

import utils.ArrayPrinter;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Cut rod problem.
 *
 * Different length of rods have different prices. The prices are defined by a table, which are not linearly related.
 * Given a rod, the problem is how to cut it to make the cut rods accumulated to the highest price.
 */
public class CutRod {

	/**
	 * Demo code.
	 */
	public static void main(String[] args) {
		int n = 7;
		int[] prices = {0, 1, 5, 8, 9, 10, 17, 17, 20, 24, 30};

		System.out.println("The rod length is " + n + ".");
		System.out.println("The prices of each length is:");
		ArrayPrinter.print(prices);
		CutRod cutRod = new CutRod();
		int price = cutRod.cutRod(n, prices);
//		int price = cutRod.memoizedCutRod(n, prices);
		System.out.println();
		System.out.println("The best price of the rod is " + price + ".");
		System.out.println("And it is cut to following lengths:");
		int[] cutLengths = cutRod.getCutLengths(n, prices);
		ArrayPrinter.print(cutLengths);
	}

	/**
	 * Cut a rod in order to get the best price.
	 *
	 * @param n      The length of the rod.
	 * @param prices The prices of each length of the rod.
	 * @return The best price accumulated by cut rods.
	 */
	public int cutRod(int n, int[] prices) {
		return cutRod(n, prices, new int[n + 1]);
	}

	/**
	 * Print the lengths of each part in the best cut solution.
	 *
	 * @param n      The length of the rod.
	 * @param prices The prices of each length of the rod.
	 */
	public int[] getCutLengths(int n, int[] prices) {
		int[] lengths = new int[n + 1];
		cutRod(n, prices, lengths);
		ArrayList<Integer> cutLengths = new ArrayList<>();
		while (n > 0) {
			cutLengths.add(lengths[n]);
			n = n - lengths[n];
		}
		return cutLengths.stream().mapToInt(i -> i).toArray();
	}

	/**
	 * Cut a rod in order to get the best price.
	 *
	 * This method uses a memo to reuse the solutions of the sub-problems. It's not the recommended implementation, just
	 * for demonstration. For the recommended implementation, please refer to {@code cutRod} method.
	 *
	 * @param n      The length of the rod.
	 * @param prices The prices of each length of the rod.
	 * @return The best price accumulated by cut rods.
	 */
	public int memoizedCutRod(int n, int[] prices) {
		int[] bestPrices = new int[n + 1];
		Arrays.fill(bestPrices, Integer.MIN_VALUE);
		return memoizedCutRodAux(n, prices, bestPrices);
	}

	/**
	 * The dynamic programming implementation of cut rod problem.
	 *
	 * @param n       The length of the rod.
	 * @param prices  The prices of each length of the rod.
	 * @param lengths Output param. The length of the first cut part in each circumstance.
	 * @return The best price accumulated by cut rods.
	 */
	private int cutRod(int n, int[] prices, int[] lengths) {
		int[] bestPrices = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			int bestPrice = Integer.MIN_VALUE;
			for (int j = 1; j <= i; j++) {
				if (prices[j] + bestPrices[i - j] > bestPrice) {
					bestPrice = prices[j] + bestPrices[i - j];
					lengths[i] = j;
				}
			}
			bestPrices[i] = bestPrice;
		}
		return bestPrices[n];
	}

	/**
	 * The recursive implementation of memoized cut rod.
	 *
	 * @param n          The length of the rod.
	 * @param prices     The prices of each length of the rod.
	 * @param bestPrices The memo remembering the best prices of each length.
	 * @return The best price accumulated by cut rods.
	 */
	private int memoizedCutRodAux(int n, int[] prices, int[] bestPrices) {
		if (bestPrices[n] >= 0) {
			return bestPrices[n];
		}
		if (n == 0) {
			return 0;
		}
		int bestPrice = Integer.MIN_VALUE;
		for (int i = 1; i <= n; i++) {
			bestPrice = Math.max(bestPrice, prices[i] + memoizedCutRodAux(n - i, prices, bestPrices));
		}
		bestPrices[n] = bestPrice;
		return bestPrice;
	}

}