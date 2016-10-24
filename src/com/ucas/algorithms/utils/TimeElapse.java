package com.ucas.algorithms.utils;

public class TimeElapse {
	
	private long startTime;
	private long endTime;
	
	public void start() {
		startTime = System.nanoTime();
	}
	
	public void stop() {
		endTime = System.nanoTime();
	}
	
	public void showElapsedTime() {
		System.out.println((endTime - startTime) / 1000000 + "ms used.");
	}

}
