import java.awt.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PaDigitalClock2016JFrame extends JPanel implements Runnable {
	private Thread thread1;
	private Image imageDigits = null;
	private int nItemWidth;
	private int nItemHeight;
	private int nClientWidth;
	private int nClientHeight;

	public PaDigitalClock2016JFrame(){
		thread1 = new Thread(this);
		thread1.start();
	}

	public void run(){
		while(true){
			try{
				thread1.sleep(10);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
			repaint();
		}
	}

	private void PaDigitalClock2016JFrame_Init(){
		nClientWidth = this.getWidth();
		nClientHeight = this.getHeight();

		int d = nClientWidth / 11 / 12 - 1;
		if(d<2) d = 2;
		int w = d + 1;
		nItemWidth = w * 12;
		nItemHeight = w * 22;
		int nImageWidth = nItemWidth * 13;
		if(imageDigits!=null) imageDigits=null;
		imageDigits = createImage(nImageWidth, nItemHeight);
		Graphics g = imageDigits.getGraphics();
		int nOffset = 0;
		int nStartX = 0;
		int nStartY = 0;
		g.setColor(Color.blue);
		g.fillRect(0, 0, nImageWidth, nItemHeight);
		g.setColor(Color.white);
		for(int m=0; m<12; m++){
			for(int n = 0; n<22; n++){
				int ch = A_byteDigitMatrix[nOffset]&255 | A_byteDigitMatrix[nOffset+1]<<8;
				for(int i=0; i<12; i++){
					if((ch & A_byteMask[i])!=0){
						g.fillOval(nStartX + i * w, nStartY + n * w, d, d);
					}
				}
				nOffset += 2;
			}
			nStartX += 12 * w;
		}
	}
	
	public void paintComponent(Graphics g){
		if(nClientWidth!=this.getWidth() || nClientHeight != this.getHeight()){
			PaDigitalClock2016JFrame_Init();
		}

		Calendar now = new GregorianCalendar();
		SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str  = dateTimeFormat.format(now.getTime());
		g.clearRect(0, 0, nClientWidth, nClientHeight);
		g.setColor(Color.yellow);
		g.drawString(str, 5, 20);
		int nHour = now.get(Calendar.HOUR_OF_DAY);
		int nMinute = now.get(Calendar.MINUTE);
		int nSecond = now.get(Calendar.SECOND);
		int nMillisecond = now.get(Calendar.MILLISECOND);

		int[] A_nDigit = new int[11];
		int nSplitter = nMillisecond>500 ? 12 : 10;
		A_nDigit[0] = nHour / 10;
		A_nDigit[1] = nHour % 10;
		A_nDigit[2] = nSplitter;
		A_nDigit[3] = nMinute / 10;
		A_nDigit[4] = nMinute % 10;
		A_nDigit[5] = nSplitter;
		A_nDigit[6] = nSecond / 10;
		A_nDigit[7] = nSecond % 10;
		A_nDigit[8] = 11;
		A_nDigit[9] = nMillisecond / 100;
		A_nDigit[10] = nMillisecond /10 % 10;


		int nOffsetX = (nClientWidth - nItemWidth * 11) /2;
		int nOffsetY = (nClientHeight - nItemHeight) /2;
		for(int i=0; i<11; i++){
			int nTargetX = nOffsetX + i * nItemWidth;
			int nSourceX = A_nDigit[i] * nItemWidth;
			g.drawImage(imageDigits, nTargetX, nOffsetY, nTargetX + nItemWidth, nOffsetY + nItemHeight,
				nSourceX, 0, nSourceX + nItemWidth, nItemHeight, this);
		}
	}

	public static void main(String[] args){
		JFrame frame = new JFrame();
		PaDigitalClock2016JFrame panel = new PaDigitalClock2016JFrame();

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("PaDigitalClock2016JFrame");
		frame.setSize(600, 450);
		frame.setLocationRelativeTo(null);
		frame.setBackground(Color.black);

		frame.getContentPane().add(panel);
		frame.setVisible(true);

		panel.PaDigitalClock2016JFrame_Init();
	}



	private char[] A_byteMask = {2048, 1024, 512, 256, 128, 64, 32, 16, 8, 4, 2, 1};

	private byte[] A_byteDigitMatrix = {
		(byte)254, 3, (byte)253, 5, (byte)251, 6, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 3, 6, 
		1, 4, 0, 0, 1, 4, 3, 6, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, (byte)251, 6, (byte)253, 
		5, (byte)254, 3, 0, 0, 0, 0, 1, 0, 3, 0, 7, 0, 7, 0, 7, 0, 7, 0, 7, 0, 3, 0, 
		1, 0, 0, 0, 1, 0, 3, 0, 7, 0, 7, 0, 7, 0, 7, 0, 7, 0, 3, 0, 1, 0, 0, 0, 0, 0, 
		(byte)254, 3, (byte)253, 1, (byte)251, 0, 7, 0, 7, 0, 7, 0, 7, 0, 7, 0, 3, 0, 
		(byte)253, 1, (byte)254, 3, (byte)252, 5, 0, 6, 0, 7, 0, 7, 0, 7, 0, 7, 0, 7, 
		(byte)248, 6, (byte)252, 5, (byte)254, 3, 0, 0, (byte)254, 3, (byte)253, 1, 
		(byte)251, 0, 7, 0, 7, 0, 7, 0, 7, 0, 7, 0, 3, 0, (byte)253, 1, (byte)254, 3, 
		(byte)253, 1, 3, 0, 7, 0, 7, 0, 7, 0, 7, 0, 7, 0, (byte)251, 0, (byte)253, 1, 
		(byte)254, 3, 0, 0, 0, 0, 1, 4, 3, 6, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 3, 6, 
		(byte)253, 5, (byte)254, 3, (byte)253, 1, 3, 0, 7, 0, 7, 0, 7, 0, 7, 0, 7, 0, 
		3, 0, 1, 0, 0, 0, 0, 0, (byte)254, 3, (byte)252, 5, (byte)248, 6, 0, 7, 0, 7, 
		0, 7, 0, 7, 0, 7, 0, 6, (byte)252, 5, (byte)254, 3, (byte)253, 1, 3, 0, 7, 0, 
		7, 0, 7, 0, 7, 0, 7, 0, (byte)251, 0, (byte)253, 1, (byte)254, 3, 0, 0, 
		(byte)254, 3, (byte)252, 5, (byte)248, 6, 0, 7, 0, 7, 0, 7, 0, 7, 0, 7, 0, 6, 
		(byte)252, 5, (byte)254, 3, (byte)253, 5, 3, 6, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 
		(byte)251, 6, (byte)253, 5, (byte)254, 3, 0, 0, (byte)254, 3, (byte)253, 1, 
		(byte)251, 0, 7, 0, 7, 0, 7, 0, 7, 0, 7, 0, 3, 0, 1, 0, 0, 0, 1, 0, 3, 0, 7, 
		0, 7, 0, 7, 0, 7, 0, 7, 0, 3, 0, 1, 0, 0, 0, 0, 0, (byte)254, 3, (byte)253, 5, 
		(byte)251, 6, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 3, 6, (byte)253, 5, (byte)254, 3, 
		(byte)253, 5, 3, 6, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, (byte)251, 6, (byte)253, 5, 
		(byte)254, 3, 0, 0, (byte)254, 3, (byte)253, 5, (byte)251, 6, 7, 7, 7, 7, 7, 
		7, 7, 7, 7, 7, 3, 6, (byte)253, 5, (byte)254, 3, (byte)253, 1, 3, 0, 7, 0, 7, 
		0, 7, 0, 7, 0, 7, 0, (byte)251, 0, (byte)253, 1, (byte)254, 3, 0, 0, 0, 0, 0, 
		0, 0, 0, 0, 0, 0, 0, 112, 0, 112, 0, 112, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
		112, 0, 112, 0, 112, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
		0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
		0, 0, 112, 0, 112, 0, 112, 0, 0, 0, 0, 0};
}