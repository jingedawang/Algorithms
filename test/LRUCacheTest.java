import cache.LRUCache;
import cache.LRUCacheSimple;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test class for {@link LRUCache}.
 */
public class LRUCacheTest {

	@Test
	void main() {
		LRUCache.main(new String[]{});
		LRUCacheSimple.main(new String[]{});
	}

	@Test
	void getAndPut() {
		LRUCache lruCache = new LRUCache(2);
		lruCache.put(1, 1);
		Assertions.assertEquals(1, lruCache.get(1));
		Assertions.assertEquals(-1, lruCache.get(2));
		lruCache.put(2, 2);
		lruCache.put(3, 3);
		Assertions.assertEquals(-1, lruCache.get(1));
		Assertions.assertEquals(2, lruCache.get(2));
		lruCache.put(4, 4);
		Assertions.assertEquals(-1, lruCache.get(3));

		LRUCacheSimple lruCacheSimple = new LRUCacheSimple(2);
		lruCacheSimple.put(1, 1);
		Assertions.assertEquals(1, lruCacheSimple.get(1));
		Assertions.assertEquals(-1, lruCacheSimple.get(2));
		lruCacheSimple.put(2, 2);
		lruCacheSimple.put(3, 3);
		Assertions.assertEquals(-1, lruCacheSimple.get(1));
		Assertions.assertEquals(2, lruCacheSimple.get(2));
		lruCacheSimple.put(4, 4);
		Assertions.assertEquals(-1, lruCacheSimple.get(3));
	}

}