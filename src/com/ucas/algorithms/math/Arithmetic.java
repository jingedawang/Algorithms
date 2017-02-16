/**
 * 
 */
package com.ucas.algorithms.math;

/**
 * @author wjg
 *
 */
public class Arithmetic {
	
	/**
	 * 整数乘方运算，只允许正数次方
	 * @param a 底数
	 * @param b 指数
	 * @return 幂
	 */
	public static int pow(int a, int b) {
		if (b < 0) {
			throw new IllegalArgumentException("The second argument of method pow must be positive.");
		}
		int result = 1;		
		for (int i=0; i<b; i++) {
			result *= a;
		}	
		return result;
	}

}
