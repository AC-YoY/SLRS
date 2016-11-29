package chapter10;
/**
 * This is a implementation of stack structure based on array
 * basic operation : push/pop
 * 
 * @author 建苍
 *
 */
public class Stack {
	public static void main(String[] args) throws Exception {
		Stack sk = new Stack();
		for(int i=0;i<20;i++){
			sk.push(i);
		}
		System.out.println(sk.pop());
	}
	
	private static Object[] stack = new Object[20];
	private static int top = -1;
	
	public void push(Object x) throws Exception{
		if(overFlow()){
			throw new Exception("OverFlow");
		}else{
			top++;
			stack[top] = x;
		}
		
	}
	
	public Object pop() throws Exception{
		if(isEmpty()){
			//栈下溢
			throw new Exception("UnderFlow");
		}else{
			top--;
			return stack[top+1];
		}
	}
	
	public static boolean isEmpty(){
		if(top == -1){
			return true;
		}else{
			return false;
		}
	}
	public static boolean overFlow(){
		if(top == stack.length -1){
			return true;
		}else{
			return false;
		}
	}
}
