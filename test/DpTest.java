/**
 * Copyright 2022 jingedawang
 */
import dp.CutRod;
import dp.LongestCommonSubsequence;
import dp.ZeroOneKnapsack;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test class for dynamic programming algorithms.
 */
public class DpTest {

	@Test
	void main() {
		CutRod.main(new String[]{});
		LongestCommonSubsequence.main(new String[]{});
		ZeroOneKnapsack.main(new String[]{});
	}

	@Test
	void cutRod() {
		int n = 7;
		int[] prices = {0, 1, 5, 8, 9, 10, 17, 17, 20, 24, 30};

		CutRod cutRod = new CutRod();
		int bestPrice = cutRod.cutRod(n, prices);
		int bestPriceByMemo = cutRod.memoizedCutRod(n, prices);
		int[] cutLengths = cutRod.getCutLengths(n, prices);
		Assertions.assertEquals(18, bestPrice);
		Assertions.assertEquals(18, bestPriceByMemo);
		Assertions.assertArrayEquals(new int[]{1, 6}, cutLengths);
	}

	@Test
	void longestCommonSubsequence() {
		int[] sequence1 = {8, 5, 9, 9, 18, 12, 2, 0, 18, 9, 19, 1, 15, 8, 17};
		int[] sequence2 = {19, 4, 13, 0, 19, 0, 8, 4, 15, 8, 0, 12, 6, 13, 8, 5, 2, 12, 9, 15};

		LongestCommonSubsequence longestCommonSubsequence = new LongestCommonSubsequence();
		int[] longestCommonSequence = longestCommonSubsequence.longestCommonSequence(sequence1, sequence2);
		Assertions.assertArrayEquals(new int[]{8, 5, 12, 9, 15}, longestCommonSequence);
	}

	@Test
	void zeroOneKnapsack() {
		int[] values = {60, 100, 120};
		int[] weights = {10, 20, 30};
		int weightLimit = 50;

		ZeroOneKnapsack zeroOneKnapsack = new ZeroOneKnapsack();
		int[] selectedIndices = zeroOneKnapsack.select(values, weights, weightLimit);
		Assertions.assertArrayEquals(new int[]{1, 2}, selectedIndices);
	}

}