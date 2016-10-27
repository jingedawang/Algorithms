package com.ucas.algorithms.math;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.ucas.algorithms.utils.ArrayPrinter;

public class Fibonacci {
	
	private static Fibonacci instance;
	
	private List<BigDecimal> fibonacciSequence = new ArrayList<BigDecimal>();
	
	private Fibonacci() {
		
	}

	public static void main(String[] args) {
		List<BigDecimal> f10 = Fibonacci.getInstance().getNFibonacci(500);
		ArrayPrinter.print(f10.toArray(new BigDecimal[1]));
	}
	
	public static Fibonacci getInstance() {
		if (instance == null) {
			instance = new Fibonacci();
		}
		return instance;
	}
	
	public List<BigDecimal> getNFibonacci(int n) {
		if (n <= fibonacciSequence.size()) {
			return fibonacciSequence.subList(0, n);
		}
		generateFibonacciToN(n);
		return fibonacciSequence;
	}
	
	/**
	 * 获取斐波那契数列的第i项
	 * @param i 获取的项数
	 * @return 第i项对应的值
	 */
	public BigDecimal getFibonacci(int i) {
		if (i < fibonacciSequence.size()) {
			return fibonacciSequence.get(i);
		}
		generateFibonacciToN(i + 1);
		return fibonacciSequence.get(i);
	}
	
	private void generateFibonacciToN(int n) {
		if (n > fibonacciSequence.size() && fibonacciSequence.size() == 0) {
			fibonacciSequence.add(new BigDecimal(0));
		}
		if (n >= fibonacciSequence.size() && fibonacciSequence.size() == 1) {
			fibonacciSequence.add(new BigDecimal(1));
		}
		while (fibonacciSequence.size() < n) {
			fibonacciSequence.add(fibonacciSequence.get(fibonacciSequence.size() - 1).add(fibonacciSequence.get(fibonacciSequence.size() - 2)));
		}
	}

}
