import java.io.File;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PaListFolder2017July {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
		String strFolder = "./src/";
		File folder = new File(strFolder);
		File[] files = folder.listFiles();
		for(File f : files){
			StringBuilder sb = new StringBuilder();
			sb.append( sdf.format(new Date(f.lastModified()) ) );
			sb.append(" ");
			boolean isDirectory = f.isDirectory();
			sb.append(isDirectory ? 'd' : '-');
			sb.append(f.canRead() ? 'r' : '-');
			sb.append(f.canWrite() ? 'w' : '-');
			sb.append(f.canExecute()? 'x' : '-');
			sb.append(" ");
			if(isDirectory){
				sb.append("        <dir>");
			}else{
				sb.append(String.format("%13s", NumberFormat.getInstance().format(f.length())));
			}
			sb.append(" ");
			sb.append(f.getName());

			System.out.println(sb.toString());
		}
	}

}
