import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
//import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JPanel;
//import javax.swing.JRootPane;

public class PaTriangle2016JFrame extends JPanel implements Runnable, MouseListener 
{
	private int[] x = new int[3];
	private int[] y = new int[3];
	private int[] dx = new int[3];
	private int[] dy = new int[3];

	private int[] x1 = new int[3];
	private int[] y1 = new int[3];
	private int[] dx1 = new int[3];
	private int[] dy1 = new int[3];

	private Thread thread1;
	private Random rand = new Random();
	private int nClientWidth;
	private int nClientHeight;
	private boolean isInitialize = false;
	//private java.util.Timer timer = new java.util.Timer();

	public PaTriangle2016JFrame(){
		/*
		timer.schedule(new TimerTask(){
			@Override
			public void run(){
				PA_DoTriangle2016Move();
				repaint();
			}
		}, 0, 10);
		*/
		addMouseListener(this);
		thread1 = new Thread(this);
		thread1.start();
	}

	public void mouseClicked(MouseEvent e){
		PA_DoTriangle2016Init();
	}
	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e){}
	public void mousePressed(MouseEvent e){}
	public void mouseReleased(MouseEvent e){}

	public void PA_DoTriangle2016Init(){
		nClientWidth = this.getWidth();
		nClientHeight = this.getHeight();
		//System.out.println("w=" + nClientWidth + ", h=" + nClientHeight);
		for(int i=0; i<3; i++){
			x[i] = rand.nextInt(nClientWidth);
			y[i] = rand.nextInt(nClientHeight);
			dx[i] = 2 + rand.nextInt(5);
			dy[i] = 2 + rand.nextInt(5);
		}
		isInitialize = true;
	}

	public void run(){
		while(true){
			try{
				thread1.sleep(20);
			}catch(InterruptedException  e){
				 // TODO Auto-generated catch block
				e.printStackTrace();
			}
			//TODO定时在此
			//PA_DoTriangle2016Move();
			repaint();
		}
	}

	public void PA_DoTriangle2016Move(){
		int nx, ny;
		for(int i=0; i<3; i++){
			nx = x[i] + dx[i];
			if(dx[i]>0 && nx>nClientWidth || dx[i]<0 && nx<0){
				dx[i] = -dx[i];
			}else{
				x[i] = nx;
			}
			ny = y[i] + dy[i];
			if(dy[i]>0 && ny>nClientHeight || dy[i]<0 && ny<0){
				dy[i] = -dy[i];
			}else{
				y[i] = ny;
			}
		}
	}

	protected void paintComponent(Graphics g){
		if(!isInitialize) return;
		if(nClientWidth!=this.getWidth() || nClientHeight!=this.getHeight()){
			PA_DoTriangle2016Init();
		}
		g.clearRect(0, 0, nClientWidth, nClientHeight);
		Color co = Color.YELLOW;
		//g.setColor(Color.YELLOW);
		for(int i=0; i<50; i++){
			if(i==2){
				System.arraycopy(x, 0, x1, 0, 3);
				System.arraycopy(y, 0, y1, 0, 3);
				System.arraycopy(dx, 0, dx1, 0, 3);
				System.arraycopy(dy, 0, dy1, 0, 3);
			}
			g.setColor(new Color(co.getRed() * i / 50, co.getGreen() * i / 50, co.getBlue() * i / 50));
			g.drawLine(x[0], y[0], x[1], y[1]);
			g.drawLine(x[1], y[1], x[2], y[2]);
			g.drawLine(x[2], y[2], x[0], y[0]);
			PA_DoTriangle2016Move();
		}
		System.arraycopy(x1, 0, x, 0, 3);
		System.arraycopy(y1, 0, y, 0, 3);
		System.arraycopy(dx1, 0, dx, 0, 3);
		System.arraycopy(dy1, 0, dy, 0, 3);
	}

	public static void main(String[] args){
		JFrame frame = new JFrame();
		PaTriangle2016JFrame panel = new PaTriangle2016JFrame();

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("PaTriangle2016JFrame");
		frame.setSize(600, 450);
		frame.setLocationRelativeTo(null); // JFrame center screen
		frame.setBackground(Color.black);

		frame.getContentPane().add(panel);

		frame.setVisible(true);

		panel.PA_DoTriangle2016Init();
	}
}