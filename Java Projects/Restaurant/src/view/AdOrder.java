package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Order;


public class AdOrder {
	JTextField name=new JTextField();
	JTextField adr=new JTextField();
	JButton adcom=new JButton("Add Comanda");
	JButton back=new JButton("Back");

	static ArrayList<Order> orr = new ArrayList<Order>();
	static ArrayList<String> ob = new ArrayList<String>();
	
	public AdOrder() {
		
		 
		 JFrame frame = new JFrame ("Restaurant");
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 frame.setSize(640, 300);
		 JPanel panel = new JPanel();
		 panel.setLayout(null);
		 
		 JLabel txt = new JLabel("Adaugati o comanda!!");
		 
		 txt.setBounds(20, 10, 320, 50);
		 panel.add(txt);
		 
		 JLabel txt1 = new JLabel("Produs:");
		 
		 txt1.setBounds(20, 60, 320, 50);
		 panel.add(txt1);
		 
		 JLabel txt2 = new JLabel("Adresa:");
		 
		 txt2.setBounds(20, 120, 320, 50);
		 panel.add(txt2);
		 
		 adr.setBounds(70, 130, 120, 40);
		 panel.add(adr);
		 
		 name.setBounds(70, 65, 120, 40);
		 panel.add(name);
			
		 panel.add(adcom).setBounds(40, 200, 130, 40);
			
		 panel.add(back).setBounds(200,200, 100, 40);
		 
		 adcom.addActionListener(new ActionListener() {
				public  void actionPerformed(ActionEvent e) {
					
					String s1=name.getText();
					String s2=adr.getText();
					Order o=new Order(s1,s2);
					
					orr.add(o);
					String r1="Trebuie sa pregatesti "+s1+"!!\n";
					ob.add(r1);
				}	
				
			});
			
			back.addActionListener(new ActionListener() {
				public  void actionPerformed(ActionEvent e) {
					
					new GUI();
					frame.dispose();
				}
			});
	
		 frame.setContentPane(panel);
		 frame.setVisible(true); 
	
	
	}
	public static ArrayList<Order> getOrder(){
		return orr;
	}
	public static ArrayList<String> getObs(){
		return ob;
	}
}
