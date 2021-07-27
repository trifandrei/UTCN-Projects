package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Method;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import DB.ConnectionDB;

public class LSOrder {
		@SuppressWarnings("unchecked")
		public LSOrder(){
	        
		       
	        JFrame frame = new JFrame();
	        JTable table = new JTable(); 
	        JButton back=new JButton("Back");
	       
	        Object[] columns = {"Id"," Nume","Prenume","Adresa","Cantitate","Produs"};
	        DefaultTableModel model = new DefaultTableModel();
	        model.setColumnIdentifiers(columns);
	        
	        table.setModel(model);
	        
	        table.setRowHeight(30);
	        
	      
	        JScrollPane pane = new JScrollPane(table);
	        pane.setBounds(0, 0, 880, 200);
	        
	        frame.add(back).setBounds(50, 250, 90, 50);
	        frame.setLayout(null);
	        
	    
	        frame.add(pane);

	        
	        Object[] row = new Object[6];
	        
	        ArrayList<String> arr=new ArrayList<String>();
	        try {
				Method method1=ConnectionDB.class.getDeclaredMethod("getAllO");
				 arr= (ArrayList<String>) method1.invoke(null);
				
		 	}catch(Exception e1) {
		 		System.out.println(e1);
		}
	        
	        // button add row
	    
	         	for(int i=0; i<arr.size();i=i+6) {
	                row[0] = arr.get(i);
	                row[1] = arr.get(i+1);
	                row[2] = arr.get(i+2);
	                row[3] = arr.get(i+3);
	                row[4] = arr.get(i+4);
	                row[5] = arr.get(i+5);
	                model.addRow(row);
	         	}
	         	
	         	back.addActionListener(new ActionListener() {
	    			public  void actionPerformed(ActionEvent e) {
	    				
	    				new GUI();
	    				frame.dispose();
	    			}
	    		});
	       
	        
	        frame.setSize(900,400);
	        frame.setLocationRelativeTo(null);
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setVisible(true);
	        
	    }
}
