package chapter06;

import java.util.Arrays;
import java.util.Random;


/**
 * 计数排序： Θ(k+n) 如果k O(n) 则 Θ(n)
 * 需要有一个具体数值k，满足所有元素都小于k
 * 
 * @author 建苍
 *
 */
public class CountingSortDemo {
	public static void main(String[] args) {
		Random random = new Random();
		int[] x = new int[20];
		int[] target = null;
		for(int i =0;i<20;i++){
			x[i] = random.nextInt(100);
		}
		System.out.println(Arrays.toString(x));
		target = countingSort(x,target,100);
//		为什么这样写出来的target就是null，问题应该是在static上面
//		countingSort(x,target,100);
		System.out.println(Arrays.toString(target));
	}
	
	//完成后新生成的数组
	private static int[] copy;
	//临时转移数据的空间
	private static int[] c;
	public static int[] countingSort(int[] arr,int[] b,int k){
		copy = new int[arr.length];
		//其实k也行，我觉得k+1解释起来更加合理一些
		c = new int[k+1];//这里有自动初始化，不虚
		//把arr中每个数值在c中对应的位置用个数填充
		for(int i=0;i<arr.length;i++){
			c[arr[i]] += 1;
		}
		//现在c中存放对应arr中位置小于它的数值。
		for(int i=1;i<c.length;i++){
			c[i] = c[i] + c[i-1];
		}
		//最后 填充目标数组。。画个图慢慢想去
		for(int i=arr.length-1;i>=0;i--){
			//因为c里面存放的是比该位置小的元素，所以应该要-1才符合他的下标
			copy[c[arr[i]]-1] = arr[i];
			c[arr[i]] -= 1;
		}
		return copy;
	}
}
