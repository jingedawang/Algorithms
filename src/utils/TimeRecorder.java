/**
 * Copyright 2022 jingedawang
 */
package utils;

import java.util.ArrayList;

/**
 * Record time consumption of code pieces and print the elapsed time in a pretty format.
 */
public class TimeRecorder {

	/**
	 * Constructs a time recorder with default title "Time used".
	 */
	public TimeRecorder() {
		this("Time used");
	}

	/**
	 * Constructs a time recorder with specific title.
	 *
	 * @param title The title of this time recorder.
	 */
	public TimeRecorder(String title) {
		this.title = title;
	}

	/**
	 * Start timing.
	 */
	public void start() {
		addTimePoint("start_time");
	}

	/**
	 * Add a time point.
	 * The {@code name} param names the time interval between now and the previous time point.
	 *
	 * @param name The name of the time point.
	 */
	public void addTimePoint(String name) {
		timePoints.add(System.nanoTime());
		names.add(name);
	}

	/**
	 * Stop timing.
	 */
	public void stop() {
		addTimePoint("end_time");
	}

	/**
	 * Get the elapsed time in milliseconds.
	 * @return The elapsed time in milliseconds.
	 */
	public int getElapsedTime() {
		return Math.toIntExact((timePoints.get(timePoints.size() - 1) - timePoints.get(0)) / 1000000);
	}

	/**
	 * Show elapsed time.
	 */
	public void print() {
		ArrayList<Integer> timeIntervals = new ArrayList<>();
		for (int i = 1; i < names.size(); i++) {
			timeIntervals.add(Math.toIntExact((timePoints.get(i) - timePoints.get(i - 1)) / 1000000));
		}
		print(title, names, timeIntervals);
	}

	private void print(String title, ArrayList<String> names, ArrayList<Integer> timeIntervals) {
		assert names.size() == timeIntervals.size() + 1;
		if (timeIntervals.size() == 1) {
			System.out.println(title + ": " + timeIntervals.get(0) + "ms used.");
		}
		else {
			// TODO: Pretty print used time.
		}
	}

	private final String title;
	private long startTime;
	private long endTime;
	private final ArrayList<String> names = new ArrayList<>();
	private final ArrayList<Long> timePoints = new ArrayList<>();

}