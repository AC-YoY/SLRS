package chapter10;
/**
 * a implementation of hashmap
 * (I don't really sure whether this class can be called a "hash" map) 
 * 
 * forbid null element && duplication conditions are not considered
 * 
 * @author 建苍
 *
 */
public class HashMap_Userdefined {
	
	public static void main(String[] args) {
		HashMap_Userdefined hmu = new HashMap_Userdefined();
		hmu.put(2, "Hehe");
		System.out.println(hmu.get(2));
		hmu.put(130,"haha");
		System.out.println(hmu.get(2));
//		这个2和130是不一样的key，但是却被影响了，
//		显然他们在hashMap内部维护的时候是存放在同一个地址中的
		hmu.remove(130);//此处没有操作发生
		System.out.println(hmu.get(2));
		hmu.remove(2);
//		删除之后就无法查找了
//		System.out.println(hmu.get(2));
	}
	/**
	 * 我不知道将内部维护的数组size定义为2^7幂是不是有什么特别的作用
	 */
	private static final int ENTRY_SIZE = 128;
	private Entry[] entries;
	public HashMap_Userdefined() {
		entries = new Entry[ENTRY_SIZE];
//		主动为数组赋初值也没有必要，java有自动初始化
//		for(int i=0;i<ENTRY_SIZE;i++){
//			entries[i] = null;
//		}
	}
	
//	接下来是几个basic operations
	/**
	 * 符合使用者的要求，并不去使用一个entry,而是直接键值对
	 * @param key
	 * @param value
	 */
	public void put(int key,Object value){
		//这一步的index计算,就是一个division hash,所以，大概还是可以算作一个hashmap的。。
		int index = key % ENTRY_SIZE;
		if(entries[index]==null){
			entries[index] = new Entry(key,value);
		}else{
			Entry e = entries[index];
			if(!e.getValue().equals(value)){
				e.setValue(value);
			}
		}
	}
	/**
	 * 根据键来查找元素，
	 * @param key
	 * @return
	 */
	public Object get(int key){
		int index = key % ENTRY_SIZE;
		Entry e = entries[index];
		if(e.getKey() == key){
			return e.getValue();
		}
		return null;
	}
	/**
	 * 删除元素，要考虑删除元素之后，
	 * 如何处理查找元素的问题，(使用DELETED Notation)
	 * @param key
	 */
	public void remove(int key){
		int index = key % ENTRY_SIZE;
		if(entries[index].getKey() == key){
			entries[index] = null;
		}
	}
}