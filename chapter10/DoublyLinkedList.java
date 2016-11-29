package chapter10;
/**
 * 实现一个 双向 链表
 *  prev/next : 都是放的Element的位置
 *  key: 需要存放的数据，或者卫星数据
 *  
 * @author 建苍
 *
 */
public class DoublyLinkedList {
	
	public static void main(String[] args) {
		DoublyLinkedList dll = new DoublyLinkedList();
		for(int i=0;i<20;i++){
			dll.insert(i);
		}
		DoublyLinkedList.Element e15 = dll.search(15);
		System.out.println(e15);
		
		dll.delete(e15);
		System.out.println(dll.search(15));
	}
	
	//这个例子不使用sentinel，使用null代表NIL
	public Element head = null;
//	public Element[] list = null;
//	public DoublyLinkedList(int size) {
//		list = new Element[size];
//	}
//	如果有空参的constructor，那么必须要有一个固定的长度
//	public DoublyLinkedList(){
//		list = new Element[20];
//	}
	
	/**
	 * 顾名思义 just as its name implies
	 * @author 建苍
	 *
	 */
	class Element{
		public Element prev = null;
		public Object key = null;
		public Element next = null;
		public Element(Element prev, Object key, Element next) {
			this.prev = prev;
			this.key = key;
			this.next = next;
		}
		//默认构造前后指针都为null
		public Element(Object key) {
			this.key = key;
		}
		public Element(){}
		
		@Override
		public String toString() {
			return "Element:\n[prev=" + prev.toString2() + ",\nkey=" + key + ",\nnext=" + next.toString2()
					+ "]";
		}
		//完美显示自己对象的地址
		public String toString2(){
			return super.toString();
		}
		
		
	}
	/**
	 * 需要将要插入的key包装成一个element对象
	 * @param x 需要插入的元素
	 */
	public void insert(Object x){
		Element e = new Element(x);
		e.next = head;
		if(head != null){
			head.prev = e;
		}
		//更换在head的元素
		head = e;
	}
	/**
	 * 查找key对应的Element
	 * @param x
	 */
	public Element search(Object x){
		Element e = head;
		while(e!=null && e.key!=x){
			e = e.next;
		}
		return e;
	}
	/**
	 * 删除
	 * @param x
	 */
	public void delete(Element x){
		if(x.prev != null){
			x.prev.next = x.next;
		}else{
			head = x.next;
		}
		if(x.next != null){
			x.next.prev = x.prev;
		}
	}
	
}
