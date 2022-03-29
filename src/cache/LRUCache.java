/**
 * Copyright 2020 jingedawang
 */
package cache;

import java.util.HashMap;

/**
 * <h3>LRU（Least Recently Used）cache</h3>
 *
 * <p>This class combines {@link HashMap} and bidirectional linked list to make {@link #get(int)} and
 * {@link #put(int, int)} reachin O(1) time complexity.
 * Lookup key from {@link HashMap} when reading cache. Update {@link HashMap} and the bidirectional linked list when
 * updating cache. During the operations, the bidirectional linked list is always in access order.</p>
 *
 * <p>More detailed explanations in <a href="https://www.jianshu.com/p/b1ab4a170c3c">LRU缓存算法</a></p>
 */
public class LRUCache {

	/**
	 * Test code.
	 *
	 * <p>Invoke as the following order
	 * {@code [[1,1],[2,2],[1],[3,3],[2],[4,4],[1],[3],[4]]}，
	 * in which we invoke {@link #put(int, int)}} for number pairs, and {@link #get(int)} for single numbers.</p>
	 *
	 * <p>The output should be {@code [1],[-1],[-1],[3],[4]}，where {@code -1} indicates a cache miss,
	 * otherwise cache hit.</p>
	 */
	public static void main(String[] args) {
		LRUCache cache = new LRUCache(2);
		cache.put(1, 1);
		cache.put(2, 2);
		System.out.println(cache.get(1));
		cache.put(3, 3);
		System.out.println(cache.get(2));
		cache.put(4, 4);
		System.out.println(cache.get(1));
		System.out.println(cache.get(3));
		System.out.println(cache.get(4));
	}

	/**
	 * Constructor. Initialize underlying data.
	 *
	 * @param capacity The capacity of the cache.
	 */
	public LRUCache(int capacity) {
		this.capacity = capacity;
		map = new HashMap<Integer, Entry>((int) (capacity / 0.75 + 1), 0.75f);
		head = new Entry(0, 0);
		tail = new Entry(0, 0);
		head.next = tail;
		tail.prev = head;
	}

	/**
	 * Get specified key from cache.
	 *
	 * @param key The key to be fetched.
	 * @return The key if cache hit, -1 otherwise.
	 */
	public int get(int key) {
		if (map.containsKey(key)) {
			Entry entry = map.get(key);
			popToTail(entry);
			return entry.value;
		}
		return -1;
	}

	/**
	 * Put new value into the cache or update an existing value.
	 *
	 * @param key   The key to be added or updated.
	 * @param value The new value to be added or updated.
	 */
	public void put(int key, int value) {
		if (map.containsKey(key)) {
			Entry entry = map.get(key);
			entry.value = value;
			popToTail(entry);
		} else {
			Entry newEntry = new Entry(key, value);
			if (map.size() >= capacity) {
				Entry first = removeFirst();
				map.remove(first.key);
			}
			addToTail(newEntry);
			map.put(key, newEntry);
		}
	}

	// Node wrapper class for cache elements. It's also used as the node of bidirectional linked list.
	private class Entry {
		int key;
		int value;
		Entry prev;
		Entry next;

		Entry(int key, int value) {
			this.key = key;
			this.value = value;
		}
	}

	// Capacity of the cache
	private final int capacity;
	// Underlying data structure used for accelerating random access
	private HashMap<Integer, Entry> map;
	// Head node of the bidirectional linked list, the cache nodes near this side have earlier access time
	private Entry head;
	// Tail node of the bidirectional linked list, the cache nodes near this side have newer access time
	private Entry tail;

	// Pop the entry to the tail of the linked list.
	private void popToTail(Entry entry) {
		Entry prev = entry.prev;
		Entry next = entry.next;
		prev.next = next;
		next.prev = prev;
		Entry last = tail.prev;
		last.next = entry;
		tail.prev = entry;
		entry.prev = last;
		entry.next = tail;
	}

	// Add the entry to the tail of the linked list.
	private void addToTail(Entry entry) {
		Entry last = tail.prev;
		last.next = entry;
		tail.prev = entry;
		entry.prev = last;
		entry.next = tail;
	}

	// Remove the first entry of the linked list and return it.
	private Entry removeFirst() {
		Entry first = head.next;
		Entry second = first.next;
		head.next = second;
		second.prev = head;
		return first;
	}
}