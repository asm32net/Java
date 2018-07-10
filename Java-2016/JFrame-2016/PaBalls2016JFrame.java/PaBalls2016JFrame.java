// PaBalls2016JFrame.java

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;
import javax.imageio.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.*;
import java.awt.event.*;

class PaBalls2016JFrame extends JPanel implements Runnable, ActionListener{
	Thread thread1;
	Random rand = new Random();
	int nClientWidth, nClientHeight;
	boolean isReady = false;
	boolean isRun = false;
	Image imageBalls = null;
	BufferedImage imageBackground = null;
	int[] A_nBallsX = new int[10];
	int nDiameter = 80;
	int nRangeX, nRangeY;
	int nCount = 50;
	int nPathID = 0;
	int nSelectedIcon = 7;
	int[] balls_nIconID = new int[nCount],
		balls_x = new int[nCount],
		balls_y = new int[nCount],
		balls_dx = new int[nCount],
		balls_dy = new int[nCount];

	private double PI2 = Math.PI + Math.PI;
	private int nCount1;
	private int nCount2;
	private double fRotate1;
	private double fRotate2;

	public PaBalls2016JFrame(){
		try{
			//imageBackground = ImageIO.read(new File("res/PaBalls2016JFrame.bg.jpg"));
			//imageBalls = ImageIO.read(new File("res/PaBalls2016JFrame.balls.png"));
			imageBackground = ImageIO.read(this.getClass().getResourceAsStream("res/PaBalls2016JFrame.bg.jpg"));
			imageBalls = ImageIO.read(this.getClass().getResourceAsStream("res/PaBalls2016JFrame.balls.png"));

			tba_set_icon=new JButton(new ImageIcon(ImageIO.read(this.getClass().getResourceAsStream("res/button-01.png"))));
			tba_set_icon_x=new JButton(new ImageIcon(ImageIO.read(this.getClass().getResourceAsStream("res/button-02.png"))));
			tba_ellipse_path=new JButton(new ImageIcon(ImageIO.read(this.getClass().getResourceAsStream("res/button-03.png"))));
			tba_refresh=new JButton(new ImageIcon(ImageIO.read(this.getClass().getResourceAsStream("res/button-04.png"))));
			tba_switch_start=new JButton(new ImageIcon(ImageIO.read(this.getClass().getResourceAsStream("res/button-05.png"))));
		}catch(Exception e){
			e.printStackTrace();
		}
		int x = 0;
		for(int i=0; i<10; i++){
			A_nBallsX[i] = x;
			x += nDiameter;
		}
		nCount1 = nCount / 3;
		nCount2 = nCount - nCount1;
		fRotate1 = 0;
		fRotate2 = 0;
		nPathID = 0;
		isRun = false;

		setBackground(Color.black);

		PA_DoSwitchThread();
	}

	public void PA_DoSwitchThread(){
		if(isRun){
			isRun = false;
			thread1.interrupt();
			thread1 = null;
		}else{
			isRun = true;
			thread1 = new Thread(this);
			thread1.start();
		}
	}

	public void PaBalls2016JFrame_Init(){
		nClientWidth = this.getWidth();
		nClientHeight = this.getHeight();

		nRangeX = nClientWidth - nDiameter;
		nRangeY = nClientHeight - nDiameter;
		if(nPathID==0){
			PA_DoBallsImpactPathPlaced();
		}else{
			PA_DoBallsEllipsePathPlaced();
		}
		isReady = true;
	}

	public void PA_DoBallsImpactPathPlaced(){
		for(int i=0; i<nCount; i++){
			balls_nIconID[i] = rand.nextInt(10); 
			balls_x[i] = rand.nextInt(nRangeX);
			balls_y[i] = rand.nextInt(nRangeY);
			balls_dx[i] = 2 + rand.nextInt(10);
			balls_dy[i] = 2 + rand.nextInt(10);
		}
	}

	public void PA_DoBallsEllipsePathPlaced(){
		int i;
		int n = 0;
		int a;
		int b;
		double fAngle;
		int cx = nRangeX/2;
		int cy = nRangeY/2;
		a = nClientWidth / 4;
		b = nClientHeight / 8;
		for(i=0; i<nCount1; i++){
			fAngle = PI2 * i / nCount1 + fRotate1;
			balls_x[n] = cx + (int) (a * Math.sin(fAngle));
			balls_y[n] = cy + (int) (b * Math.cos(fAngle));
			balls_dx[n]=0;
			balls_dy[n]=0;
			n++;
		}

		a = nClientWidth * 3 / 7;
		b = nClientHeight * 2 / 7;
		for(i=0; i<nCount2; i++){
			fAngle = PI2 - PI2 * i / nCount2 - fRotate2;
			balls_x[n] = cx + (int) (a * Math.sin(fAngle));
			balls_y[n] = cy + (int) (b * Math.cos(fAngle));
			balls_dx[n]=0;
			balls_dy[n]=0;
			n++;
		}
	}

	public void PA_DoBallsMove(){
		if(nPathID==0){
			PA_DoBallsImpactMove();
		}else{
			PA_DoBallsEllipseMove();
		}
	}

	public void PA_DoBallsImpactMove(){
		int nx, ny;
		for(int i=0; i<nCount; i++){
			nx = balls_x[i] + balls_dx[i];
			if(balls_dx[i]>0 && nx>=nRangeX || balls_dx[i]<0 && nx<0){
				balls_dx[i] = -balls_dx[i];
			}else{
				balls_x[i] = nx;
			}
			ny = balls_y[i] + balls_dy[i];
			if(balls_dy[i]>0 && ny>=nRangeY || balls_dy[i]<0 && ny<0){
				balls_dy[i] = -balls_dy[i];
			}else{
				balls_y[i] = ny;
			}
		}
	}

	public void PA_DoBallsEllipseMove(){
		fRotate1 += PI2 / 100;
		fRotate2 += PI2 / 200;
		if(fRotate1>PI2) fRotate1=0;
		if(fRotate2>PI2) fRotate2=0;
		PA_DoBallsEllipsePathPlaced();
	}

	public void run(){
		while(isRun){
			try{
				thread1.sleep(10);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
			if(isReady){
				PA_DoBallsMove();
			}
			repaint();
		}
	}

	protected void paintComponent(Graphics g){
		if(nClientWidth!=this.getWidth() || nClientHeight!=this.getHeight()){
			PaBalls2016JFrame_Init();
		}
		g.clearRect(0, 0, nClientWidth, nClientHeight);
		//g.drawImage(imageBalls, 0, 0, this);
		int bgx = (nClientWidth - imageBackground.getWidth()) / 2;
		int bgy = (nClientHeight - imageBackground.getHeight()) / 2;
		g.drawImage(imageBackground, bgx, bgy, this);
		for(int i=0; i<nCount; i++){
			int sx = A_nBallsX[balls_nIconID[i]];
			g.drawImage(imageBalls, balls_x[i], balls_y[i], balls_x[i] + nDiameter, balls_y[i] + nDiameter,
				sx, 0, sx + nDiameter, nDiameter, this);
		}
	}

	public static void main(String[] args){
		JFrame frame = new JFrame();
		JToolBar toolbar = new JToolBar();
		PaBalls2016JFrame panel = new PaBalls2016JFrame();

		toolbar.setFloatable(false);
		toolbar.add(panel.tba_set_icon);
		toolbar.add(panel.tba_set_icon_x);
		toolbar.add(panel.tba_ellipse_path);
		toolbar.add(panel.tba_refresh);
		toolbar.add(panel.tba_switch_start);

		panel.tba_set_icon.addActionListener(panel);
		panel.tba_set_icon_x.addActionListener(panel);
		panel.tba_ellipse_path.addActionListener(panel);
		panel.tba_refresh.addActionListener(panel);
		panel.tba_switch_start.addActionListener(panel);
		panel.setBounds(0, 0, 800, 800);
		panel.setBackground(Color.black);

		frame.setTitle("PaBalls2016JFrame");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 450);
		frame.setLocationRelativeTo(null);
		// frame.setBackground(Color.black);

		frame.getContentPane().add(toolbar,BorderLayout.NORTH);
		frame.getContentPane().add(panel);
		frame.setVisible(true);

		panel.PaBalls2016JFrame_Init();
	}

	public void actionPerformed(ActionEvent e){
		if(e.getSource().equals(tba_set_icon)){
			nSelectedIcon++;
			if(nSelectedIcon>=10) nSelectedIcon=0;
			for(int i=0; i<nCount; i++){
				balls_nIconID[i] = nSelectedIcon;
			}
			repaint();
		}else if(e.getSource().equals(tba_set_icon_x)){
			for(int i=0; i<nCount; i++){
				balls_nIconID[i] = rand.nextInt(10);
			}
			repaint();
		}else if(e.getSource().equals(tba_ellipse_path)){
			nPathID = 1;
			PaBalls2016JFrame_Init();
			repaint();
		}else if(e.getSource().equals(tba_refresh)){
			nPathID = 0;
			PaBalls2016JFrame_Init();
			repaint();
		}else if(e.getSource().equals(tba_switch_start)){
			PA_DoSwitchThread();
		}
	}

	//JButton tba_set_icon=new JButton(new ImageIcon("res/button-01.png"));
	JButton tba_set_icon;
	JButton tba_set_icon_x;
	JButton tba_ellipse_path;
	JButton tba_refresh;
	JButton tba_switch_start;
}