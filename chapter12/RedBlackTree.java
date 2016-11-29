package chapter12;
/**
 * Red Black Binary Search Tree constituted by RBNode
 * 一棵平衡树的实现
 * 
 * @author 建苍
 *
 */
public class RedBlackTree {
	private static final boolean RED = true;
	private static final boolean BLACK = false;
	//这样子的话每次构造的一棵树的nil都是不一样的
	private static final RBNode Nil = new RBNode(Integer.MIN_VALUE,-1,BLACK);
	//！！！！！我不知道这样会发生什么
	static{
		Nil.parent = Nil;
		Nil.left = Nil;
		Nil.right = Nil;
	}
	public RBNode root = Nil;
	
	public RedBlackTree() {
	}
	public RedBlackTree(RBNode root) {
		this.root = root;
//		root.parent = Nil;
//		root.left = Nil;
//		root.right = Nil;
	}
	public RedBlackTree(int key,Object o) {
		root = new RBNode(key,o);
//		root.parent = Nil;
//		root.left = Nil;
//		root.right = Nil;
	}
	/**
	 * make sure n and n.right all exist,otherwise nullPointerException will be thrown
	 * @param n
	 */
	public void leftRotate(RBNode n){
		//我不对传入的参数n进行保证，他是不是null，交给调用的时候去判断
		//这里的两个exception是用来判断旋转的两个对象是否是Nil
		if(n==Nil){throw new RuntimeException("n is Nil");}
		RBNode x = n.right;
		if(x==Nil){throw new RuntimeException("n's right is Nil");}
		//处理x.left与n之间的link
		RBNode b = x.left;
		n.right = b;
		if(b!=Nil){
			b.parent = n;
		}
		//处理x与n.parent之间的link
		x.parent = n.parent;
		if(n.parent==Nil){
			root = x;
		}else if(n.parent.left==n){
			n.parent.left = x;
		}else{
			n.parent.right = x;
		}
		x.left = n;
		n.parent = x;
	}
	public void rightRotate(RBNode n){
		if(n==Nil){throw new RuntimeException("n is Nil");}
		RBNode x = n.left;
		if(x==Nil){throw new RuntimeException("n's left is Nil");}
		//处理x.right与n之间的link
		RBNode b = x.right;
		n.left = b;
		if(b!=Nil){
			b.parent = n;
		}
		//处理x与n.parent之间的link
		x.parent = n.parent;
		if(n.parent==Nil){
			root=x;
		}else if(n.parent.left == n){
			n.parent.left = x;
		}else{
			n.parent.right = x;
		}
		x.right = n;
		n.parent = x;
	}
	/**
	 * 插入一个节点
	 * @param n
	 */
	public void insert(RBNode n){
		RBNode x = root;
		RBNode y = x;
		while(x != Nil){
			y = x;
			if(n.key < x.key){
				x=x.left;
			}else{
				x = x.right;
			}
		}
		n.parent = y;
		if(y==Nil){
			root = n;
		}else if(n.key<y.key){
			y.left = n;
		}else{
			y.right = n;
		}
		n.parent = y.parent;
		n.left = Nil;
		n.right = Nil;
		n.color = RED;
		insertFixUp(n);
	}
	/**
	 * First of all：Loop条件:x.p.color==RED
	 * 如果是黑色的则插入一个红色节点显然不会有任何异常
	 * 
	 * 之后的逻辑一共有6种：
	 * 根据x.p == x.p.p.left/right划分成两类
	 * 再细分成3类
	 * 
	 * 具体分析看p.179,非常清楚
	 * 
	 * @param x
	 */
	public void insertFixUp(RBNode n){
		while(n.parent.color == RED){
			//x.p==x.p.p.left
			if(n.parent == n.parent.parent.left){
				RBNode y = n.parent.parent.right;
				//condition 1
				if(y.color==RED){
					n.parent.color = BLACK;
					y.color = BLACK;
					n.parent.parent.color = RED;
					n = n.parent.parent;
				}//Condition 2: 前提uncle.color=black，再根据z和z.p位置分成两种情况
				else if(n==n.parent.right){
					n = n.parent;
					leftRotate(n);//lean to Condition 3
				}//Condition 3: 一定是最后一次操作
				else{
					n.parent.color = BLACK;
					n.parent.parent.color = RED;
					rightRotate(n.parent.parent);
				}
			}
			//x.p==x.p.p.right
			if(n.parent == n.parent.parent.right){
				RBNode y = n.parent.parent.left;
				//Condition 1
				if(y.color==RED){
					n.parent.color = BLACK;
					y.color = BLACK;
					n.parent.parent.color = RED;
					n = n.parent.parent;
				}else if(n==n.parent.right){
					n = n.parent;
					leftRotate(n);
				}else{
					n.parent.color = BLACK;
					n.parent.parent.color = RED;
					rightRotate(n.parent.parent);
				}
			}
		}
		root.color = BLACK;
	}
	/**
	 * 在删除擦操作中使用
	 * 帮助处理y与x.p之间的link关系
	 * !!注意：x,y都是可以为Nil的
	 * 
	 * @param x 被替换的节点
	 * @param y 要转移的节点
	 */
	public void transplant(RBNode x,RBNode y){
		if(x.parent==Nil){
			root = y;
		}else if(x==x.parent.left){
			x.parent.left=y;
		}else{
			x.parent.right=y;
		}
		y.parent = x.parent;
	}
	/**
	 * 注意与binary search tree的区别
	 * 被删除的节点的位置，以及颜色需要被记录!!!
	 * y -- > 被删除节点
	 * originalColor -- > y 的颜色
	 * x -- > 被迁移的子树,当有两个节点被移动时，记录的是比较靠近底层的节点
	 * @param n
	 */
	public void delete(RBNode n){
		RBNode y = n;
		boolean originalColor = y.color;
		RBNode x;
		if(n.left==Nil){
			x = n.right;
			transplant(n,n.right);
		}else if(n.right==Nil){
			x = n.left;
			transplant(n,n.left);
		}else{
			y = min(n.right);
			originalColor = y.color;
			//记录下y.right也就是
			x = y.right;
			if(y.parent == n){
				x.parent = y;//???????本来不就是么？
			}else{
				transplant(y,y.right);
				//迁移的是y右边子树，需要重建到x.right的link
				y.right = n.right;
				y.right.parent = y;
			}
			transplant(n,y);
			//处理y的左边link
			y.left = n.left;
			y.parent.parent = y;
			y.color = n.color;
		}
		if(originalColor==BLACK){
			deleteFixUp(x);
		}		
	}
	public void deleteFixUp(RBNode x) {
		
	}
	public RBNode min(RBNode n) {
		while(n.left!=Nil){
			n = n.left; 
		}
		return n;
	}
}
/**
 * red black tree's basic element
 * @author 建苍
 *
 */
class RBNode{
	public int key;
	private Object value;
	public RBNode parent;
	public RBNode left;
	public RBNode right;
	public boolean color;
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	public RBNode(int key, Object value) {
		this.key = key;
		this.value = value;
	}
	public RBNode(int key, Object value,boolean color) {
		this.key = key;
		this.value = value;
		this.color = color;
	}
	public RBNode(){}
}