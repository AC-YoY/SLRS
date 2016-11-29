package chapter04;
/**
 * 基础面试系列
 * Bit Manipulation!!!!!!!!!!!!!
 * 计算两个bit数字不相同的位数
 * 
 * 异或^ ： 如果两者位数不同，要用0补齐!
 * 与& 或|:这两者都可以用在位运算上
 * 
 * @author 建苍
 *
 */
public class ExclusiveOR {
	public static void main(String[] args) {
		//47 ： 0010 1111
		System.out.println(bitManipulate(47,0));
	}
	public static int bitManipulate(int x,int y){
		int temp = x^y;
		int result = 0;
		while(temp>0){
			result += temp&1;
			temp = temp >> 1;
		}
		return result;
		
//		int bitCount = 0;
//		int z = x ^ y;  //XOR x and y
//		while (z != 0)
//		{
//		  //increment count if last binary digit is a 1:
//		  bitCount += z & 1; 
//		  z = z >> 1;  //shift Z by 1 bit to the right
//		}
//
//		return bitCount;
		
	}
}
