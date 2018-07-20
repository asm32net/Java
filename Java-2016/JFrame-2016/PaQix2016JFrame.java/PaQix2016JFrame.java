import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;

class PaQix2016JFrame extends JPanel implements Runnable, MouseListener{
	Thread thread1;
	Random rand = new Random();
	boolean isReady = false;
	int nClientWidth;
	int nClientHeight;
	int[][] x = new int[2][2];
	int[][] y = new int[2][2];
	int[][] dx = new int[2][2];
	int[][] dy = new int[2][2];
	Color[] color = new Color[2];
	int[] dr = new int[2], dg = new int[2], db = new int[2];

	int nCount = 100;

	public PaQix2016JFrame(){
		addMouseListener(this);
		thread1 = new Thread(this);
		thread1.start();
	}

	public void mouseClicked(MouseEvent e){
		PaQix2016JFrame_Init();
	}
	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e){}
	public void mousePressed(MouseEvent e){}
	public void mouseReleased(MouseEvent e){}

	public void PaQix2016JFrame_Init(){
		nClientWidth = this.getWidth();
		nClientHeight = this.getHeight();
		for(int i=0; i<2; i++){
			x[0][i] = rand.nextInt(nClientWidth);
			y[0][i] = rand.nextInt(nClientHeight);
			dx[0][i] = 2 + rand.nextInt(3);
			dy[0][i] = 2 + rand.nextInt(3);
		}
		color[0] = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
		dr[0] = dg[0] = db[0] = 5;
		isReady = true;
	}

	public void PA_DoQix2006Move(){
		int nx, ny;
		for(int i=0; i<2; i++){
			nx = x[0][i] + dx[0][i];
			if(dx[0][i]>0 && nx>=nClientWidth || dx[0][i]<0 && nx<0){
				dx[0][i] = -dx[0][i];
			}else{
				x[0][i] = nx;
			}
			ny = y[0][i] + dy[0][i];
			if(dy[0][i]>0 && ny>=nClientHeight || dy[0][i]<0 && ny<0){
				dy[0][i] = -dy[0][i];
			}else{
				y[0][i] = ny;
			}
		}
	}

	public void PA_DoQix2016NextColor(){
		int r, g, b, nr, ng, nb;
		r = color[0].getRed();
		g = color[0].getGreen();
		b = color[0].getBlue();
		nb = b + db[0];
		if(db[0]>0 && nb>255 || db[0]<0 && nb<0){
			db[0] = -db[0];
			ng = g + dg[0];
			if(dg[0]>0 && ng>255 || dg[0]<0 && ng<0){
				dg[0] = -dg[0];
				nr = r + dr[0];
				if(dr[0]>0 && nr>255 || dr[0]<0 && nr<0){
					dr[0] = -dr[0];
				}else{
					r = nr;
				}
			}else{
				g = ng;
			}
		}else{
			b = nb;
		}
		color[0] = new Color(r, g, b);
	}

	public void PA_DoQix2006Update(boolean isBackup){
		int from, to;
		if(isBackup){
			from = 0; to=1;
		}else{
			from = 1; to=0;
		}
		for(int i=0; i<2; i++){
			x[to][i]=x[from][i];
			y[to][i]=y[from][i];
			dx[to][i]=dx[from][i];
			dy[to][i]=dy[from][i];
		}
		color[to]=color[from];
		dr[to]=dr[from];
		dg[to]=dg[from];
		db[to]=db[from];
	}

	public void run(){
		while(true){
			try{
				thread1.sleep(20);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
			repaint();
		}
	}

	protected void paintComponent(Graphics g){
		if(nClientWidth!=this.getWidth() || nClientHeight!=this.getHeight()){
			PaQix2016JFrame_Init();
		}
		g.clearRect(0, 0, nClientWidth, nClientHeight);
		for(int i=0; i<nCount; i++){
			if(i==5){
				PA_DoQix2006Update(true);
			}
			g.setColor(color[0]);
			g.drawLine(x[0][0], y[0][0], x[0][1], y[0][1]);
			PA_DoQix2006Move();
			PA_DoQix2016NextColor();
		}
		PA_DoQix2006Update(false);
	}

	public static void main(String[] args){
		JFrame frame = new JFrame();
		PaQix2016JFrame panel = new PaQix2016JFrame();

		frame.setTitle("PaQix2016JFrame");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 450);
		frame.setLocationRelativeTo(null);
		frame.setBackground(Color.black);

		frame.getContentPane().add(panel);

		frame.setVisible(true);
		panel.PaQix2016JFrame_Init();
	}
}