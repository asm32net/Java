// JavaBase64Demo1.java
import javax.crypto.*;
import java.security.*;
import java.util.Base64;

class JavaBase64Demo1{
	public static final String _keyStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=";
	public static final Base64.Encoder b64encoder = Base64.getEncoder();	// Java 1.8 之后支持
	public static final Base64.Decoder b64decoder = Base64.getDecoder();	// Java 1.8 之后支持

	public static void main(String[] args) throws Exception{

		String strSource = "JavaBase64Demo1.java\n程序中书写着所见所闻所感，编译着心中的万水千山。";;
		byte[] data = JavaBase64Demo1.getBytes(strSource);

		String strStdEncrypt = JavaBase64Demo1.b64encoder.encodeToString( data );
		String strStdDecrypt = new String( JavaBase64Demo1.b64decoder.decode( strStdEncrypt ), "UTF-8" );
		String strUserEncrypt = JavaBase64Demo1.Base64Encode(strSource);

		log(strSource);

		log( "strStdEncrypt:\n" + strStdEncrypt );
		log( "strStdDecrypt:\n" + strStdDecrypt );

		log( "strUserEncrypt:\n" + strUserEncrypt );
	}

	public static byte[] getBytes(String s){
		try{
			return s.getBytes("UTF-8");
		}catch(Exception ex){}
		return null;
	}

	public static String Base64Encode(String s){
		byte[] data = getBytes(s);
		int nCount = data.length;
		byte chr1 = 0, chr2 = 0, chr3 = 0;
		int enc1 = 0, enc2 = 0, enc3 = 0, enc4 = 0;
		int i = 0;
		StringBuilder sb = new StringBuilder();
		while(i < nCount){
			chr1 = data[i++];
			enc1 = chr1 >> 2 & 63;
			if(i < nCount){
				chr2 = data[i++];
				enc2 = ((chr1 & 3) << 4) | ((chr2 & 0xf0) >> 4 & 15);
				if(i < nCount){
					chr3 = data[i++];
					enc3 = ((chr2 & 15) << 2) | ((chr3 & 0xc0) >> 6 & 3);
					enc4 = chr3 & 63;
				}else{
					enc3 = (chr2 & 15) << 2;
					enc4 = 64;
				}
			}else{
				enc2 = (chr1 & 3) << 4;
				enc3 = enc4 = 64;
			}
			sb.append(_keyStr.charAt(enc1));
			sb.append(_keyStr.charAt(enc2));
			sb.append(_keyStr.charAt(enc3));
			sb.append(_keyStr.charAt(enc4));
		}
		return sb.toString();
	}

	public static void log(Object o){
		System.out.println(o);
	}
}

/*
JavaBase64Demo1.java
程序中书写着所见所闻所感，编译着心中的万水千山。
strStdEncrypt:
SmF2YUJhc2U2NERlbW8xLmphdmEK56iL5bqP5Lit5Lmm5YaZ552A5omA6KeB5omA6Ze75omA5oSf77yM57yW6K+R552A5b+D5Lit55qE5LiH5rC05Y2D5bGx44CC
strStdDecrypt:
JavaBase64Demo1.java
程序中书写着所见所闻所感，编译着心中的万水千山。
strUserEncrypt:
SmF2YUJhc2U2NERlbW8xLmphdmEK56iL5bqP5Lit5Lmm5YaZ552A5omA6KeB5omA6Ze75omA5oSf77yM57yW6K+R552A5b+D5Lit55qE5LiH5rC05Y2D5bGx44CC

*/