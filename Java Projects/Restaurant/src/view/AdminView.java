package view;
import view.Admin;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


public class AdminView{
	


public AdminView(){
        
       
        JFrame frame = new JFrame();
        JTable table = new JTable(); 
        JButton back=new JButton("Back");
        JButton edit=new JButton("Edit");
        JTextField name=new JTextField();
    	JTextField price=new JTextField();
    	JTextField name2=new JTextField();
    	JTextField price2=new JTextField();
        
        Object[] columns = {"Id"," Nume","Pret"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);
        
        table.setModel(model);
        
        table.setRowHeight(30);
        

		 JLabel txt1 = new JLabel("Nume:");
		 
		 txt1.setBounds(150, 200, 90, 50);
		 frame.add(txt1);
		 
		 JLabel txt2 = new JLabel("Pret:");
		 
		 txt2.setBounds(150, 250, 320, 50);
		 frame.add(txt2);
		 
		 price.setBounds(200, 250, 100, 40);
		 frame.add(price);
		 price2.setBounds(300, 250, 100, 40);
		 frame.add(price2);
		 
		 edit.setBounds(400, 250, 90, 50);
		 frame.add(edit);
		 
		 name.setBounds(200, 200, 100, 40);
		 frame.add(name);
		 
		 name2.setBounds(300, 200, 100, 40);
		 frame.add(name2);
		 
        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(0, 0, 880, 200);
        
        frame.add(back).setBounds(50, 250, 90, 50);
        frame.setLayout(null);
        
    
        frame.add(pane);

        
        Object[] row = new Object[3];
        
     
    
    
         	for(int i=0; i<  Admin.getMeniu().size();i=i+1) {
         		row[0]=i+1;
                row[1] =   Admin.getMeniu().getI(i).getNume();
                row[2] =   Admin.getMeniu().getI(i).getPret();
              
                model.addRow(row);
         	}
         	
         	back.addActionListener(new ActionListener() {
    			public  void actionPerformed(ActionEvent e) {
    				
    				new GUI();
    				frame.dispose();
    			}
    		});
        	edit.addActionListener(new ActionListener() {
    			public  void actionPerformed(ActionEvent e) {
    				
    				
    				new GUI();
    				frame.dispose();
    				
			
					String s=name.getText();
					String s2=price.getText();
					double d=Double.parseDouble(s2);
					
					String s1=name2.getText();
					String s3=price2.getText();
					double d1=Double.parseDouble(s3);
					
					Admin.getMeniu().editMenuItem(s, d, s1, d1);
    			}
    		});
        
        frame.setSize(900,400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
    }
}

