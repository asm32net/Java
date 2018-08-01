import java.io.File;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PaListFiles2017Demo1 {

//	public static String longToDateString(long ts){
//		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(ts));
//	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String path = "./src/";
		File dir = new File(path);
		File[] files = dir.listFiles();
		for(File f : files){
			StringBuilder sb = new StringBuilder();
			boolean isDirectory = f.isDirectory();
			sb.append( sdf.format(f.lastModified()) );
			sb.append("  ");
			sb.append( isDirectory ? 'd' : '-');
			sb.append( f.canRead() ? 'r' : '-');
			sb.append( f.canWrite() ? 'w' : '-');
			sb.append( f.canExecute() ? 'x' : '-');
			sb.append( " ");
			if(isDirectory){
				sb.append( "     <dir>" );
			}else{
				sb.append( String.format("%10s", NumberFormat.getInstance().format(f.length())) );
			}
			sb.append("  ");
			sb.append(f.getName());
			
			System.out.println(sb.toString() );
		}
		int i = java.lang.Integer.MAX_VALUE;
		for(i=-1;i!=0;i++){
			System.out.println(i);
		}
	}
}
