package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


import model.Order;

public class OrderView {
	  ArrayList<Order> orr = new ArrayList<Order>();
	public OrderView(){
        
	       
        JFrame frame = new JFrame();
        JTable table = new JTable(); 
        JButton back=new JButton("Back");
        JButton bill=new JButton("Bill");
        
        JTextField name=new JTextField();
    
    
        
        Object[] columns = {"Id"," Nume","Adresa"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);
        
        table.setModel(model);
        
        table.setRowHeight(30);
        

		 JLabel txt1 = new JLabel("Id:");
		 
		 txt1.setBounds(150, 200, 90, 50);
		 frame.add(txt1);

		 bill.setBounds(200, 250, 100, 40);
		 frame.add(bill);
		
		 name.setBounds(200, 200, 100, 40);
		 frame.add(name);
		
        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(0, 0, 880, 200);
        
        frame.add(back).setBounds(50, 250, 90, 50);
        frame.setLayout(null);
        
    
        frame.add(pane);

        
        Object[] row = new Object[3];
        
        orr=AdOrder.getOrder();
    
         	for(int i=0; i< orr.size();i=i+1) {
         		row[0]=i;
                row[1] =orr.get(i).getName();
                row[2] =orr.get(i).getAdress();              
                model.addRow(row);
         	}
         	
         	back.addActionListener(new ActionListener() {
    			public  void actionPerformed(ActionEvent e) {
    				
    				new GUI();
    				frame.dispose();
    			}
    		});

         	bill.addActionListener(new ActionListener() {
    			public  void actionPerformed(ActionEvent e) {
    			 String fil="out.txt";
    			 try {
					PrintWriter ouput=new PrintWriter(fil);
					
					String s=name.getText();
					int d=Integer.parseInt(s);
					
					ouput.println("################################\n");
					ouput.println("Produs:"+orr.get(d).getName()+"\n");
					ouput.println("Adresa:"+orr.get(d).getAdress()+"\n");
					ouput.println("Total:"+getPrice(orr.get(d).getName())+"\n");
					ouput.println("################################\n");
					ouput.close();
					
				} catch (FileNotFoundException e1) {
					
					e1.printStackTrace();
				}
    				
    			}
    		});
        	
        
        frame.setSize(900,400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
    }
	public double getPrice(String t1) {
		for(int i=0;i<Admin.getMeniu().size();i++) {
			if(Admin.getMeniu().getI(i).getNume().equals(t1)) {
				return Admin.getMeniu().getI(i).getPret();
			}
		}
		return 0;
	}
	
}
