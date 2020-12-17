/**
 * Copyright 2020 jingedawang
 */
package dp;

/**
 * <h3>Cut rod problem solved by dynamic programming</h3>
 */
public class CutRod {

	/**
	 * Test code.
	 */
	public static void main(String[] args) {
		int n = 7;
		int[] prices = {0, 1, 5, 8, 9, 10, 17, 17, 20, 24, 30};

		System.out.println("The rod length is " + n + ".");
		CutRod cutRod = new CutRod();
		int price = cutRod.cutRod(n, prices);
		System.out.println("The best price of the rod is " + price);
		cutRod.printSolution(n, prices);
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
	public void printSolution(int n, int[] prices) {
		int[] lengths = new int[n + 1];
		cutRod(n, prices, lengths);
		System.out.println("And it is cut to following parts:");
		while (n > 0) {
			System.out.println(lengths[n]);
			n = n - lengths[n];
		}
	}

	/**
	 * The dynamic programming implementation of cut rod problem.
	 *
	 * @param n       The length of the rod.
	 * @param prices  The prices of each length of the rod.
	 * @param lengths Output param. The length of the first cut part in every circumstances.
	 * @return The best price accumulated by cut rods.
	 */
	private int cutRod(int n, int[] prices, int[] lengths) {
		int[] bestPrices = new int[n + 1];
		bestPrices[1] = prices[1];
		lengths[1] = 1;
		for (int i = 2; i <= n; i++) {
			int bestPrice = 0;
			for (int j = 0; j < i; j++) {
				if (prices[i - j] + bestPrices[j] > bestPrice) {
					bestPrice = prices[i - j] + bestPrices[j];
					lengths[i] = i - j;
				}
			}
			bestPrices[i] = bestPrice;
		}
		return bestPrices[n];
	}

}