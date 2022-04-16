/**
 * Copyright 2020 jingedawang
 */
package cache;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <h3>A more simple implementation of LRU cache</h3>
 * <p>
 * {@link LinkedHashMap} is a data structure implemented by {@code HashMap} and bidirectional linked list provided by
 * JDK. We just need reimplement {@link LinkedHashMap#removeEldestEntry(Map.Entry)} method. When it returns {@code true},
 * {@link LinkedHashMap} will remove the oldest node.
 */
public class LRUCacheSimple {

	/**
	 * Test code.
	 *
	 * <p>Invoke as the following order
	 * {@code [[1,1],[2,2],[1],[3,3],[2],[4,4],[1],[3],[4]]},
	 * in which we invoke {@link #put(int, int)}} for number pairs, and {@link #get(int)} for single numbers.</p>
	 *
	 * <p>The output should be {@code [1],[-1],[-1],[3],[4]}, where {@code -1} indicates a cache miss,
	 * otherwise cache hit.</p>
	 */
	public static void main(String[] args) {
		LRUCacheSimple cache = new LRUCacheSimple(2);
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
	 * Constructor. Create {@link LinkedHashMap} object and override the
	 * {@link LinkedHashMap#removeEldestEntry(Map.Entry)} method.
	 *
	 * @param capacity The capacity of the cache.
	 */
	public LRUCacheSimple(int capacity) {
		this.capacity = capacity;
		map = new LinkedHashMap<Integer, Integer>(capacity, 0.75f, true) {
			protected boolean removeEldestEntry(Map.Entry eldest) {
				return size() > capacity;
			}
		};
	}

	/**
	 * Get specified key from cache.
	 *
	 * @param key The key to be fetched.
	 * @return The key if cache hit, -1 otherwise.
	 */
	public int get(int key) {
		return map.getOrDefault(key, -1);
	}

	/**
	 * Put new value into the cache or update an existing value.
	 *
	 * @param key   The key to be added or updated.
	 * @param value The new value to be added or updated.
	 */
	public void put(int key, int value) {
		map.put(key, value);
	}

	private LinkedHashMap<Integer, Integer> map;
	private final int capacity;
}