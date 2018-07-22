import java.awt.*;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;

class PaStarry2016JFrame extends JPanel implements Runnable {
	Thread thread1;
	Random rand = new Random();
	int nClientWidth = 0;
	int nClientHeight = 0;
	int nCount = 100;
	int[] x = new int[nCount];
	int[] y = new int[nCount];
	int[] d = new int[nCount];
	int sel = 0;
	boolean isReady = false;
	Color[] color = new Color[nCount];

	public PaStarry2016JFrame(){
		thread1 = new Thread(this);
		thread1.start();
	}

	public void PaStarry2016JFrame_Init(){
		nClientWidth = this.getWidth();
		nClientHeight = this.getHeight();
		for(int i=0; i<nCount; i++){
			PA_DoStarrySetItem(i);
		}
		isReady = true;
	}

	public void PA_DoStarrySetItem(int i){
		x[i] = rand.nextInt(nClientWidth);
		y[i] = rand.nextInt(nClientHeight);
		d[i] = 2 + rand.nextInt(5);
		color[i] = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
	}

	public void run(){
		while(true){
			try{
				thread1.sleep(20);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
			if(isReady){
				sel++;
				if(sel>=nCount) sel = 0;
				PA_DoStarrySetItem(sel);
			}

			repaint();
		}
	}

	protected void paintComponent(Graphics g){
		if(nClientWidth != this.getWidth() || nClientHeight != this.getHeight()){
			PaStarry2016JFrame_Init();
		}

		g.clearRect(0, 0, nClientWidth, nClientHeight);

		for(int i=0; i<nCount; i++){
			g.setColor(color[i]);
			g.fillOval(x[i], y[i], d[i], d[i]);
		}
	}

	public static void main(String[] args){
		JFrame frame = new JFrame();
		PaStarry2016JFrame panel = new PaStarry2016JFrame();

		frame.setTitle("PaStarry2016JFrame");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 450);
		frame.setLocationRelativeTo(null);
		frame.setBackground(Color.black);

		frame.getContentPane().add(panel);
		frame.setVisible(true);

		panel.PaStarry2016JFrame_Init();

	}
}