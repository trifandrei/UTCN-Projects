package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Method;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import DB.ConnectionDB;

public class DLProduse {

	private  static JTextField nume1=new JTextField();
	
	private JButton delproduse=new JButton("Sterge");
	private JButton back=new JButton("Back");
	
	private JTextArea text=new  JTextArea();
	
	public DLProduse() {
		JFrame frame = new JFrame ("Magazin");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(640, 480);
		
		
		JPanel panel1 = new JPanel();
		panel1.setLayout(null);
		
		JLabel txt=new JLabel("Aici se poate sterge un produs din stoc!!");
		
		panel1.add(txt).setBounds(60,-20, 250,100);
		
		JLabel nume=new JLabel("Nume");
		
		panel1.add(nume).setBounds(50, 50, 50, 50);
		panel1.add(nume1).setBounds(100, 60, 150, 30);
		
		panel1.add(delproduse).setBounds(40, 150, 100, 40);
		
		panel1.add(back).setBounds(170,150, 100, 40);
		
		panel1.add(text).setBounds(300, 50, 200, 100);
		
		frame.setContentPane(panel1);
		
		delproduse.addActionListener(new ActionListener() {
			public  void actionPerformed(ActionEvent e) {
			
				
				String a1=nume1.getText();
				
				
				try {
					Method method1=ConnectionDB.class.getDeclaredMethod("deleteProduse",String.class);
					method1.invoke(null,a1);
					text.append("Produsul a fost sters din stoc!!\n");
			 	}catch(Exception e1) {
			 		System.out.println(e1);
			}
				text.setEditable(false);
			}
			
		});
		
		back.addActionListener(new ActionListener() {
			public  void actionPerformed(ActionEvent e) {
				
				new GUI();
				frame.dispose();
			}
		});
	      frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
