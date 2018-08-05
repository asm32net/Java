// JavaFNVHashDemo1.java

class JavaFNVHashDemo1{
	public static final int M_MASK = 31;
	public static final int M_SHIFT = 0;
	public static void main(String[] args){
		String[] A_strKeys = {"C", "C++", "Java", "C#", "Python", "Go", "Scala", "vb.net", "JavaScript", "PHP", "Perl", "Ruby"};
		int nCount = A_strKeys.length;

		for(int i = 0; i < nCount; i++){
			int nHash = FNVHash(A_strKeys[i]);
			System.out.println( String.format("%-10d %-15s %12d %3d", i, A_strKeys[i], nHash, nHash % 33) );
		}
	}

	public static int FNVHash(String s){
		int n = s.length();
		long nHash = 2166136261L;
		for(int i = 0; i < n; i++){
			nHash = (nHash * 16777619) ^ s.charAt(i);
		}
		if(M_SHIFT == 0) return (int)nHash;
		return (int)((nHash ^ (nHash >> M_SHIFT)) & M_MASK);
	}
}

/*
0          C                   84696412  31
1          C++              -2075630010  -3
2          Java              1542292725   6
3          C#                1316419575   9
4          Python            -193191885 -18
5          Go                1249309159  10
6          Scala             -250560223  -1
7          vb.net           -1198697754 -12
8          JavaScript        -790376216 -14
9          PHP                589789791  30
10         Perl              -897830718 -15
11         Ruby              -651897675   0
*/