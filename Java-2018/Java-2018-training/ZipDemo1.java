// https://www.cnblogs.com/qingergege/p/5768376.html

import java.io.*;
import java.util.zip.*;
//import org.apache.tools.zip.*;

class ZipCompress{
	private String sourceFileName;	//Դ�ļ�����ѹ�����ļ����ļ��У�
	private String zipFileName;		// Ŀ�ĵ�Zip�ļ�

//	/**
//     * ����ѹ�����룬��������ļ�����������
//     */
//    private static final String encoding = System.getProperty("sun.jnu.encoding");
//
//    static {
//        /**
//         * ��ѹ���ݵı�����sun.zip.encoding ������Ҫ���� ��ѹ����
//         */
//        System.setProperty("sun.zip.encoding", encoding);
//    }	

    public ZipCompress(String sourceFileName, String zipFileName){
		this.sourceFileName = sourceFileName;
		this.zipFileName = zipFileName;
	}
	
	public void zip() throws Exception{
		// System.out.println("zipping...");
		ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipFileName));

		// ����zip�����
		BufferedOutputStream bos = new BufferedOutputStream(zos);
		
		// �������������
		File sourceFile = new File(sourceFileName);
		
		// �ݹ�ѹ��
		compress(zos, bos, sourceFile, sourceFile.getName());

		bos.close();
		//zos.setEncoding("gbk");
		//zos.closeEntry();
		zos.close();
		// System.out.println("zip done.");
	}

	public void compress(ZipOutputStream zos, BufferedOutputStream bos, File sourceFile, String base) throws Exception{
		System.out.println(base);
		if(sourceFile.isDirectory()){	//���·��ΪĿ¼���ļ��У�
			// ȡ���ļ����е��ļ��������ļ��У�
			File[] files = sourceFile.listFiles();
			String sBase = base + File.separator;
			if(files.length==0){	//����ļ���Ϊ�գ���ֻ����Ŀ�ĵ�zip�ļ���д��һ��Ŀ¼�����
				// System.out.println(sBase);
				zos.putNextEntry(new ZipEntry(sBase));
			}else{	// ����ļ��в�Ϊ�գ���ݹ����compress���ļ����е�ÿһ���ļ������ļ��У�����ѹ��
				for(int i = 0, fl = files.length; i < fl; i++){
					compress(zos, bos, files[i], sBase + files[i].getName());
				}
			}
		}else{	// �������Ŀ¼���ļ��У�����Ϊ�ļ�������д��Ŀ¼����㣬֮���ļ�д��zip�ļ���
			zos.putNextEntry(new ZipEntry(base));
			FileInputStream fis = new FileInputStream(sourceFile);
			BufferedInputStream bis = new BufferedInputStream(fis);
			
			// ��Դ�ļ�д�뵽zip�ļ���
			//int data;
			//while((data = bis.read()) != -1){
			//	bos.write(data);
			//}
			int nCountRead;
			byte[] buff = new byte[1024];
			while((nCountRead = bis.read(buff)) != -1){
				bos.write(buff, 0, nCountRead);
			}
			bis.close();
			fis.close();

			bos.flush();
		}
	}
}

public class ZipDemo1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		String s = "E:\\Documents\\Desktop\\Asm32FF\\py\\web-trans.py";
		String s = "E:\\My Documents\\Desktop\\11";
		ZipCompress zc = new ZipCompress(s, s + ".zip");
		try{
			zc.zip();
			System.out.println("Done.");
		}catch(Exception ex){
			System.out.println("Exception:" + ex.getMessage());
			ex.printStackTrace();
		}
	}

}