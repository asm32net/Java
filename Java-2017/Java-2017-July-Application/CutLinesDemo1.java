import java.util.ArrayList;

public class CutLinesDemo1 {

	public CutLinesDemo1(){
		//String source = "国网湖南省电力公司工程及服务项目2015年第一批非招标采购公开竞争谈判会议文件（四）";
		String source = "  国网湖南省电力公司工程及服务项目2015年第二批零星工程和服务项目招标采购会议文件（五）";
		
		System.out.println( source + " len=" + source.length());
		String[] lines = cutLines(source, 42);
		log("lines.length=" + lines.length);
		for(int i=0, l = lines.length; i < l; i++){
			System.out.println(lines[i] + "\t" + lines[i].length());
		}
	}
	
	private void log(String s){
		System.out.println(s);
	}

//	private void log(int i){
//		System.out.println(i);
//	}
	
	public String[] cutLines(String source, int nCountInLine){
		ArrayList<String> al = new ArrayList<String>();
		if( source != null){
			int start = 0, ls = 0, l = source.length(); // ls: length of string
			for(int i = 0; i < l; i++){
				int ch = source.charAt(i); // ASC code
				int chl = ch > 127 ? 2 : 1; // chl: char length
				if( ls + chl > nCountInLine){
					al.add( source.substring(start, i) );
					start = i;
					ls = 0;
				}
				ls += chl;
			}
			if(ls > 0){
				al.add( source.substring(start, l) );
			}
		}
		int als = al.size();
		String[] lines = new String[ als ];
		for(int i = 0; i < als; i++){
			lines[i] = (String)al.get(i);
		}
		return lines;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new CutLinesDemo1();
	}

}
