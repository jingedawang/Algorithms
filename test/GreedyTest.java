/**
 * Copyright 2022 jingedawang
 */

import greedy.HuffmanCode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test class for greedy algorithms.
 */
public class GreedyTest {

	@Test
	void main() {
		HuffmanCode.main(new String[]{});
	}

	@Test
	void huffmanCode() {
		char[] characters = {'a', 'b', 'c', 'd', 'e', 'f'};
		int[] frequencies = {45, 13, 12, 16, 9, 5};

		HuffmanCode huffmanCode = new HuffmanCode();
		huffmanCode.buildHuffmanTree(characters, frequencies);
		Assertions.assertEquals("0", huffmanCode.encode("a"));
		Assertions.assertEquals("101", huffmanCode.encode("b"));
		Assertions.assertEquals("100", huffmanCode.encode("c"));
		Assertions.assertEquals("111", huffmanCode.encode("d"));
		Assertions.assertEquals("1101", huffmanCode.encode("e"));
		Assertions.assertEquals("1100", huffmanCode.encode("f"));
		Assertions.assertEquals("010110011111011100", huffmanCode.encode("abcdef"));
	}

}