/**
 * Copyright 2020 jingedawang
 */
package utils;

/**
 * <h3>A simple time elapse</h3>
 */
public class TimeRecorder {

	/**
	 * Start timing.
	 */
	public void start() {
		startTime = System.nanoTime();
	}

	/**
	 * Stop timing.
	 */
	public void stop() {
		endTime = System.nanoTime();
	}

	/**
	 * Show time elapsed.
	 */
	public void showElapsedTime() {
		System.out.println((endTime - startTime) / 1000000 + "ms used.");
	}

	private long startTime;
	private long endTime;

}