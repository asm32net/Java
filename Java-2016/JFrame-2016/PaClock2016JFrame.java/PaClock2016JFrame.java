import java.awt.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PaClock2016JFrame extends JPanel implements Runnable{
	private Thread thread1;
	private double PI2 = Math.PI + Math.PI;
	public void PaClock2016JFrame_Init(){
		thread1 = new Thread(this);
		thread1.start();
	}
	public void run(){
		while(true){
			try{
				thread1.sleep(500);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
			repaint();
		}
	}
	protected void paintComponent(Graphics g){
		int nClientWidth = this.getWidth();
		int nClientHeight = this.getHeight();

		int nClockRadius = (int)(Math.min(nClientWidth, nClientHeight) * 0.4);
		int nClockDiameter = nClockRadius + nClockRadius;

		int cx = nClientWidth/2;
		int cy = nClientHeight/2;
		double dx, dy, fStart;

		g.setColor(Color.black);
		g.clearRect(0, 0, nClientWidth, nClientHeight);
		g.drawOval(cx - nClockRadius, cy - nClockRadius, nClockDiameter, nClockDiameter);
		g.drawOval(cx - 5, cy - 5, 10, 10);
		for(int i=0; i<60; i++){
			dx = Math.sin(PI2 * i / 60) * nClockRadius;
			dy = Math.cos(PI2 * i / 60) * nClockRadius;
			if(i%5==0){
				fStart = 0.9;
				g.drawString(String.valueOf(i/5),
					cx + (int)(nClockRadius * 0.8 * Math.sin(PI2 * i / 60)) - 3,
					cy - (int)(nClockRadius * 0.8 * Math.cos(PI2 * i / 60)) + 4);
			}else{
				fStart = 0.95;
			}
			g.drawLine(cx + (int)(dx*fStart), cy - (int)(dy*fStart), cx + (int)dx, cy - (int)dy);
		}

		Calendar now = new GregorianCalendar();
		SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str  = dateTimeFormat.format(now.getTime());
		g.drawString(str, 5, 20);
		int nHour = now.get(Calendar.HOUR);
		int nMinute = now.get(Calendar.MINUTE);
		int nSecond = now.get(Calendar.SECOND);

		fStart = 0.05;
		double[] A_fEnd = {0.4, 0.5, 0.7};
		double[] A_fAngle = {PI2 * (60 * (60 * (nHour % 12) + nMinute) + nSecond) / 43200,
			PI2 * (60 * nMinute + nSecond) / 3600,
			PI2 * nSecond / 60};
		for(int i=0; i<3; i++){
			dx = Math.sin(A_fAngle[i]) * nClockRadius;
			dy = Math.cos(A_fAngle[i]) * nClockRadius;
			g.drawLine(cx + (int)(dx*fStart), cy - (int)(dy*fStart),
				cx + (int)(dx*A_fEnd[i]), cy - (int)(dy*A_fEnd[i]));
		}
	}
	public static void main(String[] args){
		JFrame frame = new JFrame();
		PaClock2016JFrame panel = new PaClock2016JFrame();

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("PaClock2016JFrame");
		frame.setSize(600, 450);
		frame.setLocationRelativeTo(null);
		frame.setBackground(Color.white);

		frame.getContentPane().add(panel);

		frame.setVisible(true);
		panel.PaClock2016JFrame_Init();
	}
}