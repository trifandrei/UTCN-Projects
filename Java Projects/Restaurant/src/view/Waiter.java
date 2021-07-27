package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Waiter {
	
	public Waiter() {
		JButton adc=new JButton("Adauga Comanda");
		JButton viewc=new JButton("View ");
		
		JFrame frame = new JFrame ("Restaurant");
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 frame.setSize(640, 300);

		 
		 JPanel panel = new JPanel();
		 
		panel.setLayout(null);
		
		panel.add(adc);
		adc.setBounds(50, 50, 150, 100);
		
		
		panel.add(viewc);
		viewc.setBounds(350, 50, 130, 100);
		
		adc.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent arg0) {
	              new AdOrder();
	              frame.dispose();
	            }

				
	        });
	
		viewc.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent arg0) {
	        	  new OrderView();
	              frame.dispose();
	             
				}
	            });
		
		 frame.setContentPane(panel);
		 frame.setVisible(true); 
	
	
	}
}
