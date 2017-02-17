/**
 * 
 */
package com.ucas.algorithms.utils;

import java.util.Arrays;
import java.util.Random;

/**
 * @author wjg
 * @version 0.0.1
 *
 */
public class IntegerArrayGenerator {

	private static int[] A = new int[] {4, 8, 1, 2, 0, 6, 5, 1, 9, 3};
	
	/**
	 * 返回内置的长度为10的元素大小不超过10的数组。
	 * @return 数组
	 */
	public static int[] fixedArray() {
		return A.clone();
	}
	
	/**
	 * 返回随机生成的长度为10的元素大小不超过10的数组。
	 * @return 数组
	 */
	public static int[] randomArray() {
		return randomArray(10);
	}
	
	/**
	 * 返回随机生成的长度为10的指定元素大小上限的数组。
	 * @param upperLimit 元素大小上限
	 * @return 数组
	 */
	public static int[] randomArray(int upperLimit) {
		return randomArray(upperLimit, 10);
	}
	
	/**
	 * 返回随机生成的指定长度的指定元素大小上限的数组。
	 * @param upperLimit 元素大小上限
	 * @param size 数组长度
	 * @return 数组
	 */
	public static int[] randomArray(int upperLimit, int size) {
		Random random = new Random(Seed.next());
		int[] randomArray = new int[size];
		for (int i=0; i<randomArray.length; i++) {
			randomArray[i] = random.nextInt(upperLimit);
		}
		return randomArray;
	}
	
	/**
	 * 返回随机生成的指定长度的指定元素大小上下限的数组。
	 * @param lowerLimit 元素大小下限
	 * @param upperLimit 元素大小上限
	 * @param size 数组长度
	 * @return 数组
	 */
	public static int[] randomArray(int lowerLimit, int upperLimit, int size) {
		int[] arr = randomArray(upperLimit - lowerLimit, size);
		for (int i=0; i<arr.length; i++) {
			arr[i] += lowerLimit;
		}
		return arr;
	}
	
	/**
	 * 返回随机生成的长度为10的分布在[0,1)区间上的数组。
	 * @return 数组
	 */
	public static double[] randomArrayDouble() {
		int[] arr = randomArray();
		double[] arrDouble = new double[arr.length];
		for (int i=0; i<arr.length; i++) {
			arrDouble[i] = arr[i] / 10.0;
		}
		return arrDouble;
	}
	
}
