// SortTIOBEFileNameByDate.java
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SortTIOBEFileNameByDate {

	HashMap<String,Integer> dictMonth = new HashMap<String,Integer>(){{
		put("January", 1);
		put("February", 2);
		put("March", 3);
		put("April", 4);
		put("May", 5);
		put("June", 6);
		put("July", 7);
		put("August", 8);
		put("September", 9);
		put("October", 10);
		put("November", 11);
		put("December", 12);
	}};

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] A_strFiles = {
				"TIOBE Index for April 2018.html",
				"TIOBE Index for February 2018.html",
				"TIOBE Index for January 2018.html",
				"TIOBE Index for June 2018.html",
				"TIOBE Index for March 2018.html",
				"TIOBE Index for May 2018.html",
				"TIOBE-exchange-matrix-data.html",
				"TIOBE-exchange-matrix-data.py",
				"TIOBE-gernate-index-py2.py",
				"TIOBE-index.html",
				"TIOBE_matrixData.txt"};
		int nCount = A_strFiles.length;
		
		SortTIOBEFileNameByDate stf = new SortTIOBEFileNameByDate();

		String[] sorted = stf.sortFileName(A_strFiles);

		System.out.println(String.format("%-40s %-40s", "Source data", "Sorted data"));
		System.out.println(String.format("%-40s %-40s", "==================", "=================="));
		for(int i = 0; i < nCount; i++){
			System.out.println(String.format("%-40s %-40s", A_strFiles[i], sorted[i]));
		}
	}
	
	private int getDatespan(String month, String year){
		return Integer.parseInt(year) * 100 + (dictMonth.containsKey(month) ? dictMonth.get(month) : 0);
	}
	
	private int getIndex(int[][] buff, int i){
		return buff[i][1];
	}
	
	private void setIndex(int[][] buff, int i, int n){
		buff[i][1] = n;
	}
	
	private int getData(int[][] buff, int i){
		return buff[getIndex(buff, i)][2];
	}
	
	Pattern regexTIOBEFile = Pattern.compile("^TIOBE Index for (\\w+) (\\d{4})\\.html$");
	public String[] sortFileName(String[] files){
		int nCount = files.length;
		String[] list = new String[nCount];
		int[][] buff = new int[nCount][3];
		// init buff
		for(int i = 0, n = 0; i < nCount; i++){
			buff[i][0] = buff[i][1] = i;
			Matcher m = regexTIOBEFile.matcher(files[i]);
			buff[i][2] = m.find() ? getDatespan(m.group(1), m.group(2)) : ++n;
		}
		// sort data
		for(int i = 0; i < nCount - 1; i++){
			int nMin = i;
			for(int j = i + 1; j < nCount; j++){
				if(getData(buff, j) < getData(buff, nMin)){
					nMin = j;
				}
			}
			if(i != nMin){
				int t = getIndex(buff, i);
				setIndex(buff, i, getIndex(buff, nMin));
				setIndex(buff, nMin, t);
			}
		}
		// gernate result
		for(int i = 0; i < nCount; i++){
			list[i] = files[getIndex(buff, i)];
		}
		return list;
	}

}

/*
Source data                              Sorted data
==================                       ==================
TIOBE Index for April 2018.html          TIOBE-exchange-matrix-data.html
TIOBE Index for February 2018.html       TIOBE-exchange-matrix-data.py
TIOBE Index for January 2018.html        TIOBE-gernate-index-py2.py
TIOBE Index for June 2018.html           TIOBE-index.html
TIOBE Index for March 2018.html          TIOBE_matrixData.txt
TIOBE Index for May 2018.html            TIOBE Index for January 2018.html
TIOBE-exchange-matrix-data.html          TIOBE Index for February 2018.html
TIOBE-exchange-matrix-data.py            TIOBE Index for March 2018.html
TIOBE-gernate-index-py2.py               TIOBE Index for April 2018.html
TIOBE-index.html                         TIOBE Index for May 2018.html
TIOBE_matrixData.txt                     TIOBE Index for June 2018.html
*/
