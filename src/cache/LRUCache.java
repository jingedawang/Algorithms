/**
 * 
 */
package cache;

import java.util.HashMap;

/**
 * @author wjg
 * 
 * LRU（Least Recently Used）缓存算法
 * 使用HashMap+双向链表，使get和put的时间复杂度达到O(1)。
 * 读缓存时从HashMap中查找key，更新缓存时同时更新HashMap和双向链表，双向链表始终按照访问顺序排列。
 *
 */
public class LRUCache {

	/**
	 * @param args
	 * 测试程序，访问顺序为[[1,1],[2,2],[1],[3,3],[2],[4,4],[1],[3],[4]]，其中成对的数调用put，单个数调用get。
	 * get的结果为[1],[-1],[-1],[3],[4]，-1表示缓存未命中，其它数字表示命中。
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
	
	// 缓存容量
	private final int capacity;
	// 用于加速缓存项随机访问性能的HashMap
    private HashMap<Integer, Entry> map;
    // 双向链表头结点，该侧的缓存项访问时间较早
    private Entry head;
    // 双向链表尾结点，该侧的缓存项访问时间较新
    private Entry tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<Integer, Entry>((int)(capacity / 0.75 + 1), 0.75f);
        head = new Entry(0, 0);
        tail = new Entry(0, 0);
        head.next = tail;
        tail.prev = head;
    }
    
    /**
     * 从缓存中获取key对应的值，若未命中则返回-1
     * @param key 键
     * @return key对应的值，若未命中则返回-1
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
     * 向缓存中插入或更新值
     * @param key 待更新的键
     * @param value 待更新的值
     */
    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Entry entry = map.get(key);
            entry.value = value;
            popToTail(entry);
        }
        else {
            Entry newEntry = new Entry(key, value);
            if (map.size() >= capacity) {
                Entry first = removeFirst();
                map.remove(first.key);
            }
            addToTail(newEntry);
            map.put(key, newEntry);
        }
    }
    
    /**
     * 缓存项的包装类，包含键、值、前驱结点、后继结点
     * @author wjg
     *
     */
    class Entry {
        int key;
        int value;
        Entry prev;
        Entry next;
        
        Entry(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
    
    // 将entry结点移动到链表末端
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
    
    // 移除链表首端的结点
    private Entry removeFirst() {
        Entry first = head.next;
        Entry second = first.next;
        head.next = second;
        second.prev = head;
        return first;
    }
    
    // 添加entry结点到链表末端
    private void addToTail(Entry entry) {
        Entry last = tail.prev;
        last.next = entry;
        tail.prev = entry;
        entry.prev = last;
        entry.next = tail;
    }

}
