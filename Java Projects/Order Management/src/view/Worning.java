package view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Worning {
		

	
	public Worning() {
		JFrame frame = new JFrame ("Magazin");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(200, 200);
		
		
		JPanel panel1 = new JPanel();
		panel1.setLayout(null);
		
		JLabel txt=new JLabel("Ati depasit cantitatea din stoc!!");
		
		panel1.add(txt).setBounds(10, 50, 200, 30);;
		
		
		frame.setContentPane(panel1);
		
		
	      frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
