package chapter04;

import java.util.Hashtable;
/**
 * how to find the first nonrepeated char in the string.
 * Method One use hashMap.
 * Method Two use array. 
 * 
 * @author 建苍
 *
 */
public class NonReaptedString {

	public static void main(String[] args) {
		String str = "SADSASAXZDECSAFVAVRRTTASDSAVFBTBSAVBRAACXMYURTV";
		System.out.println(findFirstNonRepeated(str));
		System.out.println(findFirstNonreaptedChar(str));
		System.out.println(findFirstNonreaptedChar2(str));
		
	}

	// returns the first non-repeated Character in a string
	public static Character findFirstNonRepeated(String input) {
		// create a new hashtable:
		Hashtable hashChar = new Hashtable();
		int j, strLength;
		Character chr;
		Integer intgr;

		strLength = input.length();

		for (j = 0; j < strLength; j++) {
			chr = new Character(input.charAt(j));
			intgr = (Integer) hashChar.get(chr);
			if (intgr == null) {
				hashChar.put(chr, new Integer(1));
			} else {
				hashChar.put(chr, new Integer(intgr.intValue() + 1));
			}
		}
		/*
		 * go through hashtable and search for the first non-repeated char:
		 */
		for (j = 0; j < strLength; j++) {
			chr = new Character(input.charAt(j));
			if (((Integer) hashChar.get(chr)).intValue() == 1){
				return chr;
			}
		}
		/*
		 * this only returns if the loop above doesn't find a non-repeated
		 * character.
		 */
		return null;
	}
	//现在都会自动拆装包了好嘛
	public static Character findFirstNonreaptedChar(String str){
		Hashtable<Character,Integer> hasht = new Hashtable<Character,Integer>();
		//I'm just a temp parameter
		Character chs;
		Integer inte;
		//插入hashTable的逻辑判断完了
		for(int i=0;i<str.length();i++){
			inte = hasht.get(str.charAt(i));
			if(inte == null){
				hasht.put(str.charAt(i), 1);
			}else{
				hasht.put(str.charAt(i),inte+1);
			}
		}
		//然后循环一边看看是不是有唯一值
		for(int i=0;i<str.length();i++){
			if(hasht.get(str.charAt(i)) == 1){
				return str.charAt(i);
			}			
		}
		return null;
	}

	//biubility的来了
	public static Character findFirstNonreaptedChar2(String str){
		Hashtable<Character,Object> ht = new Hashtable<Character,Object>();
		Object o1 = new Object();
		Object o2 = new Object();
		Character c;
		for(int i=0;i<str.length();i++){
			c = str.charAt(i);
			Object o = ht.get(c);
			if(o == null){
				ht.put(c, o1);
			}else if(o == o1){
				ht.put(c, o2);
			}
			//超过两次不去乱他
		}
		//看结果
		for(int i=0;i<str.length();i++){
			c = str.charAt(i);
			if(ht.get(c)==o1){
				return c;
			}
		}
		return null;
	}
}
