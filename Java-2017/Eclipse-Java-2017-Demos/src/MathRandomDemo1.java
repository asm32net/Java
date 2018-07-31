
public class MathRandomDemo1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("MathRandomDemo1()");
		int [] data = new int[20];
		for(int i=0; i<data.length; i++){
			data[i] = (int)(Math.random() * 100);
			System.out.print(data[i] + ", ");
		}
		System.out.println();
	}

}
