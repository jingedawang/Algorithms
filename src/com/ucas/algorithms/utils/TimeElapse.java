package com.ucas.algorithms.utils;

public class TimeElapse {
	
	private long startTime;
	private long endTime;
	
	/**
	 * 开始时间测量。
	 */
	public void start() {
		startTime = System.nanoTime();
	}
	
	/**
	 * 结束时间测量。
	 */
	public void stop() {
		endTime = System.nanoTime();
	}
	
	/**
	 * 显示消耗的时间。
	 */
	public void showElapsedTime() {
		System.out.println((endTime - startTime) / 1000000 + "ms used.");
	}

}
