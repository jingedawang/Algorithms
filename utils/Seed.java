package utils;

import java.util.Random;

/**
 * 种子生成器，在同一应用程序中生成不重复的种子，可用于随机数生成器。
 * @author wjg
 *
 */
public class Seed {

	private static Random random = new Random(System.currentTimeMillis());
	
	/**
	 * 获得一个种子
	 * @return 种子
	 */
	public static long next() {
		return random.nextLong();
	}
	
}
