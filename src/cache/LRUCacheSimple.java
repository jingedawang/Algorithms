/**
 * 
 */
package cache;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 
 * 一个更简单实用的LRUCache方案，使用LinkedHashMap即可实现。
 * LinkedHashMap提供了按照访问顺序排序的方案，内部也是使用HashMap+双向链表。
 * 只需要重写removeEldestEntry方法，当该方法返回true时，LinkedHashMap会删除最旧的结点。
 * 
 * @author wjg
 *
 */
public class LRUCacheSimple {

	/**
	 * @param args
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
	
	private LinkedHashMap<Integer, Integer> map;
    private final int capacity;
    public LRUCacheSimple(int capacity) {
    	this.capacity = capacity;
        map = new LinkedHashMap<Integer, Integer>(capacity, 0.75f, true){
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() > capacity;
            }
        };
    }
    public int get(int key) {
        return map.getOrDefault(key, -1);
    }
    public void put(int key, int value) {
        map.put(key, value);
    }

}
