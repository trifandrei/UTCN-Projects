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

public class ADClienti {
	
	private  static JTextField nume1=new JTextField();
	private  static JTextField prenume1=new JTextField();
	private  static JTextField adresa1=new JTextField();
	
	private JButton adclient=new JButton("Add client");
	private JButton back=new JButton("Back");
	
	private JTextArea text=new  JTextArea();
	
	public ADClienti() {
		JFrame frame = new JFrame ("Magazin");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(640, 480);
		
		
		JPanel panel1 = new JPanel();
		panel1.setLayout(null);
		
		JLabel txt=new JLabel("Va rog adaugati un client nou!!");
		
		panel1.add(txt).setBounds(60,-20, 200,100);
		
		JLabel nume=new JLabel("Nume");
		
		panel1.add(nume).setBounds(50, 50, 50, 50);
		panel1.add(nume1).setBounds(100, 60, 150, 30);
		
		JLabel prenume=new JLabel("Prenume");
		
		panel1.add(prenume).setBounds(40, 100, 70, 50);
		panel1.add(prenume1).setBounds(100, 110, 150, 30);
		
		JLabel adresa=new JLabel("Adresa");
		
		panel1.add(adresa).setBounds(40, 150, 70, 50);
		panel1.add(adresa1).setBounds(100, 160, 150, 30);
		
		panel1.add(adclient).setBounds(40, 210, 100, 40);
		
		panel1.add(back).setBounds(170,210, 100, 40);
		
		panel1.add(text).setBounds(300, 50, 200, 100);
		
		frame.setContentPane(panel1);
		
		adclient.addActionListener(new ActionListener() {
			public  void actionPerformed(ActionEvent e) {
			
				
				String a1=nume1.getText();
				String a2=prenume1.getText();
				String a3=adresa1.getText();
			
				try {
					Method method1=ConnectionDB.class.getDeclaredMethod("insertClienti",String.class,String.class,String.class);
					method1.invoke(null,a1,a2,a3);
					text.append("Client adaugat!!\n");
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
