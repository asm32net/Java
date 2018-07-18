import java.awt.*;
import java.awt.Point;
import javax.swing.JFrame;
import javax.swing.JPanel;

class PaEllipse2016JFrame extends JPanel implements Runnable{
	Thread thread1;
	double PI2 = Math.PI + Math.PI;
	boolean isReady = false;
	int nClientWidth;
	int nClientHeight;
	double fStartAngle = 0;

	// ellipse
	int[] x = new int[2], y = new int[2], a = new int[2], b = new int[2];
	double[] angle = new double[2], rotate = new double[2];

	public PaEllipse2016JFrame(){
		thread1 = new Thread(this);
		thread1.start();
	}

	public void PaEllipse2016JFrame_Init(){
		nClientWidth = this.getWidth();
		nClientHeight = this.getHeight();
		PA_DoEllipse2016Init(0, 0, 0, 300, 75, 0, 0);
		PA_DoEllipse2016Init(1, 0, 0, 50, 200, 0, 0);
		isReady = true;
	}

	public void PA_DoEllipse2016Init(int i, int x, int y, int a, int b, double angle, double rotate){
		this.x[i] = x;
		this.y[i] = y;
		this.a[i] = a;
		this.b[i] = b;
		this.angle[i] = angle;
		this.rotate[i] = rotate;
	}

	public void PA_DoEllipseRotate(){
		fStartAngle += PI2 / 160;
		if(fStartAngle>=PI2) fStartAngle=0;
	}

	public void run(){
		while(true){
			try{
				thread1.sleep(20);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
			PA_DoEllipseRotate();
			repaint();
		}
	}

	private Point rotatePoint(Point point, double angle){
		double sin1 = Math.sin(angle);
		double cos1 = Math.cos(angle);
		point.setLocation(point.getX() * cos1 - point.getY() * sin1,
			point.getY() * cos1 + point.getX() * sin1);
		return point;
	}

	protected void paintComponent(Graphics g){
		if(nClientWidth!=this.getWidth() || nClientHeight!=this.getHeight()){
			PaEllipse2016JFrame_Init();
		}
		g.clearRect(0, 0, nClientWidth, nClientHeight);
		g.setColor(Color.yellow);
		int x0 = nClientWidth/2;
		int y0 = nClientHeight/2;
		double step = PI2 / 40;
		int r=0, d;
		Point point = new Point();
		for(double i=0; i<PI2; i+=step){
			double sin1 = Math.sin(i + fStartAngle);
			double cos1 = Math.cos(i + fStartAngle);
			r = r==3 ? 6: 3;
			d = r + r;
			for(int n=0; n<2; n++){
				point.setLocation(a[n] * sin1, b[n] * cos1);
				point = rotatePoint(point, fStartAngle);
				g.fillOval(x0 + (int)(point.getX()) - r, y0 + (int)(point.getY()) - r, d, d);
			}
		}
	}

	public static void main(String[] args){
		JFrame frame = new JFrame();
		PaEllipse2016JFrame panel = new PaEllipse2016JFrame();

		frame.setTitle("PaEllipse2016JFrame");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 450);
		frame.setLocationRelativeTo(null);
		frame.setBackground(Color.black);

		frame.getContentPane().add(panel);
		frame.setVisible(true);

		panel.PaEllipse2016JFrame_Init();
	}
}