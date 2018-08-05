// JavaJSHashDemo1.java

class JavaJSHashDemo1{
	public static void main(String[] args){
		String[] A_strKeys = {"C", "C++", "Java", "C#", "Python", "Go", "Scala", "vb.net", "JavaScript", "PHP", "Perl", "Ruby"};
		int nCount = A_strKeys.length;

		for(int i = 0; i < nCount; i++){
			int nHash = JSHash(A_strKeys[i]);
			System.out.println( String.format("%-10d %-15s %12d %3d", i, A_strKeys[i], nHash, nHash % 31) );
		}
	}

	public static int JSHash(String s){
		int nHash = 1315423911;
		int n = s.length();
		for(int i = 0; i < n; i++){
			nHash ^= ((nHash << 5) + s.charAt(i) + (nHash >> 2));
		}
		return nHash & 0x7fffffff;
	}
}

/*
0          C                  787808363   0
1          C++               1251704976  27
2          Java               210226759  11
3          C#                1688751606   8
4          Python             199420479  21
5          Go                1688751899  22
6          Scala             1947895722  27
7          vb.net            1590035886   6
8          JavaScript        1431535225  20
9          PHP               1251758047  26
10         Perl               212654416  27
11         Ruby               212422186  18
*/
