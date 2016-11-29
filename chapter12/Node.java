package chapter12;
/**
 * this is an element used in the structure BinarySearchTree
 * @author 建苍
 *
 */
public class Node {
	
	private int key;
	private Object value;
	public Node left;
	public Node right;
	//除了root的parent以外，其他都应该存在。
	public Node parent;
	
	public Node(int key, Object value) {
		this.key = key;
		this.value = value;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	public int getKey() {
		return key;
	}
	@Override
	public String toString() {		
		if(value!=null&&value instanceof Node){
			Node d = (Node)value;
			return "Node [key=" + key + ", value=" + d.superToString() + "]";
		}
		return "Node [key=" + key + ", value=" + value + "]";
	}
	/**
	 * used to call the super toString()
	 * @return
	 */
	public String superToString(){
		return super.toString();
	}
}
