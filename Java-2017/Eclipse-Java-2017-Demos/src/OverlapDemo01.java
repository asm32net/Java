
public class OverlapDemo01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		OverlapDemo01 od = new OverlapDemo01();
		System.out.println( od.overlap("1237895643", "1234015643") );
	}
	
	public String overlap(String s1, String s2){
		int n = Math.min(s1.length(), s2.length());
		int i = 0;
		for(i=0; i<n; i++){
			if(s1.charAt(i) != s2.charAt(i)){
				break;
			}
		}
		return s1.substring(0, i);
	}
}
