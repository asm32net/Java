import java.util.Calendar;
//import java.util.Date;


public class CalendarDemo1 {
	public static void main(String[] args){
		Calendar now = Calendar.getInstance();
		System.out.println(now.get(Calendar.YEAR) + "-" +
				(now.get(Calendar.MONTH)+1) + "-" +
				now.get(Calendar.DAY_OF_MONTH));
		System.out.println(1.0 * 6 / 4);
	}
}
