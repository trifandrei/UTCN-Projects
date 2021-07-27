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

public class ADOrder {
	
	private  static JTextField nume1=new JTextField();
	private  static JTextField prenume1=new JTextField();
	private  static JTextField adresa1=new JTextField();
	private  static JTextField cantitate1=new JTextField();
	private static JTextField produs1=new JTextField();
	
	private JButton adorder=new JButton("Add Order");
	private JButton back=new JButton("Back");
	
	private JTextArea text=new  JTextArea();
	
	public ADOrder() {
		JFrame frame = new JFrame ("Magazin");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(640, 480);
		
		
		JPanel panel1 = new JPanel();
		panel1.setLayout(null);
		
		JLabel txt=new JLabel("Va rog adaugati o comanda!!");
		
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
		
		JLabel cantitate=new JLabel("Cantitate");
		
		panel1.add(cantitate).setBounds(40, 200, 70, 50);
		panel1.add(cantitate1).setBounds(100, 210, 150, 30);
		
		JLabel produs=new JLabel("Produs");
		
		panel1.add(produs).setBounds(40, 250, 70, 50);
		panel1.add(produs1).setBounds(100, 260, 150, 30);
		
		panel1.add(adorder).setBounds(40, 310, 100, 40);
		
		panel1.add(back).setBounds(170,310, 100, 40);
		
		panel1.add(text).setBounds(300, 50, 300, 100);
		
		frame.setContentPane(panel1);
		
		adorder.addActionListener(new ActionListener() {
			public  void actionPerformed(ActionEvent e) {
				

				String a1=nume1.getText();
				String a2=prenume1.getText();
				String a3=adresa1.getText();
				String a4=cantitate1.getText();
				String a5=produs1.getText();
				String d = null ;
				
				try {
					Method method1=ConnectionDB.class.getDeclaredMethod("getCantitate",String.class);
					 d = (String) method1.invoke(null,a5);
					
			 	}catch(Exception e1) {
			 		System.out.println(e1);
			}
				
				int c1=Integer.parseInt(a4);
				int c2=Integer.parseInt(d);
				if(c2>c1) {
				try {
					Method method1=ConnectionDB.class.getDeclaredMethod("insertOrder",String.class,String.class,String.class,String.class,String.class);
					method1.invoke(null,a1,a2,a3,a4,a5);
					text.append("Comanda adaugata!!\n");
			 	}catch(Exception e1) {
			 		System.out.println(e1);
			}
				int cantnou=c2-c1;
				try {
					Method method1=ConnectionDB.class.getDeclaredMethod("updateCantitate",int.class,String.class);
					method1.invoke(null,cantnou,a5);
					text.append("Cantitate actualizata!!\n");
			 	}catch(Exception e1) {
			 		System.out.println(e1);
			}
				
				}
				else
				{
					new Worning();
					
				}
				String pret1 = null;
				try {
					Method method1=ConnectionDB.class.getDeclaredMethod("getPret",String.class);
					 pret1 = (String) method1.invoke(null,a5);
					
						int pret=Integer.parseInt(pret1);
						int total=pret*c1;
						
						String str=String.valueOf(total);
						text.append("Comanda dumneavoastra este in valoare de: "+str+" lei!\n");
						
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
