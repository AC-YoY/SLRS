package chapter06;

import java.util.Arrays;
import java.util.Random;

/**
 * heapsort相关的算法实现 O(nlgn)
 * 
 * 类 内部 维护一个 按照heap来排列的数组,
 * 排序时，使用这个数组
 * 
 * !本类中 全部序号都是数组的下标
 * 
 * @author 建苍
 *
 */
public class HeapDemo {

	public static void main(String[] args) {
		int[] numbers= new int[20];
		Random random = new Random();
		for(int i=0;i<20;i++){
			numbers[i] = random.nextInt(100);
		}
		System.out.println(Arrays.toString(numbers));
		heapSort(numbers);
		System.out.println(Arrays.toString(numbers));
	}
	/**
	 * 内部维护的堆
	 */
	private static int[] heap;
	/**
	 * 堆的最终下标
	 */
	private static int n;
	/**
	 * 保持heap中一个下标以及其子树 maxHeapify的特性
	 * @param array  make a ordered heap base on this
	 * @param i  the index of the element 
	 */
	public static void maxHeapify(int[] array,int i){
		// +1 是为了将节点顺序转换成数组下标 index+1 = (index+1)*2
		int left = i*2 +1;
		int right = left +1;
		int max;
		if(left<=n &&array[left]>array[i]){
			max = left;
		}else{
			max = i;
		} 
		if(right<=n &&array[right]>array[max]){
			max = right;
		}
		//只有当该下标的Node没有按照顺序排列，才会recurse其child
		if(max != i){
			exchange(i,max);
			maxHeapify(array,max);
		}
	}
	/**
	 * build a max-heap based on specified array
	 * @param array  specified array 
	 */
	public static void buildMaxHeap(int[] array){
		n = array.length -1;
		// -1 是因为从节点顺序转换成数组下标,直接写成n/2也可以，不过可能会多跑一次循环
		int index = array.length/2 - 1;
		for(int i=index;i>=0;i--){
			maxHeapify(array,i);
		}
	}
//	public static void buildheap(int []a) {
//		n = a.length-1;
//      	for(int i=n/2; i>=0; i--){
//      		maxHeapify(a,i);
//      	}
//	}
	/**
	 * sort
	 * @param array
	 */
	public static void heapSort(int[] array){
		heap = array;
		buildMaxHeap(heap);
		for(int i=n; i>0; i--) {
			exchange(0, i);
	        n = n-1;
	        //重新生成一个max-heap
	        maxHeapify(heap, 0);
		}
	}
	
	public static void exchange(int x,int y){
		int temp = heap[x];
		heap[x] = heap[y];
		heap[y] = temp;
	}
	
}
