package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUI extends JFrame{

	private static final long serialVersionUID = 1L;

	public GUI() {
		

		
			JButton admin=new JButton("Administrator");
			JButton waiter=new JButton("Waiter");
			JButton chef=new JButton("Chef");
			
			JFrame frame = new JFrame ("Restaurant");
			 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			 frame.setSize(640, 300);

			 
			 JPanel panel = new JPanel();
			 
			panel.setLayout(null);
			
			panel.add(admin);
			admin.setBounds(50, 50, 130, 100);
			
			panel.add(waiter);
			waiter.setBounds(250, 50, 130, 100);
			
			panel.add(chef);
			chef.setBounds(450, 50, 130, 100);
			
			admin.addActionListener(new ActionListener() {
		         public void actionPerformed(ActionEvent arg0) {
		              new AdminOP();
		              frame.dispose();
		            }

					
		        });
			
			waiter.addActionListener(new ActionListener() {
		         public void actionPerformed(ActionEvent arg0) {
		              new Waiter();
		              frame.dispose();
		            }

					
		        });
			
			chef.addActionListener(new ActionListener() {
		         public void actionPerformed(ActionEvent arg0) {
		        	  new Chef();
		              frame.dispose();
		            }

					
		        });
			
			 frame.setContentPane(panel);
			 frame.setVisible(true); 
		
		
		
	}
}
