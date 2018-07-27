// JavaHexStringDemo1.java
import java.util.HashMap;

class JavaHexStringDemo1{
	public static final String CHAR_SET = "utf-8";
	public static final String strHexMap = "0123456789ABCDEF";
	public static String HexStringEncode(String s) throws Exception{
		StringBuilder sb = new StringBuilder();
		int nCount = strHexMap.length();
		HashMap<Integer,Character> map = new HashMap<Integer,Character>();
		for(int i = 0; i < nCount; i++){
			map.put(i, strHexMap.charAt(i));
		}
		byte[] data = s.getBytes(CHAR_SET);
		nCount = data.length;
		for(int i = 0; i < nCount; i++){
			int ch = data[i] & 255;
			sb.append(map.get(ch >> 4)).append(map.get(ch & 15));
		}
		return sb.toString();
	}

	public static String HexStringDecode(String s) throws Exception{
		int nCount = strHexMap.length();
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		for(int i = 0; i < nCount; i++){
			map.put(strHexMap.charAt(i), i);
		}
		nCount = s.length();
		byte[] cache = new byte[nCount / 2];
		for(int i = 0, n = 0; i < nCount; i += 2){
			cache[n++] = (byte)((map.get(s.charAt(i)) << 4) + map.get(s.charAt(i + 1)));
		}
		return new String(cache, CHAR_SET);
	}

	public static void main(String[] args) throws Exception{
		String strSource = "JavaHexStringDemo1.java\n程序中书写着所见所闻所感，编译着心中的万水千山。";
		String strEncrypt = HexStringEncode(strSource);
		String strDecrypt = HexStringDecode(strEncrypt);

		System.out.println("strSource:\n\t" + strSource);
		System.out.println("strEncrypt:\n\t" + strEncrypt);
		System.out.println("strDecrypt:\n\t" + strDecrypt);
	}
}