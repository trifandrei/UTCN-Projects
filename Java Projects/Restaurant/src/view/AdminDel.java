package view;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.BaseProduct;
import model.CompositeProduct;
import model.MenuItem;

public class AdminDel{
	 
	JTextField name=new JTextField();
	
	JButton delprodus=new JButton("Sterge produs");
	JButton back=new JButton("Back");
	
	MenuItem m1=new CompositeProduct("Meniu ");
	
	public AdminDel() {
		
		 
		 JFrame frame = new JFrame ("Restaurant");
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 frame.setSize(640, 300);
		 JPanel panel = new JPanel();
		 panel.setLayout(null);
		 
		 JLabel txt = new JLabel("Stergeti un produs in meniu!!");
		 
		 txt.setBounds(20, 10, 320, 50);
		 panel.add(txt);
		 
		 JLabel txt1 = new JLabel("Nume:");
		 
		 txt1.setBounds(20, 60, 320, 50);
		 panel.add(txt1);
		 
		
		 
		 name.setBounds(60, 65, 100, 40);
		 panel.add(name);
			
		 panel.add(delprodus).setBounds(40, 200, 130, 40);
			
		 panel.add(back).setBounds(170,200, 100, 40);
		 
		 delprodus.addActionListener(new ActionListener() {
				public  void actionPerformed(ActionEvent e) {
					
					m1.addO(new BaseProduct("varz",20));
					m1.addO(new BaseProduct("ceapa",20));
					String s=name.getText();
				
					Admin.getMeniu().deleteMenuItem(s);
				
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
}
