// package JFrameDemos;

import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PaChessboard2017Demo1JFrame extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private JFrame frame = new JFrame();
	
	PaChessboard2017Demo1JFrame(){
		frame.setTitle("PaChessboard2017Demo1JFrame.java");
		frame.setSize(450, 450);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.getContentPane().add(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		int w = this.getWidth(), h = this.getHeight(), mw = Math.min(w, h);
		int iw = mw / 19, aw = iw * 18;
		int stx = (w - aw) / 2, dx = stx, sty = (h - aw) / 2, dy = sty;
		for(int i=0; i<19; i++){
			g.drawLine(dx, sty, dx, sty+aw);
			g.drawLine(stx, dy, stx+aw, dy);
			dx += iw;
			dy += iw;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new PaChessboard2017Demo1JFrame();
	}
}
