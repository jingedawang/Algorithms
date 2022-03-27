/**
 * Copyright 2020 jingedawang
 */
package math;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import utils.ArrayPrinter;

/**
 * <h3>Fibonacci sequence</h3>
 *
 * <p>To reuse the generated fibonacci sequence, this class is designed as singleton.</p>
 */
public class Fibonacci {

	/**
	 * Test code.
	 *
	 * <p>Generate the first n fibonacci numbers.</p>
	 */
	public static void main(String[] args) {
		List<BigDecimal> f10 = Fibonacci.getInstance().getNFibonacci(10);
		ArrayPrinter.print(f10.toArray(new BigDecimal[1]));
	}

	/**
	 * Get the singleton instance of this class.
	 *
	 * @return The singleton instance.
	 */
	public static Fibonacci getInstance() {
		if (instance == null) {
			instance = new Fibonacci();
		}
		return instance;
	}

	/**
	 * Get the first {@code n} fibonacci numbers.
	 *
	 * @param n The length of the sequence wanted.
	 * @return A list of the first {@code n} fibonacci numbers.
	 */
	public List<BigDecimal> getNFibonacci(int n) {
		if (n <= fibonacciSequence.size()) {
			return fibonacciSequence.subList(0, n);
		}
		generateFibonacciToN(n);
		return fibonacciSequence;
	}

	/**
	 * Get the {@code i}-th item of the fibonacci sequence.
	 *
	 * @param i The index of the item to be fetched.
	 * @return The {@code i}-th item of the fibonacci sequence.
	 */
	public BigDecimal getFibonacci(int i) {
		if (i < fibonacciSequence.size()) {
			return fibonacciSequence.get(i);
		}
		generateFibonacciToN(i + 1);
		return fibonacciSequence.get(i);
	}

	// Singleton instance.
	private static Fibonacci instance;

	// Cache list for generated fibonacci sequence.
	private List<BigDecimal> fibonacciSequence = new ArrayList<BigDecimal>();

	// Disabled constructor.
	private Fibonacci() {
	}

	// Generate all the fibonacci numbers below n.
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