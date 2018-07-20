import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

class PaSin2016JFrame extends JPanel implements Runnable{
	Thread thread1;
	boolean isReady = false;
	int nClientWidth;
	int nClientHeight;
	int nCenterY;
	int nWidth1;
	int nCount = 200;
	int nOffset = 0;
	double PI2 = Math.PI + Math.PI;
	public PaSin2016JFrame(){
		thread1 = new Thread(this);
		thread1.start();
	}

	public void PaSin2016JFrame_Init(){
		nClientWidth = this.getWidth();
		nClientHeight = this.getHeight();
		nCenterY = nClientHeight / 2;
		nWidth1 = nClientWidth / nCount / 2;
		isReady = true;
	}

	public void run(){
		while(true){
			try{
				thread1.sleep(10);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
			if(isReady){
				nOffset += 5;
				if(nOffset>nCount) nOffset=0;
			}
			repaint();
		}
	}

	protected void paintComponent(Graphics g){
		if(nClientWidth!=this.getWidth() && nClientHeight!=this.getHeight()){
			PaSin2016JFrame_Init();
		}
		int dx = 0;
		g.setColor(Color.green);
		g.clearRect(0, 0, nClientWidth, nClientHeight);
		for(int i=0; i<nCount; i++){
			double dy = Math.sin(PI2 * (i + nOffset) / nCount) * nCenterY;
			int x = nClientWidth * i / nCount;
			g.fillRect(x, nCenterY - (int)(dy * 0.9), nWidth1, (int)(-dy * 0.1));
		}
	}

	public static void main(String[] args){
		JFrame frame = new JFrame();
		PaSin2016JFrame panel = new PaSin2016JFrame();

		frame.setTitle("PaSin2016JFrame");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 400);
		frame.setLocationRelativeTo(null);
		frame.setBackground(Color.black);

		frame.getContentPane().add(panel);
		frame.setVisible(true);

		panel.PaSin2016JFrame_Init();
	}
}