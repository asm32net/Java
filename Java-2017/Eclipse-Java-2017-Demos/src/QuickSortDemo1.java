import java.util.Date;
import java.util.LinkedList;


public class QuickSortDemo1 {

	private int deep = 0;
	private int maxDeep = 0;
	public void display(int[] data, int low, int high){
/*		for(int i=0; i<data.length; i++){
			System.out.print(data[i] + ", ");
		}
		System.out.println();*/
		println("maxDeep=" + maxDeep);
	}
	
	public void println(String s){
		System.out.println(s);
	}
	
	public void println(int n){
		println(Integer.toString(n));
	}

	public void quickSort(int[] data, int left, int right){
		deep++;
		if(maxDeep<deep) maxDeep = deep;
		//println("deep=" + deep + " ,maxDeep=" + maxDeep);
		if(left < right){
			int key = data[left];
			int low = left;
			int high = right;
			while(low < high){
				while(low < high && data[high] >= key){
					high--;
				}
				data[low] = data[high];
				while(low < high && data[low] <= key){
					low++;
				}
				data[high] = data[low];
			}
			data[low] = key;
	
			//System.out.println("deep=" + deep + " ,maxDeep=" + maxDeep);
			quickSort(data, left, low-1);
			quickSort(data, low+1, right);
		}
		deep--;
	}
	
	public void rec_quickSort(int[] data, int left, int right){
		if(left < right){
			int index = partition(data, left, right);
			rec_quickSort(data, left, index-1);
			rec_quickSort(data, index+1, right);
		}
	}
	
	private int partition(int[] data, int left, int right){
		int povid = data[left];
		while(left < right){
			while(left < right && data[right] >= povid)
				right--;
			data[left] = data[right];
			while(left < right && data[left] <= povid)
				left++;
			data[right] = data[left];
		}
		data[left] = povid;
		return left;
	}
	
	public void nonRec_quickSort(int[] data, int left, int right){
		LinkedList<Integer> stack = new LinkedList<Integer>();
		stack.push(right);
		stack.push(left);
		while(!stack.isEmpty()){
			int l = stack.pop();
			int r = stack.pop();
			int m = partition(data, l , r);
			if(l < m - 1){
				stack.push(m - 1);
				stack.push(l);
			}
			if(r > m + 1){
				stack.push(r);
				stack.push(m + 1);
			}
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("QuickSortDemo1()");
		QuickSortDemo1 qsd = new QuickSortDemo1();
		//int[] data = {2, 4, 9, 3, 6, 7, 1, 5};
		int[] data = new int[100000];
		System.out.println("Items count = " + data.length);
		for(int i=0; i<data.length; i++){
			data[i] = (int)(Math.random() * 10000000);
		}
		qsd.display(data, 0, data.length-1);

//		qsd.rec_quickSort(data, 0, data.length-1);
//		qsd.nonRec_quickSort(data, 0, data.length-1);
		Date dateStart = new Date();
		qsd.quickSort(data, 0, data.length-1);
		System.out.println("Time elapsed: " + ((new Date()).getTime() - dateStart.getTime())* 0.001 );
		qsd.display(data, 0, data.length-1);
		
	}
}
