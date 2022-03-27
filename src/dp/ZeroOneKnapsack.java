/**
 * Copyright 2020 jingedawang
 */
package dp;

import java.util.Arrays;
import java.util.TreeSet;

/**
 * <h3>0-1 knapsack problem</h3>
 */
public class ZeroOneKnapsack {

	/**
	 * Test code.
	 */
	public static void main(String[] args) {
		int[] values = {60, 100, 120};
		int[] weights = {10, 20, 30};
		int weightLimit = 50;
		ZeroOneKnapsack zeroOneKnapsack = new ZeroOneKnapsack();
		int[] selectedIndices = zeroOneKnapsack.select(values, weights, weightLimit);
		System.out.println("Selected items include the following indices:");
		int maxValue = 0;
		for (int index : selectedIndices) {
			System.out.println(index);
			maxValue += values[index];
		}
		System.out.println("The value of the selected items is " + maxValue + ".");
	}

	/**
	 * Select the items that achieve the most value and put them into the knapsack.
	 *
	 * @param values      The values of all the items.
	 * @param weights     The weights of all the items.
	 * @param weightLimit The weight limit of the knapsack.
	 * @return An array including the indices of the selected items.
	 */
	public int[] select(int[] values, int[] weights, int weightLimit) {
		int[] maxValues = new int[weightLimit + 1];
		TreeSet<Integer>[] selectedSets = new TreeSet[weightLimit + 1];
		Arrays.fill(selectedSets, new TreeSet<Integer>());
		for (int i = 0; i < values.length; i++) {
			for (int w = weightLimit; w > 0; w--) {
				int value1 = maxValues[w];
				int value2 = w >= weights[i] ? maxValues[w - weights[i]] + values[i] : 0;
				if (value1 < value2) {
					TreeSet<Integer> newSet = new TreeSet<>(selectedSets[w - weights[i]]);
					newSet.add(i);
					selectedSets[w] = newSet;
					maxValues[w] = value2;
				}
			}
		}
		return selectedSets[weightLimit].stream().mapToInt(Integer::valueOf).toArray();
	}

}