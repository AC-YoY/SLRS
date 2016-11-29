package chapter02;

import java.util.Arrays;

/**
 * 
 * 
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
