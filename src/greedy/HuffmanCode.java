/**
 * Copyright 2022 jingedawang
 */
package greedy;

import utils.ArrayPrinter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * Huffman code.
 *
 * Given the frequencies of each character, we need to find the most efficient encoding strategy to make the encoded
 * text short. The variant encoding method is called Huffman code.
 */
public class HuffmanCode {

	/**
	 * Demo code.
	 */
	public static void main(String[] args) {
		char[] characters = {'a', 'b', 'c', 'd', 'e', 'f'};
		int[] frequencies = {45, 13, 12, 16, 9, 5};
		System.out.println("The character collection is:");
		ArrayPrinter.print(characters);
		System.out.println("The frequencies of each character are:");
		ArrayPrinter.print(frequencies);

		HuffmanCode huffmanCode = new HuffmanCode();
		huffmanCode.buildHuffmanTree(characters, frequencies);
		System.out.println();
		System.out.println("The huffman code book is");
		for (int i = 0; i < characters.length; i++) {
			System.out.println(characters[i] + ": " + huffmanCode.encode(String.valueOf(characters[i])));
		}
		String str = "abcdef";
		String code = huffmanCode.encode(str);
		System.out.println("The huffman code of string \"" + str + "\" is " + code + ".");
	}

	/**
	 * Build the huffman tree using given characters and its corresponding frequencies.
	 *
	 * @param characters  A character array.
	 * @param frequencies A frequency array corresponding to the character array.
	 */
	public void buildHuffmanTree(char[] characters, int[] frequencies) {
		ArrayList<Node> list = new ArrayList<>(characters.length);
		for (int i = 0; i < characters.length; i++) {
			list.add(new Node(characters[i], frequencies[i]));
		}
		PriorityQueue<Node> queue = new PriorityQueue<>(list);
		for (int i = 0; i < characters.length - 1; i++) {
			Node left = queue.poll();
			Node right = queue.poll();
			Node root = new Node(' ', left.frequency + right.frequency);
			root.left = left;
			root.right = right;
			queue.add(root);
		}
		Node huffmanTree = queue.poll();
		codeBook = new HashMap<Character, String>(characters.length * 2);
		generateCodeBook(huffmanTree, "");
	}

	/**
	 * Get the huffman encode of the given string.
	 *
	 * @param str The string to be encoded.
	 * @return The code of the string.
	 */
	public String encode(String str) {
		StringBuilder stringBuilder = new StringBuilder();
		for (char c : str.toCharArray()) {
			stringBuilder.append(codeBook.get(c));
		}
		return stringBuilder.toString();
	}

	/**
	 * Inner Node class.
	 * <p>
	 * The comparison between instances is based on {@code frequency} field.
	 */
	private static class Node implements Comparable<Node> {

		Node(char character, int frequency) {
			this.character = character;
			this.frequency = frequency;
		}

		char character;
		int frequency;
		Node left;
		Node right;

		@Override
		public int compareTo(Node node) {
			return this.frequency - node.frequency;
		}
	}

	/**
	 * The code book field.
	 */
	private HashMap<Character, String> codeBook;

	/**
	 * Generate the code book recursively.
	 *
	 * @param root   The root of current subtree.
	 * @param prefix The prefix string from the root to this subtree.
	 */
	private void generateCodeBook(Node root, String prefix) {
		if (root.left == null || root.right == null) {
			codeBook.put(root.character, prefix);
		} else {
			generateCodeBook(root.left, prefix + "0");
			generateCodeBook(root.right, prefix + "1");
		}
	}

}