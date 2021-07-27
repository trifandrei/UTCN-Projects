package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;



public class Chef {
	JTextArea txt=new JTextArea();
	  ArrayList<String> ob = AdOrder.getObs();
	  JButton back=new JButton("Back");
	public Chef() {
		
		
		 JFrame frame = new JFrame ("Restaurant");
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 frame.setSize(640, 300);

		 
		 JPanel panel = new JPanel();
		 
		 panel.setLayout(null);
		 
		 JLabel text=new JLabel("Comenzile pe care cheful le pregateste!!");
		 
		 text.setBounds(50, 10, 300, 50);
		 panel.add(text);
		 
		 txt.setBounds(50, 80, 250, 150);
		 panel.add(txt);
		 
			
		 panel.add(back).setBounds(400,100, 100, 40);
		 
		 for(int i = 0;i<ob.size();i++) {
			 txt.append(ob.get(i));
		 }
		 
		 
		 frame.setContentPane(panel);
		 frame.setVisible(true); 
	
		 back.addActionListener(new ActionListener() {
 			public  void actionPerformed(ActionEvent e) {
 				
 				new GUI();
 				frame.dispose();
 			}
 		});
	
	}
	
}
