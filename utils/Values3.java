/**
 * 
 */
package utils;

import java.util.Map;

/**
 * 封装3返回值的包装类，使用时需指明三个返回值类型，三个返回值类型可不同。
 * @author jinge
 */
public class Values3<T, U, V> {

	public T value1;
	public U value2;
	public V value3;
	
	public Values3(T value1, U value2, V value3) {
		this.value1 = value1;
		this.value2 = value2;
		this.value3 = value3;
	}
	
	
	
}
