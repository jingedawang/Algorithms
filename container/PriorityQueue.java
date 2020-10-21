package container;

/**
 * @author wjg
 * @version 0.0.1
 *
 */
public class PriorityQueue {
	// TODO 由于Heap类被设计成了固定大小的类，因此该优先队列无法实现，需要先修改Heap才行。暂且搁置
	
	private int[] arr;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public PriorityQueue(int[] arr) {
		this.arr = arr;
	}
	
	public void insert(int x) {
		// TODO
	}
	
	public int maximum() {
		// TODO
		return arr[0];
	}
	
	public int extractMax() {
		// TODO
		return 0;
	}
	
	public void increaseKey(int x, int k) {
		// TODO
	}

}
