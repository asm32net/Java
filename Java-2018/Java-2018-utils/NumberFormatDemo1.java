import java.text.NumberFormat;

public class NumberFormatDemo1 {
	
	public static NumberFormat nfd = NumberFormat.getInstance();

	public static String toCurrenyString(Object o){
		if(o == null) return null;
		String r;
		String t = o.toString();
		try{
			r = nfd.format(Double.valueOf(t));
//			r = nfd.format(o); // 不是很稳定不支持  “-1234”
		}catch(Exception ex){
			r = t;
		}
		return r;
	}
	
	public static void main(String[] args){
		nfd.setMaximumFractionDigits(2); //  设置2位小数
		nfd.setMinimumFractionDigits(2); //  设置2位小数

		Double d1 = 23323.2323442;
		Double d2 = 2345432D;
		int i = 123456;

		System.out.println(toCurrenyString(null)); // null
		System.out.println(toCurrenyString(d1));   // 23,323.23
		System.out.println(toCurrenyString(d2));   // 2,345,432.00
		System.out.println(toCurrenyString(i));   // 123,456.00
		System.out.println(toCurrenyString(12345));   // 12345.00
		System.out.println(toCurrenyString(-12345));   // -12,345.00
		System.out.println(toCurrenyString("12345678"));   // 12,345,678.00
		System.out.println(toCurrenyString("-12345678"));   // -12,345,678.00
		System.out.println(toCurrenyString("12,,345678"));   // 12,,345678
		System.out.println(toCurrenyString(""));   // 
		System.out.println(toCurrenyString("aaa"));   // aaa
		System.out.println(toCurrenyString(1234567.00));   // 1,234,567.00
		System.out.println(toCurrenyString(-1234567.00));   // -1,234,567.00
	}
}
