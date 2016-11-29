package chapter10;
/**
 * This is a implementation of queue based on array
 * basic operation : enqueue,dequeue
 * 类似于一个闭环，tail在前，head在后，同方向进行指针的移动。
 * 注意：为了处理边界问题，我们让一个N元数组，只能存放N-1个元素！！！
 * 
 * @author 建苍
 *
 */
public class Queue {
	public static void main(String[] args) throws Exception{
		Queue que = new Queue();
		for(int i=0;i<20;i++){
			que.enqueue(i);
		}
		for(int i =0;i<20;i++){
			System.out.println(que.dequeue());
		}
		que.enqueue(4);
	}
	/**
	 * capicity = 20;
	 */
	private static Object[] queue = new Object[21];
	private static int head = -1;
	private static int tail = 0;
	
	public void enqueue(Object x) throws Exception{
		queue[tail] = x;
		tail = (++tail)%queue.length;
		if(flow()){
			throw new Exception("Over Flow");
		}
		//为了保证初始化的正确性
		if(isEmpty()){
			head = 0;
		}
	}
	
	public Object dequeue() throws Exception{
		if(isEmpty()){
			throw new Exception("Under Flow");
		}
		if(flow()){
			throw new Exception("Under Flow");
		}
		Object o = queue[head];
		head = (++head)%queue.length;
		return o;
	}
	
	/**
	 * Coincidentally over flow and under flow happen at the same condition
	 * 判断的依据是 两者相等，而head 指向的是有元素的，tail指向的是空的，
	 * 要确保他们之间有最小为1的差（空的一格就是tail指向的位置）
	 * @return
	 */
	public static boolean flow(){
		if(tail == head){
			return true;
		}else{
			return false;
		}
	}
	public static boolean isEmpty(){
		if(head == -1){
			return true;
		}else{
			return false;
		}
	}
//	public static boolean underFlow(){
//		if(tail==head){
//			return true;
//		}else{
//			return false;
//		}
//	}
}
