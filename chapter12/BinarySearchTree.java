package chapter12;
/**
 * an implementation of BinarySearchTree
 * 
 *
 */
public class BinarySearchTree {
	
	public static void main(String[] args) {
		BinarySearchTree bst = new BinarySearchTree(100,"world");
		bst.display(bst.root);
		for(int i=0;i<200;i+=2){
			bst.insert(new Node(i,i));
		}
		bst.display(28);
	}
	
	public Node root = null;
	
	public BinarySearchTree(Node root) {
		this.root = root;
	}
	public BinarySearchTree(int key,Object o) {
		root = new Node(key,o); 
	}
	
	/**
	 * a method to display subtree of Node x
	 * @param x
	 */
	public void display(Node x){
		if(x != null){
			display(x.left);
			System.out.println(x);
			display(x.right);
		}
	}
	public void display(int key){
		Node n = this.search(key);
		this.display(n);
	}
	/**
	 * recursion method
	 * @param key
	 * @return
	 */
	public Node searchDontUse(int key){
		if(root==null||root.getKey()==key){
			return root;
		}
		if(key<root.getKey()){
			return search(root.left.getKey());			
		}else{
			return search(root.right.getKey());
		}
	}
	/**
	 * iteration Method//And this is a better method
	 * @param key
	 * @return
	 */
	public Node search(int key){
		Node x = root;
		while(x!=null&&x.getKey()!=key){
			if(key<x.getKey()){
				x = x.left;
			}else{
				x = x.right;
			}
		}
		return x;
	}
	/**
	 * @param x
	 * @return
	 */
	public static Node max(Node x){
		while(x.right!=null){
			x = x.right;
		}
		return x;
	}
	public static Node min(Node x){
		while(x.left!=null){
			x = x.left;
		}
		return x;
	}
	/**
	 * @param x
	 * @return
	 */
	public Node successor(Node x){
		if(x.right!=null){
			return min(x.right); 
		}
		Node y = x.parent;
		while(y!=null&&y.left!=x){
			x = y;
			y = y.parent;
		}
		return y;
	}
	/**
	 * @param x
	 */
	public void insert(Node x){
		Node n1 = null;
		Node n2 = root;
		while(n2!=null){
			n1 = n2;
			if(x.getKey()<n2.getKey()){
				n2 = n2.left;
			}else{
				n2 = n2.right;
			}
		}
		x.parent = n1;
		if(n1==null){
			root = x;
		}else if(x.getKey()<n1.getKey()){
			n1.left = x;
		}else{
			n1.right = x;
		}
	}
	/**
	 * 3 conditions:
	 * 1.deleted node do not has child
	 * 2.deleted node only has one child
	 * 3.deleted node has two children
	 * @param x
	 */
	public void delete(Node x){
		if(x.left==null){//1&2 has right child or do not have child
			this.transplant(x,x.right);
		}else if(x.right==null){//2 has left child
			this.transplant(x, x.left);
		}else{//3
			Node n = min(x.right);
			
//			//do not handle n's children
//			if(n.parent==x){
//				this.transplant(x, n);
//				n.left = x.left;
//				n.left.parent = n;
//			}else{//consider n's children
//				this.transplant(n, n.right);
//				this.transplant(x, n);
//				n.left = x.left;
//				n.left.parent = n;
//				n.right = x.right;
//				n.right.parent = n;
//			}
			
			//n isn't x's child
			if(n.parent!=x){
				this.transplant(n, n.right);
				n.right = x.right;
				n.right.parent = n;
			}//n is x's child
			this.transplant(x, n);
			n.left = x.left;
			n.left.parent = n;
		}
		
	}
	/**
	 * transplant Node target to origin's place,
	 * rebuild links
	 * 
	 * @param origin can't use null element
	 * @param target allow null
	 */
	public void transplant(Node origin,Node target){
		//rebuild parent to children's link
		if(origin.parent == null){
			root = target;
		}else if(origin.parent.left == origin){
			origin.parent.left = target;
		}else{
			origin.parent.right = target;
		}
		//rebuild children to parent's link
		if(target != null){
			target.parent = origin.parent;
		}
	}
	
}
//I do not decide to use it yet 
class NIL extends Node{
	private NIL nil;
	private NIL() {
		super(-1, -1);
	}
	public NIL getInstance(){
		if(nil==null){
			nil = new NIL();
		}
		return nil;
	}
}
