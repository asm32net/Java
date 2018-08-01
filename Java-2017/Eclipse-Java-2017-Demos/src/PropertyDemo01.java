import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;

public class PropertyDemo01 {
	public static void main(String[] args) {
		Properties prop = new Properties();
		try{
			//��ȡ�����ļ�a.properties
			InputStream in = new BufferedInputStream (new FileInputStream("a.properties"));
			prop.load(in);     ///���������б�
			Iterator<String> it=prop.stringPropertyNames().iterator();
			while(it.hasNext()){
				String key=it.next();
				System.out.println(key+":"+prop.getProperty(key));
			}
			in.close();
			System.out.println("Read a.properties OK!");
		}catch(Exception e){
			System.out.println(e);
		}
		
		try{
			///�������Ե�b.properties�ļ�
			FileOutputStream oFile = new FileOutputStream("b.properties", true);//true��ʾ׷�Ӵ�
			prop.setProperty("phone", "10086");
			prop.store(oFile, "The New properties file");
			oFile.close();
			System.out.println("Write b.properties OK!");
		}catch(Exception e){
			System.out.println(e);
		}
	}

}
