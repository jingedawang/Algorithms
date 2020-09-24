package sort;

public class SortByPriority {

	/**
	 * 根据给定的优先级数组重排数组arr（未完成）
	 * @param arr 待重排的数组
	 * @param priority 存储优先级信息的数组
	 */
	public void sortByPriority(int[] arr, int[] priority) {
		if (arr.length != priority.length) {
			throw new IllegalArgumentException("Two arrays must have same length.");
		}
		new OnSort().onSort(priority);
		///暂时未想出使用优先级排序数组的具体方法。。。
		for (int i=0; i<arr.length; i++) {
			
		}
	}
	
}
