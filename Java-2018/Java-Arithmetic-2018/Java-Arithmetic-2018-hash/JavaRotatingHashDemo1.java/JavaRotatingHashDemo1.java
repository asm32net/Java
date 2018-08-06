// JavaRotatingHashDemo1.java

class JavaRotatingHashDemo1{
	public static void main(String[] args){
		String[] keys = {"C", "C++", "Java", "C#", "Python", "Go", "Scala", "vb.net", "JavaScript", "PHP", "Perl", "Ruby"};

		JavaRotatingHashDemo1 jrhd = new JavaRotatingHashDemo1();
		for(int i = 0, n = keys.length; i < n; i++){
			int nHash = jrhd.RotatingHash(keys[i], 31);
			System.out.println(String.format("%-10d %-15s %3d", i, keys[i], nHash));
		}
	}

	public int RotatingHash(String s, int nPrime){
		int n = s.length(), nHash = n;
		for(int i = 0; i < n; i++){
			nHash = (nHash << 4 >> 28) ^ s.charAt(i);
		}
		return nHash % nPrime;
	}
}

/*
0          C                 5
1          C++              12
2          Java              4
3          C#                4
4          Python           17
5          Go               18
6          Scala             4
7          vb.net           23
8          JavaScript       23
9          PHP              18
10         Perl             15
11         Ruby             28
*/