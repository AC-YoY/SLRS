package chapter04;

import java.util.Arrays;
import java.util.Random;

/**
 * 归并算法 排序：
 * 最坏情况运行时间Θ(nlgn)  平均运行时间Θ(nlgn)
 * 根据一个排好序的数组的条件
 * 将事件划分为 ：将两个排好序的数组排序。
 * 
 * @author 建苍
 * 
 */
public class Merge_Sort_Demo {
	private static int[] arrays;
	private static Integer[] arrays2;
	static{
		Random random = new Random();
		int len = random.nextInt(50);
		arrays = new int[len];
		arrays2 = new Integer[len];
		for(int i=0;i<len;i++){
			arrays[i] = random.nextInt(100);
		}
		for(int i=0;i<len;i++){
			arrays2[i] = arrays[i];
		}
	}
	
	public static void main(String[] args) {
		System.out.println(Arrays.toString(arrays));
		MergeSort(arrays,0,arrays.length-1);
		System.out.println(Arrays.toString(arrays));
		mergeSort(arrays2);
		System.out.println(Arrays.toString(arrays2));
		
	}

	/**
	 * 分治的体现 确保前后两段数组都是排好序的！！！ 然后去排两个已经排好序的数组
	 * 
	 * @param array
	 *            输入数组
	 * @param p
	 *            起始下标
	 * @param r
	 *            末尾下标
	 */
	public static void MergeSort(int[] array, int p, int r) {
		if (p < r) {
			int q = (r+p) / 2;// 随便划分一半，java中等价于 floor
			MergeSort(array, p, q);
			MergeSort(array, q + 1, r);
			Merge(array, p, q, r);
		}
	}

	/**
	 * 如何将两个已经排序好的数组合并成一个？参考两叠扑克牌。
	 * 
	 * @param array
	 * @param p
	 * @param q
	 * @param r
	 */
	public static void Merge(int[] array, int p, int q, int r) {
		// 构造两个新的数组
		int len1 = q - p + 1;
		int len2 = r - q;
		int[] leftArray = new int[len1+1];
		int[] rightArray = new int[len2+1];
		for (int i = 0; i < len1; i++) {
			leftArray[i] = array[p + i];
		}
		for (int i = 0; i < len2; i++) {
			rightArray[i] = array[q+1 + i];
		}
		//增加两个哨兵位置
		leftArray[len1] = Integer.MAX_VALUE;
		rightArray[len2] = Integer.MAX_VALUE;
		
		int i = 0;
		int j = 0;
		//因为r是我们的边界，所以边界也是要处理的!!！
		for(int k=p;k<=r;k++){
			if(leftArray[i]<rightArray[j]){
				array[k] = leftArray[i];
				i++;
			}else{
				array[k] = rightArray[j];
				j++;
			}
		}
	}
	
	//-----------------------------------------------------------------------
	//-----------------------------------------------------------------------
	//-----------------------------------------------------------------------
	
	@SuppressWarnings("rawtypes") 
    public static Comparable[] mergeSort(Comparable[] list)
    {
        //If list is empty; no need to do anything
        if (list.length <= 1) {
            return list;
        }
         
        //Split the array in half in two parts
        Comparable[] first = new Comparable[list.length / 2];
        Comparable[] second = new Comparable[list.length - first.length];
        System.arraycopy(list, 0, first, 0, first.length);
        System.arraycopy(list, first.length, second, 0, second.length);
         
        //Sort each half recursively
        mergeSort(first);
        mergeSort(second);
         
        //Merge both halves together, overwriting to original array
        merge(first, second, list);
        return list;
    }
     
    @SuppressWarnings({ "rawtypes", "unchecked" }) 
    private static void merge(Comparable[] first, Comparable[] second, Comparable[] result)
    {
        //Index Position in first array - starting with first element
        int iFirst = 0;
         
        //Index Position in second array - starting with first element
        int iSecond = 0;
         
        //Index Position in merged array - starting with first position
        int iMerged = 0;
         
        //Compare elements at iFirst and iSecond, 
        //and move smaller element at iMerged
        while (iFirst < first.length && iSecond < second.length)
        {
            if (first[iFirst].compareTo(second[iSecond]) < 0) 
            {
                result[iMerged] = first[iFirst];
                iFirst++;
            } 
            else
            {
                result[iMerged] = second[iSecond];
                iSecond++;
            }
            iMerged++;
        }
        //copy remaining elements from both halves - each half will have already sorted elements
        System.arraycopy(first, iFirst, result, iMerged, first.length - iFirst);
        System.arraycopy(second, iSecond, result, iMerged, second.length - iSecond);
    }
}
