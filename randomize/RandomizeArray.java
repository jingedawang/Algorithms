package randomize;

import java.util.Random;

import sort.SortByPriority;
import utils.ArrayPrinter;
import utils.ArrayGenerator;
import utils.Seed;

public class RandomizeArray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] arr = ArrayGenerator.fixedArray();
		ArrayPrinter.print(arr);
		randomizeInPlace(arr);
		ArrayPrinter.print(arr);
		
		
	}
	
	/**
	 * 使用随机产生的优先级数组生成随机排列（未完成）
	 * @param arr 需要随机排列的数组
	 */
	public static void permuteBySorting(int[] arr) {
		int n = arr.length;
		int[] P = ArrayGenerator.randomArray(n * n * n, n);
		new SortByPriority().sortByPriority(arr, P);
	}
	
	/**
	 * 使用随机交换元素的方式生成随机排列
	 * @param arr 需要随机排列的数组
	 */
	public static void randomizeInPlace(int[] arr) {
		int n = arr.length;
		Random random = new Random(Seed.next());
		for (int i=0; i<n; i++) {
			int randomIndex = random.nextInt(n - i) + i;
			int temp = arr[i];
			arr[i] = arr[randomIndex];
			arr[randomIndex] = temp;
		}
	}

}
