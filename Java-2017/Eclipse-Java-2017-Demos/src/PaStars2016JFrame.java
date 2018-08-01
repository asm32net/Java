import java.awt.*;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;

class PaStars2016JFrame extends JPanel implements Runnable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double PI2 = Math.PI + Math.PI;
	private Thread thread1 = null;
	private Random rand = new Random();
	private boolean isInitialize = false;
	private int nClientWidth, nClientHeight;
	private int nCount = 30;
	private int nSelected = 0;
	private int[][] x = new int[nCount][10];
	private int[][] y = new int[nCount][10];
	private int[] cr = new int[nCount];
	private int[] cg = new int[nCount];
	private int[] cb = new int[nCount];

	public PaStars2016JFrame(){
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("PaStars2016JFrame");
		frame.setBackground(Color.black);
		frame.setSize(600, 450);
		frame.setLocationRelativeTo(null);

		frame.getContentPane().add(this);

		frame.setVisible(true);
		PA_DoFormResize();

		thread1 = new Thread(this);
		thread1.start();
	}

	public void run(){
		while(true){
			try{
				Thread.sleep(200);
				nSelected = (nSelected + 1) % nCount;
				PA_DoStarInit(nSelected);
				repaint();
			}catch(Exception e){
				System.out.println("Exception: " + e.getMessage());
			}
		}
	}

	private void PA_DoStarInit(int i){
		int r = 20 + rand.nextInt(nClientWidth/20);
		int r1 = r / 2;
		double a1 = PI2 / 10;
		int cx = r + rand.nextInt(nClientWidth - r - r);
		int cy = r + rand.nextInt(nClientHeight - r - r);
		for(int n=0; n<5; n++){
			int id = n + n;
			double a2 = PI2 * n / 5;
			x[i][id] = cx + (int)(Math.sin(a2) * r);
			y[i][id] = cy - (int)(Math.cos(a2) * r);
			a2 = a2 + a1;
			x[i][id+1] = cx + (int)(Math.sin(a2) * r1);
			y[i][id+1] = cy - (int)(Math.cos(a2) * r1);
		}
		cr[i] = rand.nextInt(256);
		cg[i] = rand.nextInt(256);
		cb[i] = rand.nextInt(256);
	}

	private void PA_DoFormResize(){
		nClientWidth = this.getWidth();
		nClientHeight = this.getHeight();
		for(int i=0; i<nCount; i++){
			PA_DoStarInit(i);
		}
		isInitialize = true;
	}

	protected void paintComponent(Graphics g){
		if(!isInitialize) return;
		if(nClientWidth!=this.getWidth() || nClientHeight!=this.getHeight()){
			PA_DoFormResize();
		}
		g.clearRect(0, 0, nClientWidth, nClientHeight);
		for(int i=0; i<nCount; i++){
			g.setColor(new Color(cr[i], cg[i], cb[i]));
			g.fillPolygon(x[i], y[i], 10);
		}
	} 

	public static void main(String[] args){
		new PaStars2016JFrame();
	}
}