package chapter04;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Maximum_SubArray_Demo {
	private static int[] ARRAY = new int[20000000];
	
	static{
		Random random = new Random();
		for(int i=0;i<ARRAY.length;i++){
			int x = random.nextInt(100);
			if(random.nextInt(100)<33){
				x = -x;
			}
			ARRAY[i] = x;
		}
	}
	public static void main(String[] args) {
		
//		int[] result = findMaxSubArray(ARRAY,0,19);
//		System.out.println(Arrays.toString(result));
		
//		long startTime = System.nanoTime();
//		int max = maxSubArrayCubic(ARRAY);
//		long endTime = TimeUnit.MILLISECONDS.convert(System.nanoTime() - startTime,TimeUnit.NANOSECONDS);
//		System.out.println(max+": "+endTime);
		
//		long startTime2 = System.nanoTime();
//		int max2 = maxSubArrayQuadratic(ARRAY);
//		long endTime2 = TimeUnit.MILLISECONDS.convert(System.nanoTime() - startTime2,TimeUnit.NANOSECONDS);
//		System.out.println(max2+": "+endTime2);
		
		long startTime3 = System.nanoTime();
		int max3 = maxSumRec(ARRAY,0,ARRAY.length-1);
		long endTime3 = TimeUnit.MILLISECONDS.convert(System.nanoTime() - startTime3,TimeUnit.NANOSECONDS);
		System.out.println(max3+": "+endTime3);

		long startTime4 = System.nanoTime();
		int max4 = maxSubSum(ARRAY);
		long endTime4 = TimeUnit.MILLISECONDS.convert(System.nanoTime() - startTime4,TimeUnit.NANOSECONDS);
		System.out.println(max4+": "+endTime4);
		
		
		
	}
	/**
	 * 求最大和子数组，
	 * 数组的处理划分，从array[low]到array[high]全部排序
	 * 
	 * 返回值 是最大子数组之一，头坐标，尾坐标，子数组的和
	 * @param array
	 * @param low	左边坐标
	 * @param high	右边坐标
	 */
	public static int[] findMaxSubArray(int[] array,int low,int high){
		if(low == high){
			//单一元素的情况
			return new int[]{low,high,array[low]};
		}else{
			int length = low + high ;
			int mid = length%2==0 ? length/2 : length/2+1;
			int[] leftResult = findMaxSubArray(array,low,mid);
			int[] rightResult = findMaxSubArray(array,mid+1,high);
			
			int[] crossResult = findMaxCrossSubArray(array,low,mid,high);
			if(leftResult[2]>=rightResult[2]&&leftResult[2]>=crossResult[2]){
				return leftResult;
			}else if(rightResult[2]>=leftResult[2]&&rightResult[2]>=crossResult[2]){
				return rightResult;
			}else{
				return crossResult;
			}
		}
		
	}
	/**
	 * 将一个数组用mid分开，取得横跨mid的最大子数组，array[high]并不包括在内
	 * 返回值： 左边坐标，右边坐标，和
	 * @param array
	 * @param low	左边的坐标
	 * @param mid	横跨的数组坐标
	 * @param high	右边的坐标
	 * @return
	 */
	public static int[] findMaxCrossSubArray(int[] array,int low,int mid,int high){
		//处理左边界
		int leftIndex = low;
		int leftSum = -1000;
		int sum = 0;
		for(int i=mid;i>low-1;i--){
			sum = sum + array[i];
			if(sum > leftSum){
				leftSum = sum;
				leftIndex = i;
			}
		}
		//处理右边界
		int rightIndex = high;
		int rightSum = -1000;
		sum = 0;
		for(int i = mid+1;i<high;i++){
			sum = sum + array[i];
			if(sum > rightSum){
				rightSum = sum;
				rightIndex = i;
			}
		}
		return new int[]{ leftIndex, rightIndex, leftSum+rightSum};
	}
	/**
	 * dynamic programming solution
	 * must be something wrong
	 * @param array
	 * @return
	 */
//	public static int MaxSubArray(int[] array){
//		int max = array[0];
//		int[] sum = new int[array.length];
//		for(int i=1;i<array.length;i++){
//			sum[i] = Math.max(array[i], sum[i-1]+array[i]);
//			max = Math.max(max, sum[i]);
//		}
//		return max;
//	}
//	public static int[] MaxSubArrays(int[] array){
//		int left = 0;
//		int right = array.length - 1;
//		int max = array[0];
//		int[] sum = new int[array.length];
//		for(int i=1;i<array.length;i++){
//			if(array[i]> sum[i-1]+array[i]){
//				sum[i] = array[i];
//				left = i;
//			}else{
//				sum[i] = sum[i-1]+array[i];
//				right = i;
//			}
//			max = Math.max(max, sum[i]);
//		}
//		return new int[]{left,right,max};
//	} 
	//标准
//    public static int maxSubArray(int[] A) {
//        if (A == null || A.length == 0){
//            return 0;
//        }
//        
//        int max = Integer.MIN_VALUE, sum = 0;
//        for (int i = 0; i < A.length; i++) {
//            sum += A[i];
//            max = Math.max(max, sum);
//            sum = Math.max(sum, 0);
//        }
//
//        return max;
//    }
	/**
	 * 三次算法
	 * @param array
	 * @return
	 */
	public static int maxSubArrayCubic(int[] array){
		int max = 0;
		for(int i=0;i<array.length;i++){
			//注意！为什么这里我不用j=i+1，因为要计算单个的数据
			for(int j=i;j < array.length;j++){
				int sum = 0;
				//注意！k<=j是因为根据上层循环，j并不能取到
				for(int k=i;k<=j;k++){
					sum += array[k];
				}
				max = Math.max(sum, max);
			}
		}
		return max;
	}
	/**
	 * 二次算法
	 * @param array
	 * @return
	 */
	public static int maxSubArrayQuadratic(int[] array){
		int max = 0;
		for(int i=0;i<array.length;i++){
			int sum = 0;
			for(int j=i;j<array.length;j++){
				sum += array[j];
				max = Math.max(sum, max);
			}
		}
		return max;
	}
	/**
	 * 
	 * @param array		待确认的数组
	 * @param left		下标
	 * @param right		下标
	 * @return
	 */
	public static int maxSumRec(int[] array,int left,int right){
		if(left == right){
			if(array[left]>0){
				return array[left];
			}else{
				return 0;
			}
		}
		//just divide this array into two parts,don't care precise length
		int center = (left+right)/2;
		//recurse core part
		int maxLeftSum  = maxSumRec(array,left,center);
		int maxRightSum = maxSumRec(array,center+1,right);
		
		int maxLeftBorderSum = 0, leftBorderSum = 0;
		for(int i = center;i >= left;i--){
			leftBorderSum += array[i];
			maxLeftBorderSum = Math.max(maxLeftBorderSum, leftBorderSum);
		}
		
		int maxRightBorderSum = 0, rightBorderSum = 0;
		for(int i = center+1;i <= right;i++){
			rightBorderSum += array[i];
			maxRightBorderSum = Math.max(maxRightBorderSum, rightBorderSum);
		}
		
		return max3(maxLeftSum,maxRightSum,maxLeftBorderSum+maxRightBorderSum);
	}
	/**
	 * 貌似是个最优解
	 * @param array
	 * @return
	 */
	public static int maxSubSum(int[] array){
		int max = 0, thisSum = 0;
		for(int i=0;i<array.length;i++){
			thisSum += array[i];
			max = Math.max(max, thisSum);
			if(thisSum < 0){
				thisSum = 0;
			}
		}
		return max;
	}
	//------------ 一些辅助 方法 -----------------
	public static int max3(int a,int b ,int c){
		if(a>=b&&a>=c){return a;}
		else if (b>=a&&b>=c){return b;}
		else {return c;}
	}
}
