package chapter06;

import java.util.Arrays;
import java.util.Random;

/**
 * QuickSort  最坏Θ(n^2) 期望/平均情况运行时间Θ(nlgn)
 * in place 原址
 * 将数组划分成三部分 [p,x-1] <= x <= [x+1,r]
 * 
 * 
 * @author 建苍
 *
 */
public class QuickSortDemo {
	public static void main(String[] args) {
		int[] arr = new int[20];
		Random rand = new Random();
		for(int i=0;i<20;i++){
			arr[i] = rand.nextInt(100);
		}
		System.out.println(Arrays.toString(arr));
		quickSort(arr,0,19);
		System.out.println(Arrays.toString(arr));
	}
	/**
	 * 
	 * @param arr specified array which is wanted to be sorted
	 * @param p 起始数组下标
	 * @param r 终止数组下标
	 */
	public static void quickSort(int[] arr,int p,int r){
		if(p<r){
			int q = partition(arr,p,r);
			//为什么没有q?因为q已经在合适的位置上了
			quickSort(arr,p,q-1);
			quickSort(arr,q+1,r);
		}
	}
	/**
	 * 将目标数组划分成三部分，并且返回X
	 * @param array specified array
	 * @param p	起始数组下标
	 * @param r	终止数组下标
	 */
	public static int partition(int[] arr,int p,int r){
		int x = arr[r];
		//注意i&j
		int i = p-1;
		for(int j=p;j<r;j++){
			if(arr[j]<=x){
				i++;
				exchange(arr,i,j);
			}
		}
		exchange(arr,i+1,r);
		return i+1;
	}
	
	public static void exchange(int[] arr,int x,int y){
		int temp = arr[x];
		arr[x] = arr[y];
		arr[y] = temp;
	}
}
