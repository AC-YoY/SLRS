package chapter02;

import java.util.Arrays;

/**
 * 将A[j]插入到排序好的A[j-1]中去 insert A[j] into the sorted sequence A[j-1]
 * 
 * 最坏情况运行时间Θ(n^2)  平均运行时间Θ(n^2)
 * 
 * @author 建苍
 * 
 */
public class Insertion_Sort {
	private static int[] A = { 32, 54, 21, 7, 98, 43, 57, 7, 43, 2 };

	public static void main(String[] args) {
		Sort(A);
		System.out.println(Arrays.toString(A));
		SortDecr(A);
		System.out.println(Arrays.toString(A));
	}
	
	//increase Order
	public static void Sort(int[] array){
		for (int j = 1; j < array.length; j++) {
			int key = array[j];
			
			int i = j - 1;
			while (i >= 0 && array[i] > key) {
				array[i + 1] = array[i];
				i--;
			}
			array[i+1] = key;
		}
	}
	
	//Decrease Order
	public static void SortDecr(int[] array){
		for(int i=1;i<array.length;i++){
			int key = array[i];
			
			int j = i-1;
			while(j>-1 && array[j]<key){
				array[j+1] = array[j];
				j--;
			}
			array[j+1] = key;
		}	
	}
}
