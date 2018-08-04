// JavaBKDRHashDemo1.java

class JavaBKDRHashDemo1{
	public static void main(String[] args){
		String[] A_strKeys = {"C", "C++", "Java", "C#", "Python", "Go", "Scala", "vb.net", "JavaScript", "PHP", "Perl", "Ruby"};
		int nCount = A_strKeys.length;
		// System.out.println("JavaBKDRHashDemo1.java");

		for(int i = 0; i < nCount; i++){
			int nHash = BKDRHash(A_strKeys[i], 31);
			System.out.println(String.format("%-10d %-15s %3d", i, A_strKeys[i], nHash));
		}
	}

	public static int BKDRHash(String s, int nPrime){
		int nSeed = 131; // 31 131 1313 13131 131313 etc
		int n = s.length(), nHash = 0;
		for(int i = 0; i < n; i++){
			nHash = nHash * nSeed + s.charAt(i);
		}
		return nHash % nPrime;
	}
}

/*
0          C                 5
1          C++               0
2          Java             27
3          C#                8
4          Python          -24
5          Go               19
6          Scala            -2
7          vb.net            0
8          JavaScript      -25
9          PHP               9
10         Perl              1
11         Ruby              8
*/
