/**
 * Copyright 2022 jingedawang
 */
package utils;

import java.util.ArrayList;

/**
 * Record time consumption of code pieces and print the elapsed time in a pretty format.
 *
 * Two usages are provided, please refer to following examples.
 * <p/>
 * Usage 1: Simple record mode
 * <pre>
 * TimeRecorder timeRecorder = new TimeRecorder();
 * timeRecorder.start();
 * // Do your job here...
 * timeRecorder.stop();
 * timeRecorder.print();
 * </pre>
 * The output in the console should like<p>
 * {@code Time used: 13ms used.}
 * <p/>
 * Usage 2: Multi-stage record mode
 * <pre>
 * TimeRecorder timeRecorder = new TimeRecorder("My workflow");
 * timeRecorder.start();
 * // Do the initialization here...
 * timeRecorder.addTimePoint("Initialize");
 * // Execute the job here...
 * timeRecorder.addTimePoint("Execute");
 * // Release the resources here...
 * timeRecorder.addTimePoint("Release");
 * timeRecorder.print();
 * </pre>
 * The output in the console should like<p>
 * <pre>
 * -----------------------My workflow---------------------
 * --    Initialize      Execute      Release        Total
 * --          15ms         31ms          6ms         52ms
 * -------------------------------------------------------
 * </pre>
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
		// Add end time point only if user didn't add any extra time points.
		if (names.size() == 1) {
			addTimePoint("end_time");
		}
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
		System.out.println(summary(title, names));
	}

	/**
	 * Get the summary text of the time consumption.
	 * @return A summary string.
	 */
	public String summary() {
		return summary(title, names);
	}

	private String summary(String title, ArrayList<String> names) {
		// Compute time intervals for the time points.
		ArrayList<Integer> timeIntervals = new ArrayList<>();
		for (int i = 1; i < names.size(); i++) {
			timeIntervals.add(Math.toIntExact((timePoints.get(i) - timePoints.get(i - 1)) / 1000000));
		}

		// Generate the summary string.
		assert names.size() == timeIntervals.size() + 1;
		if (timeIntervals.size() == 1) {
			return title + ": " + timeIntervals.get(0) + "ms used.";
		}
		else {
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("---");
			for (int i = 0; i < (names.size() * 13 - title.length()) / 2; i++) {
				stringBuilder.append('-');
			}
			stringBuilder.append(title);
			for (int i = 0; i < (names.size() * 13 - title.length() + 1) / 2; i++) {
				stringBuilder.append('-');
			}
			stringBuilder.append("\n-- ");
			for (int i = 1; i < names.size(); i++) {
				stringBuilder.append(String.format("%13s", names.get(i)));
			}
			stringBuilder.append(String.format("%13s", "Total"));
			stringBuilder.append("\n-- ");
			for (int timeInterval : timeIntervals) {
				stringBuilder.append(String.format("%11dms", timeInterval));
			}
			stringBuilder.append(String.format("%11dms", timeIntervals.stream().reduce(0, Integer::sum)));
			stringBuilder.append("\n---");
			for (int i = 0; i < names.size() * 13; i++) {
				stringBuilder.append('-');
			}

			return stringBuilder.toString();
		}
	}

	private final String title;
	private final ArrayList<String> names = new ArrayList<>();
	private final ArrayList<Long> timePoints = new ArrayList<>();

}