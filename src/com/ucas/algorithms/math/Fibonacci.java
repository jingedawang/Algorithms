package com.ucas.algorithms.math;

import java.util.ArrayList;
import java.util.List;

public class Fibonacci {
	
	private static Fibonacci instance;
	
	private List<Integer> fibonacciSequence = new ArrayList<Integer>();
	
	private Fibonacci() {
		
	}

	public static void main(String[] args) {
		
		int f10 = Fibonacci.getInstance().getFibonacci(10);
		System.out.println(f10);
		
	}
	
	public static Fibonacci getInstance() {
		if (instance == null) {
			instance = new Fibonacci();
		}
		return instance;
	}
	
	public int[] getNFibonacci(int n) {
		if (n <= fibonacciSequence.size()) {
			return fibonacciSequence.subList(0, n).stream().mapToInt(Integer).toArray();
		}
	}
	
	/**
	 * 获取斐波那契数列的第i项
	 * @param i 获取的项数
	 * @return 第i项对应的值
	 */
	public int getFibonacci(int i) {
		if (i < fibonacciSequence.size()) {
			return fibonacciSequence.get(i);
		}
		generateFibonacciToN(i + 1);
		return fibonacciSequence.get(i);
	}
	
	private void generateFibonacciToN(int n) {
		if (n > fibonacciSequence.size() && fibonacciSequence.size() == 0) {
			fibonacciSequence.add(0);
		}
		if (n >= fibonacciSequence.size() && fibonacciSequence.size() == 1) {
			fibonacciSequence.add(1);
		}
		while (fibonacciSequence.size() < n) {
			fibonacciSequence.add(fibonacciSequence.get(fibonacciSequence.size() - 1) + fibonacciSequence.get(fibonacciSequence.size() - 2));
		}
	}

}
