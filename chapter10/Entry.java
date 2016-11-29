package chapter10;
/**
 * Map.Entry
 * @author 建苍
 *
 */
public class Entry {
	private int key;
	private Object value;
	public Entry(int key, Object value) {
		super();
		this.key = key;
		this.value = value;
	}
	public int getKey() {
		return key;
	}
//	我觉得Key值在存入之后就不应该再去改变，whatever comment this method first
//	public void setKey(int key) {
//		this.key = key;
//	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
}
