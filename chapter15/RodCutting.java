package chapter15;

import java.util.Arrays;

/**
 * 
 * 
 */
public class RodCutting {
	public static void main(String[] args) {
		RodCutting rc = new RodCutting();
//		rc.solve(10);
//		rc.cutRod(10);
//		System.out.println(Arrays.toString(r)+"\n"+Arrays.toString(s));
		
		rc.cutDown(5);
		
//		rc.solution(10);
//		System.out.println(Arrays.toString(r)+"\n"+Arrays.toString(s));
	}

	/**
	 */
	private static final int[] p = { 1, 5, 8, 9, 10, 17, 17, 20, 24, 30 };
	/**
	 */
	private static int[] r;
	/**
	 */
	private static int[] s;

	
//	public void solution(int len){
//		r = new int[len+1];
//		s = new int[len+1];
//		for(int j=1;j<r.length;j++){
//			int temp = Integer.MIN_VALUE;
//			for(int i=1;i<=j;i++){
//				if(temp < p[i-1]+r[j-i]){
//					temp = p[i-1] +r[j-i];
//					s[j] = i;
//				}
//			}
//			r[j] = temp;
//		}
//	}
	
	
	
	/**
	 * method with record index
	 * @param len
	 */
	public void solve(int len) {
		r = new int[len+1];
		s = new int[len+1];

		for (int i = 1; i < len+1; i++) {
			int q = Integer.MIN_VALUE;
			for (int j = 1; j < i+1; j++) {
				if (q < p[j-1] + r[i - j]) {
					q = p[j-1] + r[i - j];
					s[i] = j;
				}
				r[i] = q;
			}
		}
		
	}

	/**
	 * traversal all part of Rod which cut by an optimal algorithm
	 * @param len
	 */
	public void cutDown(int len) {
		solve(len);
		while (len > 0) {
			System.out.println(s[len]);
			len = len - s[len];
		}
	}
	
	/**
	 * Method without recording index
	 * Test OK
	 * @param len
	 */
	public void cutRod(int len){
		r = new int[len+1];
		//r[0] == 0,so i don't repeat this
		for(int i=1;i<r.length;i++){
			int q = Integer.MIN_VALUE;
			for(int j=1;j<i+1;j++){
				q = Math.max(q, p[j-1]+r[i-j]);
			}
			r[i] = q;
		}
	}

}
