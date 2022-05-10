/**
 * Copyright 2022 jingedawang
 */

import container.PriorityQueue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test class for {@link PriorityQueue}.
 */
public class PriorityQueueTest {

	@Test
	void main() {
		PriorityQueue.main(new String[]{});
	}

	@Test
	void priority() {
		PriorityQueue priorityQueue = new PriorityQueue(new int[] {3, 6, 1});
		priorityQueue.push(5);
		priorityQueue.push(2);
		priorityQueue.push(4);
		Assertions.assertEquals(6, priorityQueue.pop());
		Assertions.assertEquals(5, priorityQueue.pop());
		Assertions.assertEquals(4, priorityQueue.pop());
		priorityQueue.push(10);
		Assertions.assertEquals(10, priorityQueue.front());
		Assertions.assertEquals(10, priorityQueue.pop());
		Assertions.assertEquals(3, priorityQueue.pop());
		Assertions.assertEquals(2, priorityQueue.pop());
		Assertions.assertEquals(1, priorityQueue.pop());
		Assertions.assertThrowsExactly(ArrayIndexOutOfBoundsException.class, priorityQueue::front);
		Assertions.assertThrowsExactly(ArrayIndexOutOfBoundsException.class, priorityQueue::pop);
		Assertions.assertThrowsExactly(UnsupportedOperationException.class, priorityQueue::back);
	}

}