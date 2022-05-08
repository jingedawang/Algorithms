/**
 * Copyright 2022 jingedawang
 */
package dp;

import utils.ArrayPrinter;

import java.util.Arrays;
import java.util.TreeSet;

/**
 * 0-1 knapsack problem.
 *
 * There are several items, each has a value and a weight. Given a knapsack with a capacity limit, find which items
 * could be put into the knapsack and make the highest total value.
 */
public class ZeroOneKnapsack {

	/**
	 * Demo code.
	 */
	public static void main(String[] args) {
		int[] values = {60, 100, 120};
		int[] weights = {10, 20, 30};
		int weightLimit = 50;
		System.out.println("We have items with following values and weights.");
		System.out.print("Values: ");
		ArrayPrinter.print(values);
		System.out.print("Weights: ");
		ArrayPrinter.print(weights);
		System.out.println("The capacity of the knapsack is " + weightLimit + ".");

		ZeroOneKnapsack zeroOneKnapsack = new ZeroOneKnapsack();
		int[] selectedIndices = zeroOneKnapsack.select(values, weights, weightLimit);
		System.out.println();
		System.out.println("Selected items with the following indices:");
		ArrayPrinter.print(selectedIndices);
		System.out.println("The value of the selected items is "
				+ Arrays.stream(selectedIndices).map(i -> values[i]).sum() + ".");
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