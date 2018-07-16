// JavaAesDemo1.java

import java.io.UnsupportedEncodingException;
import java.security.*;
import javax.crypto.spec.*;
import javax.crypto.*;

import java.util.Base64;

class JavaAesDemo1{
	/**
	 * AES加密字符串
	 * 
	 * @param content
	 *			需要被加密的字符串
	 * @param password
	 *			加密需要的密码
	 * @return 密文
	 */
	public static byte[] encrypt(String content, String password) {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");// 创建AES的Key生产者

			kgen.init(128, new SecureRandom(password.getBytes()));// 利用用户密码作为随机数初始化出
			// 128位的key生产者
			// 加密没关系，SecureRandom是生成安全随机数序列，password.getBytes()是种子，只要种子相同，序列就一样，所以解密只要有password就行

			SecretKey secretKey = kgen.generateKey();// 根据用户密码，生成一个密钥

			byte[] enCodeFormat = secretKey.getEncoded();// 返回基本编码格式的密钥，如果此密钥不支持编码，则返回
															// null。

			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");// 转换为AES专用密钥

			Cipher cipher = Cipher.getInstance("AES");// 创建密码器

			byte[] byteContent = content.getBytes("utf-8");

			cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化为加密模式的密码器

			byte[] result = cipher.doFinal(byteContent);// 加密

			return result;

		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 解密AES加密过的字符串
	 * 
	 * @param content
	 *			AES加密过过的内容
	 * @param password
	 *			加密时的密码
	 * @return 明文
	 */
	public static byte[] decrypt(byte[] content, String password) {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");// 创建AES的Key生产者
			kgen.init(128, new SecureRandom(password.getBytes()));
			SecretKey secretKey = kgen.generateKey();// 根据用户密码，生成一个密钥
			byte[] enCodeFormat = secretKey.getEncoded();// 返回基本编码格式的密钥
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");// 转换为AES专用密钥
			Cipher cipher = Cipher.getInstance("AES");// 创建密码器
			cipher.init(Cipher.DECRYPT_MODE, key);// 初始化为解密模式的密码器
			byte[] result = cipher.doFinal(content);  
			return result; // 明文   
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {

		final Base64.Encoder b64encoder = Base64.getEncoder();	// Java 1.8 之后支持

		String content = "Hello aes ! 老子说:“上善若水”，“水善利万物而不争，处众人之所恶，故几于道”。这里实际说的是做人的方法，即做人应如水，水滋润万物，但从不与万物争高下，这样的品格才最接近道。\n\n“厚德载物”语出《周易》：“君子以厚德载物。”意思是说，以深厚的德泽育人利物，今多用来指以崇高的道德、博大精深的学识培育学子成才。清华大学的校训就是“自强不息，厚德载物”。";
		String password = "123";
		System.out.println("加密之前：" + content);

		try{
			// 加密
			byte[] encrypt = JavaAesDemo1.encrypt(content, password);
			// System.out.println("加密后的内容：" + new String(encrypt));
			System.out.println("加密后的内容：" + b64encoder.encodeToString(encrypt));
			
			// 解密
			byte[] decrypt = JavaAesDemo1.decrypt(encrypt, password);
			System.out.println("解密后的内容：" + new String(decrypt, "utf-8"));
		}catch(Exception ex){
			System.out.println("Exception: " + ex.getMessage());
		}
	}
}

/*
加密之前：Hello aes ! 老子说:“上善若水”，“水善利万物而不争，处众人之所恶，故
几于道”。这里实际说的是做人的方法，即做人应如水，水滋润万物，但从不与万物争高下
，这样的品格才最接近道。

“厚德载物”语出《周易》：“君子以厚德载物。”意思是说，以深厚的德泽育人利物，今
多用来指以崇高的道德、博大精深的学识培育学子成才。清华大学的校训就是“自强不息，
厚德载物”。
加密后的内容：c51rmDh3Oo+Ln0heK/alePT8z6UKXplapeiuWHnDLd+v7d8c3X9wJV6bwtl2EQAwJH
byxLOvxrwb7zMOdnWtlN8QjkmVXI5k6u1jajk6WePznZsbyzWPsZbIZMtRSdWq/xg5YqOjDMBZKGjPUL
HgrutxN+lylr/tUa59GZTTM4dK+5VjkTi9BdRY4OcRxWIivVw5oGsn8i+PzREU5fnV6vxVnZrXmvscp3
3eYMcPnsts2Q+9wradFdLWgTaTUFxodUoBxzSc9Jwv6a0bMGn3Za4S8LUMfAgOWuwUjSD2XHMQRDylzl
Y5w9+RlDTrDoBtnaPJo1T0mzP9Qd6pW9kv1t5ImyG5Q8Qf7/M39YzAyU/ddtYYt4n3TVmWxld2IjPkHr
mSGGXY2MY/Bh++HzSWVTP9fAhq9nzTdK5PdwLyGjhU/sg6Wr41Drk7Apac+k6ttlB9+mjJomJu6azXZp
2QtEC7pwesp0E4fOZwfqUzrV1GyP2e4f+xQ50WRjtyLfGfSlpqhrpYKUMBq/EsC8FSPpklgi2o246zJt
vfBitPyEgEtzjL45esp9cgos1LZac+tBMp9TE1YdZqhphScP2I08fVwPo5Tw9tcfItgMHPGMYXouzdkk
R0sDETZRMoqFIx+X2aHYXsykw7Da2x6R6lgE/YpXXOjLtFT39nHGum7h4LTTIaniojNwls9nnMtrzy
解密后的内容：Hello aes ! 老子说:“上善若水”，“水善利万物而不争，处众人之所恶
，故几于道”。这里实际说的是做人的方法，即做人应如水，水滋润万物，但从不与万物争
高下，这样的品格才最接近道。
*/