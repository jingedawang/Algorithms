package com.ucas.algorithms.sort;

public class SortByPriority {

	public void sortByPriority(int[] arr, int[] priority) {
		if (arr.length != priority.length) {
			throw new IllegalArgumentException("Two arrays must have same length.");
		}
		new OnSort().onSort(priority);
		for (int i=0; i<arr.length; i++) {
			
		}
	}
	
}
