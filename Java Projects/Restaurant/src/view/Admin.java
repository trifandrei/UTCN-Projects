package view;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.CompositeProduct;
import model.MenuItem;

public class Admin implements Serializable {
	private static final long serialVersionUID = 4602018641249748219L;
	 
	JTextField name=new JTextField();
	JTextField price=new JTextField();
	JButton adprodus=new JButton("Add produs");
	JButton back=new JButton("Back");
	
	static MenuItem m1=new CompositeProduct("Meniu");
	
	public Admin() {
		
		 
		 JFrame frame = new JFrame ("Restaurant");
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 frame.setSize(640, 300);
		 JPanel panel = new JPanel();
		 panel.setLayout(null);
		 
		 JLabel txt = new JLabel("Adaugati un produs in meniu!!");
		 
		 txt.setBounds(20, 10, 320, 50);
		 panel.add(txt);
		 
		 JLabel txt1 = new JLabel("Nume:");
		 
		 txt1.setBounds(20, 60, 320, 50);
		 panel.add(txt1);
		 
		 JLabel txt2 = new JLabel("Pret:");
		 
		 txt2.setBounds(20, 120, 320, 50);
		 panel.add(txt2);
		 
		 price.setBounds(60, 130, 100, 40);
		 panel.add(price);
		 
		 name.setBounds(60, 65, 100, 40);
		 panel.add(name);
			
		 panel.add(adprodus).setBounds(40, 200, 100, 40);
			
		 panel.add(back).setBounds(170,200, 100, 40);
		 
		 adprodus.addActionListener(new ActionListener() {
				public  void actionPerformed(ActionEvent e) {
					
					
					
					String s=name.getText();
					String p=price.getText();
					double d=Double.parseDouble(p);
					m1.createMenuItem(s, d);
					
					try {
						writefile(m1);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
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
	public static MenuItem  readfile() throws  IOException, ClassNotFoundException {
		
		@SuppressWarnings("resource")
		ObjectInputStream obj=new ObjectInputStream(new FileInputStream("restaurant.ser"));
		MenuItem m=  (MenuItem) obj.readObject();
	
		return m;
	}
	public static void writefile(MenuItem  set) throws  IOException {
		
		@SuppressWarnings("resource")
		ObjectOutputStream obj=new ObjectOutputStream(new FileOutputStream("restaurant.ser"));
		
		obj.writeObject(set);
	}
	public static MenuItem getMeniu() {
		return m1;
	}
	
}
