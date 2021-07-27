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

public class ADProdus {
	
	
	private  static JTextField nume1=new JTextField();
	private  static JTextField pret1=new JTextField();
	private  static JTextField cantitate1=new JTextField();
	
	private JButton adprodus=new JButton("Add produs");
	private JButton back=new JButton("Back");
	
	private JTextArea text=new  JTextArea();
	
	public ADProdus() {
		JFrame frame = new JFrame ("Magazin");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(640, 480);
		
		
		JPanel panel1 = new JPanel();
		panel1.setLayout(null);
		
		JLabel txt=new JLabel("Va rog adaugati un produs nou in stoc!!");
		
		panel1.add(txt).setBounds(60,-20, 250,100);
		
		JLabel nume=new JLabel("Nume");
		
		panel1.add(nume).setBounds(50, 50, 50, 50);
		panel1.add(nume1).setBounds(100, 60, 150, 30);
		
		JLabel pret=new JLabel("Pret");
		
		panel1.add(pret).setBounds(50, 100, 70, 50);
		panel1.add(pret1).setBounds(100, 110, 150, 30);
		
		JLabel cantitate=new JLabel("Cantitate");
		
		panel1.add(cantitate).setBounds(40, 150, 70, 50);
		panel1.add(cantitate1).setBounds(100, 160, 150, 30);
		
		panel1.add(adprodus).setBounds(40, 210, 100, 40);
		
		panel1.add(back).setBounds(170,210, 100, 40);
		
		panel1.add(text).setBounds(300, 50, 200, 100);
		
		frame.setContentPane(panel1);
		
		adprodus.addActionListener(new ActionListener() {
			public  void actionPerformed(ActionEvent e) {
			
				
				String a1=nume1.getText();
				String a2=pret1.getText();
				String a3=cantitate1.getText();
				
				try {
					Method method1=ConnectionDB.class.getDeclaredMethod("insertProduse",String.class,String.class,String.class);
					method1.invoke(null,a1,a2,a3);
					text.append("Produs adaugat in stoc!!\n");
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
