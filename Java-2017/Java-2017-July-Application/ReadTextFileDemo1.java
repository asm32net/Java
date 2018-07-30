import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;

public class ReadTextFileDemo1 {
	private BigDecimal bd = new BigDecimal("1.23");

	public String readTextFile(String fileName){
		StringBuilder sb = new StringBuilder();
		try{
			String encoding = "gbk";
			File file = new File(fileName);
			if(file.exists() && file.isFile()){
				InputStreamReader isr = new InputStreamReader( new FileInputStream( file ), encoding );
				char[] buff = new char[512];
				int nCount = 0;
				while((nCount = isr.read(buff)) > 0){
					sb.append( buff, 0, nCount );
				}
				isr.close();
			}
		}catch(Exception e){
			e.printStackTrace();
			return "";
		}
		return sb.toString();
	}
	
	public ReadTextFileDemo1(){
		System.out.println( bd );
		System.out.println(readTextFile("D:\\Documents\\Desktop\\print-0815\\xml.txt.xml"));
//		System.out.println( readTextFile("D:\\Documents\\Desktop\\WebPrint\\jeap-inject-utils-note-20171011.txt") );
		System.out.println( readTextFile("D:\\Documents\\Desktop\\WebPrint\\付款凭证-(A4 纸张).xml") );
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ReadTextFileDemo1();

	}

}
