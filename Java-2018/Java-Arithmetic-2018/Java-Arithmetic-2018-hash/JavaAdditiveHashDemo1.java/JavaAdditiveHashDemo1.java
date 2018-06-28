// JavaAdditiveHashDemo1.java

class JavaAdditiveHashDemo1{
	public static void main(String[] args){
		String[] keys = {"C", "C++", "Java", "C#", "Python", "Go", "Scala", "vb.net", "JavaScript", "PHP", "Perl", "Ruby"};

		JavaAdditiveHashDemo1 jahd = new JavaAdditiveHashDemo1();
		for(int i = 0, n = keys.length; i < n; i++){
			int nHash = jahd.AdditiveHash(keys[i], 31);
			System.out.println(String.format("%-10d %-15s %3d", i, keys[i], nHash));
		}
	}

	public int AdditiveHash(String s, int nPrime){
		int n = s.length(), nHash = n;
		for(int i = 0; i < n; i++){
			nHash += s.charAt(i);
		}
		return nHash % nPrime;
	}
}

/*
0          C                 6
1          C++               1
2          Java             18
3          C#               11
4          Python           28
5          Go               29
6          Scala            24
7          vb.net            6
8          JavaScript        2
9          PHP              18
10         Perl              4
11         Ruby             19
*/