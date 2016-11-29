package chapter10;
/**
 * just as its name implies,
 * it's a hash map which use open addressing-linear probe to solve duplication condition
 * 
 * 本hash map 中使用null来表示NIL
 * @author 建苍
 *
 */
public class HashMap_LinearProbing {
	public static void main(String[] args) {
		HashMap_LinearProbing hmlp = new HashMap_LinearProbing();
		hmlp.put(1, "hehe");
		System.out.println(hmlp.get(1));
	}
	
	private Entry[] T;
	private static final int ENTRY_SIZE = 128;
	public HashMap_LinearProbing() {
		T = new Entry[ENTRY_SIZE];
	}
	/**
	 * 1.寻找空位  
	 * 2.寻找deleted位
	 * 3.与linear probe算法相关，只能循环一个m次
	 * 4.在填充的时候，有null就填充null，有deleted在起那就填充deleted-->然而，必然是deleted在前面
	 * 
	 * @param key
	 * @param value
	 */
	public void put(int key,Object value){
		int hash = key%ENTRY_SIZE;
		int initialHash = -1;
		int indexOfDeleted = -1;
		//循环找出hash的位置，以及indexOfDeleted的位置
		while((T[hash]!=null || T[hash]==Deleted.getInstance())
				&&initialHash!=hash//循环不得超过一个m
				&&T[hash].getKey()!=key){//查找的位置不能重复
			if(initialHash == -1){//仅仅赋一次初始值
				initialHash = hash;
			}
			if(indexOfDeleted==-1&&T[hash]==Deleted.getInstance()){
				indexOfDeleted = hash;
			}
			//重新计算hash数值
			hash = (++hash)%ENTRY_SIZE;
		}
		if( (T[hash]==null||hash==initialHash)//后一个条件对应的是，找了一遍只有deleted元素，没有空位了
				&&indexOfDeleted!=-1){
			//这两种情况无论哪种，肯定都是deleted的位置在前面
			T[indexOfDeleted] = new Entry(key,value);
		}else if(hash != initialHash){//循环超过一边的不可能是重复键
			if(T[hash]!=null&&T[hash]!=Deleted.getInstance()&&T[hash].getKey()==key){
				T[hash].setValue(value);
			}else{
				T[hash] = new Entry(key,value);
			}
		}
	}
	/**
	 * 查找基本元素，
	 * 一次hash之后再其中遍历i
	 * 
	 * @param key
	 * @return
	 */
	public Object get(int key){
		int hash = key%ENTRY_SIZE;
		int initialHash = -1;
		while(T[hash]!=null&&initialHash!=hash){
			if(initialHash==-1){
				initialHash = hash;
			}
			if(T[hash]!=Deleted.getInstance()){
				if(T[hash].getKey()==key){
					return T[hash].getValue();
				}
			}
			hash = (++hash)%ENTRY_SIZE;
		}
		return null;
	}
	/**
	 * 其实跟get或者说search算法一样，只是去其中寻找一个
	 * element所在的位置，将其替换成deleted元素.
	 * @param key
	 */
	public void remove(int key){
		int hash = key%ENTRY_SIZE;
		int initialHash = -1;
		while(T[hash]!=null&&initialHash!=hash){
			if(initialHash==-1){
				initialHash = hash;
			}
			if(T[hash]!=Deleted.getInstance()){
				if(T[hash].getKey()==key){
					T[hash] = Deleted.getInstance();
				}
			}
			hash = (++hash)%ENTRY_SIZE;
		}
	}
	
}
/**
 * 用来表示空值的一个Entry对象,区别于NIL
 * @author 建苍
 *
 */
class Deleted extends Entry{
	private static Deleted deleted;
	private Deleted() {
		super(-1, -1);
	}
	public static Deleted getInstance(){
		if(deleted == null){
			deleted = new Deleted();
		}
		return deleted;
	}
}