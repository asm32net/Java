// JavaCarsarDemo1.java

import java.util.*;

class JavaCarsarDemo1{
	public static final HashMap<Character, Character> encMap = new HashMap<Character, Character>(){{
		for(int i = 0; i < 26; i++){
			int enc = (i + 3) % 26;
			put(new Character((char)(97 + i)), new Character((char)(97 + enc)));
			put(new Character((char)(65 + i)), new Character((char)(65 + enc)));
		}
	}};

	public static final HashMap<Character, Character> decMap = new HashMap<Character, Character>(){{
		for(int i = 0; i < 26; i++){
			int dec = (i + 23) % 26;
			put(new Character((char)(97 + i)), new Character((char)(97 + dec)));
			put(new Character((char)(65 + i)), new Character((char)(65 + dec)));
		}
	}};

	public static void main(String[] args){
		String strSource = "JavaCarsarDemo1.java (JAVA实现caesar凯撒加密算法)";
		String strEncrypt = carsarEncode(strSource);
		String strDecrypt = carsarDecode(strEncrypt);
		System.out.println( "strSource:\n\t" + strSource );
		System.out.println( "strEncrypt:\n\t" + strEncrypt );
		System.out.println( "strDecrypt:\n\t" + strDecrypt );
	}

	public static String carsarEncode(String s){
		if(s == null) return null;
		StringBuilder sb = new StringBuilder();
		for(int i = 0, l = s.length(); i < l; i++){
			char ch = s.charAt(i);
			if(encMap.containsKey(ch)){
				sb.append( encMap.get(ch) );
			}else{
				sb.append( ch );
			}
		}
		return sb.toString();
	}

	public static String carsarDecode(String s){
		if(s == null) return null;
		StringBuilder sb = new StringBuilder();
		for(int i = 0, l = s.length(); i < l; i++){
			char ch = s.charAt(i);
			if(decMap.containsKey(ch)){
				sb.append( decMap.get(ch) );
			}else{
				sb.append( ch );
			}
		}
		return sb.toString();
	}
}

/*
strSource:
	JavaCarsarDemo1.java (JAVA实现caesar凯撒加密算法)
strEncrypt:
	MdydFduvduGhpr1.mdyd (MDYD实现fdhvdu凯撒加密算法)
strDecrypt:
	JavaCarsarDemo1.java (JAVA实现caesar凯撒加密算法)
*/

/*
JAVA实现caesar凯撒加密算法

更新时间：2014年01月11日 15:37:46

Carsar加密算法是最简单的加密算法，原理是把一个字母在字母表中移动相应的位置，比如输入a，将其移动3位，经过Caesar加密后输出的d，位置可以循环移动，输入x,则输出a
*/