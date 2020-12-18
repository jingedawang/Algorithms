/**
 * Copyright 2020 jingedawang
 */
package dp;

import utils.ArrayGenerator;
import utils.ArrayPrinter;

/**
 * <h3>Longest common subsequence problem</h3>
 */
public class LongestCommonSubsequence {

	/**
	 * Test code.
	 */
	public static void main(String[] args) {
		int[] sequence1 = ArrayGenerator.randomArray(20, 20);
		int[] sequence2 = ArrayGenerator.randomArray(20, 20);

		System.out.println("The two input sequences are:");
		ArrayPrinter.print(sequence1);
		ArrayPrinter.print(sequence2);

		LongestCommonSubsequence lcs = new LongestCommonSubsequence();
		int[] longestCommonSequence = lcs.longestCommonSequence(sequence1, sequence2);
		System.out.println("The longest common sequence is:");
		ArrayPrinter.print(longestCommonSequence);
	}

	/**
	 * Get longest common subsequence from two sequences.
	 * <p>
	 * Dynamic programming algorithm is used in this method.
	 *
	 * @param sequence1 The first input sequence.
	 * @param sequence2 The second input sequence.
	 * @return The longest common subsequence.
	 */
	public int[] longestCommonSequence(int[] sequence1, int[] sequence2) {
		int[][] lengths = new int[sequence1.length + 1][sequence2.length + 1];
		for (int i = 1; i <= sequence1.length; i++) {
			for (int j = 1; j <= sequence2.length; j++) {
				if (sequence1[i - 1] == sequence2[j - 1]) {
					lengths[i][j] = lengths[i - 1][j - 1] + 1;
				} else if (lengths[i - 1][j] >= lengths[i][j - 1]) {
					lengths[i][j] = lengths[i - 1][j];
				} else {
					lengths[i][j] = lengths[i][j - 1];
				}
			}
		}
		int i = sequence1.length;
		int j = sequence2.length;
		int[] longestCommonSequence = new int[lengths[i][j]];
		int index = longestCommonSequence.length - 1;
		while (i > 0 && j > 0) {
			if (sequence1[i - 1] == sequence2[j - 1]) {
				longestCommonSequence[index--] = sequence1[i - 1];
				i--;
				j--;
			} else if (lengths[i][j] == lengths[i - 1][j]) {
				i--;
			} else {
				j--;
			}
		}
		return longestCommonSequence;
	}

}