/**
 * Copyright 2022 jingedawang
 */

import org.junit.jupiter.api.Test;
import utils.TimeRecorder;

/**
 * Test class for {@link TimeRecorder}.
 */
public class TimeRecorderTest {

	@Test
	void print() throws InterruptedException {
		TimeRecorder timeRecorderSimple = new TimeRecorder();
		timeRecorderSimple.start();
		Thread.sleep(10);
		timeRecorderSimple.stop();
		timeRecorderSimple.print();
		timeRecorderSimple.summary();

		TimeRecorder timeRecorderComplex = new TimeRecorder("My workflow");
		timeRecorderComplex.start();
		Thread.sleep(10);
		timeRecorderComplex.addTimePoint("Initialize");
		Thread.sleep(30);
		timeRecorderComplex.addTimePoint("Execute");
		Thread.sleep(5);
		timeRecorderComplex.addTimePoint("Release");
		timeRecorderComplex.stop();
		timeRecorderComplex.print();
		timeRecorderComplex.summary();
	}

}