package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.CompositeProduct;
import model.MenuItem;

public class AdminOP {
	static MenuItem m1=new CompositeProduct("Meniu");
	public AdminOP() {
		

		
		JButton adp=new JButton("Adauga produs");
		JButton delp=new JButton("Sterge produs");
		JButton viewp=new JButton("View ");
		
		JFrame frame = new JFrame ("Restaurant");
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 frame.setSize(640, 300);

		 
		 JPanel panel = new JPanel();
		 
		panel.setLayout(null);
		
		panel.add(adp);
		adp.setBounds(50, 50, 130, 100);
		
		panel.add(delp);
		delp.setBounds(250, 50, 130, 100);
		
		panel.add(viewp);
		viewp.setBounds(450, 50, 130, 100);
		
		adp.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent arg0) {
	              new Admin();
	              frame.dispose();
	            }

				
	        });
		
		delp.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent arg0) {
	        	new AdminDel();
	              frame.dispose();
	            }

				
	        });
		
		viewp.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent arg0) {
	        	  new AdminView();
	              frame.dispose();
	              try {
						m1=readfile();
					} catch (ClassNotFoundException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
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
}
