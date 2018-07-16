// PaTextFileDemo1.java
import java.io.*;

class PaTextFileDemo1{

	public final static String CHAR_SET = "utf-8";

	public static void main(String[] args){

		System.out.println( readTextFile("PaTextFileDemo1.java") );
	}

	public static String readTextFile(String strFile){
		String strContent = null;
		try{
			File file = new File(strFile);
			byte[] cache = new byte[ (int)(file.length()) ];
			FileInputStream fis = new FileInputStream(file);
			fis.read(cache);
			fis.close();
			strContent = new String(cache, CHAR_SET);
		}catch(Exception ex){}
		return strContent;
	}

	public static boolean writeTextFile(String strFile, String strContent){
		File file = new File(strFile);
		try(FileOutputStream fos = new FileOutputStream(file)){
			if(!file.exists()){ file.createNewFile(); }
			if(strContent != null){
				fos.write( strContent.getBytes(CHAR_SET) );
			}
			fos.flush();
			fos.close();
		}catch(IOException ex){
			return false;
		}
		return true;
	}
}
