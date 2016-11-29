package chapter04;

import java.util.concurrent.TimeUnit;

public class TEST {
	
	public static void main(String[] args) {
		int x = 1000;
		long time1 = System.nanoTime();
		for(int i=0;i<10000000;i++){
			x *= 2;
		}
		System.out.println(TimeUnit.MICROSECONDS.convert(System.nanoTime()-time1,TimeUnit.NANOSECONDS));
		
		long time2 = System.nanoTime();
		for(int i=0;i<10000000;i++){
			x *= 3;
		}
		System.out.println(TimeUnit.MICROSECONDS.convert(System.nanoTime()-time2,TimeUnit.NANOSECONDS));
		
		long time3 = System.nanoTime();
		for(int i=0;i<10000000;i++){
			x *= 4;
		}
		System.out.println(TimeUnit.MICROSECONDS.convert(System.nanoTime()-time3,TimeUnit.NANOSECONDS));
		
		int y =9;
		System.out.println(y<<1);
	}
	
}