// https://www.cnblogs.com/qingergege/p/5768376.html

import java.io.*;
import java.util.zip.*;
//import org.apache.tools.zip.*;

class ZipCompress{
	private String sourceFileName;	//源文件（带压缩的文件或文件夹）
	private String zipFileName;		// 目的地Zip文件

//	/**
//     * 设置压缩编码，解决中文文件名乱码问题
//     */
//    private static final String encoding = System.getProperty("sun.jnu.encoding");
//
//    static {
//        /**
//         * 解压依据的编码是sun.zip.encoding 所以需要设置 解压编码
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

		// 创建zip输出流
		BufferedOutputStream bos = new BufferedOutputStream(zos);
		
		// 创建缓冲输出流
		File sourceFile = new File(sourceFileName);
		
		// 递归压缩
		compress(zos, bos, sourceFile, sourceFile.getName());

		bos.close();
		//zos.setEncoding("gbk");
		//zos.closeEntry();
		zos.close();
		// System.out.println("zip done.");
	}

	public void compress(ZipOutputStream zos, BufferedOutputStream bos, File sourceFile, String base) throws Exception{
		System.out.println(base);
		if(sourceFile.isDirectory()){	//如果路径为目录（文件夹）
			// 取出文件夹中的文件（或子文件夹）
			File[] files = sourceFile.listFiles();
			String sBase = base + File.separator;
			if(files.length==0){	//如果文件夹为空，则只需在目的地zip文件中写入一个目录进入点
				// System.out.println(sBase);
				zos.putNextEntry(new ZipEntry(sBase));
			}else{	// 如果文件夹不为空，则递归调用compress，文件夹中的每一个文件（或文件夹）进行压缩
				for(int i = 0, fl = files.length; i < fl; i++){
					compress(zos, bos, files[i], sBase + files[i].getName());
				}
			}
		}else{	// 如果不是目录（文件夹），即为文件，则先写入目录进入点，之后将文件写入zip文件中
			zos.putNextEntry(new ZipEntry(base));
			FileInputStream fis = new FileInputStream(sourceFile);
			BufferedInputStream bis = new BufferedInputStream(fis);
			
			// 将源文件写入到zip文件中
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