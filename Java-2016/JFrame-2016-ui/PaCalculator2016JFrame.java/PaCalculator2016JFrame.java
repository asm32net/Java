// PaCalculator2016JFrame.java
import java.awt.*;
import javax.swing.*;

class PaCalculator2016JFrame extends JFrame{
	JTextField txtDisplay = new JTextField();

	Font font = new Font("Arial", Font.PLAIN, 20);

	String strKeys[] = {"7", "8", "9", "*", "<",
						"4", "5", "6", "/", "C",
						"1", "2", "3", "-", "=",
						"0", ".", "+"
		};

	public PaCalculator2016JFrame(){
		super("java JFrame 计算器");
		setSize(300, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null); // 让窗体居中显示

		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();

		setLayout(gb);//定义窗体布局
		JButton btn[] = new JButton[strKeys.length];

		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.weightx = 10;
		gbc.weighty = 10;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridwidth = 5;
		txtDisplay.setHorizontalAlignment(JTextField.RIGHT);
		txtDisplay.setText("3.14159265358979323846264");
		txtDisplay.setFont(font);
		add(txtDisplay, gbc);

		int nCol = 0;
		int nRow = 1;

		for (int i=0; i<strKeys.length; i++){
			if (i%5==0){
				nRow++;
				nCol = 0;
			}
			gbc.gridx = nCol;
			gbc.gridy = nRow;
			gbc.gridwidth = i==15 ? 2: 1;
			gbc.gridheight = i==14 ? 2: 1;
			btn[i] = new JButton(strKeys[i]);
			btn[i].setMargin(new Insets(0, 0, 0, 0));
			btn[i].setFont(font);
			add(btn[i], gbc);
			if (i==15){
				nCol += 2;
			}else{
				nCol++;
			}
		}

		setVisible(true);
	}

	public static void main(String[] args) 
	{
		System.out.println("Hello World!");
		PaCalculator2016JFrame calc = new PaCalculator2016JFrame();
	}
}
