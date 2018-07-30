
public class StringSplitDemo1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String source = "打印人：李岩@*~打印日期：2017年8月17日";
		String[] arr = source.split("@\\*~", 3);
		System.out.println(arr.length);
		for(int i = 0 ; i < arr.length; i++){
			System.out.println("arr[" + i + "]=" + arr[i]);
		}
		String chars = "不怕辣";
		for(int i=0, l = chars.length(); i < l; i++){
			System.out.println(chars.charAt(i));
		}
	}
}
