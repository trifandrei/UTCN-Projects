package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import Polynom.Polinom;
import Operatii.Operation;
public class Interfata extends JFrame {
	
	private static final long serialVersionUID = 4225241154365086482L;
	
	 JTextField tf = new JTextField();
	 JTextField tf2 = new JTextField();
	 JTextArea rezultat=new JTextArea();
	 //Crearea butanelor
	 JButton egal=new JButton("=");
	 JRadioButton plusB = new JRadioButton("Adunare");
	 JRadioButton minusB = new JRadioButton("Scadere");
	 JRadioButton mulB = new JRadioButton("Inmultire");
	 JCheckBox pol1 = new JCheckBox("Derivez Polinomul 1!");
	 JCheckBox pol2 = new JCheckBox("Derivez Polinomul 2!");
	 JCheckBox pol11 = new JCheckBox("Integrez Polinomul 1!");
	 JCheckBox pol22 = new JCheckBox("Integrez Polinomul 2!");
	 //Constructor pentru interfata
	public Interfata() {
		JFrame frame = new JFrame ("Calculator Polinomial");
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 frame.setSize(640, 480);

		 //crearea si adaugarea de continut pentru frame
		 JPanel panel1 = new JPanel();
		 
		 JLabel l = new JLabel ("Introduceti primul polinom:");
		
		 panel1.add(l);
		 panel1.add(tf);
		 
		 
		 JLabel l2 = new JLabel ("Introduceti al doilea  polinom: ");
		 panel1.add(l2);
		 panel1.add(tf2);
		
		 
		 JLabel l3 = new JLabel ("Rezultatul este:");
		 panel1.add(l3);
		 panel1.add(rezultat);
		 panel1.setLayout(new GridLayout(4,5));
		 
		 JPanel panel2 = new JPanel();
		 
		 panel2.add(plusB);
		 panel2.add(minusB);
		 panel2.add(mulB);
		 panel2.add(pol1);
		 panel2.add(pol2);
		 panel2.add(pol11);
		 panel2.add(pol22);
		 panel2.setLayout(new FlowLayout(50,60,20));

		 JPanel panel3 = new JPanel();
		 panel3.add(egal);
		 
		 
		 JPanel p = new JPanel();
		 p.add(panel1);
		 p.add(panel2);
		 p.add(panel3);
		 p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
		 
		 //adaugam un asucltator pentru butonul =
		 egal.addActionListener(new ActionListener() {

	            public void actionPerformed(ActionEvent arg0) {
	                Calculeaza();
	            }
	        });
		 //setam continutul framului
		 frame.setContentPane(p);
		 frame.setVisible(true); 
	}
	
	//Metoda care face operatiile
	public void Calculeaza() {
		
		boolean run=true;
		//citim inputurile
		String input1=tf.getText();
		String input2=tf2.getText();
		
		//daca sunt goale nu avem ce face
		if(input1.isEmpty() && input2.isEmpty())
				run=false;
			else 
				run=true;
		// facem diferitele operatii alese de utilizator
		if (run==true)
		{
			Polinom p1=new Polinom();
			Polinom p2=new Polinom();
			
			p1.Polinom1(input1);
			p2.Polinom1(input2);
			
			if(plusB.isSelected()) {
				rezultat.setText(null);
				Polinom r=new Polinom();
				Operation op1=new Operation();
				r=op1.addP(p1, p2);
				rezultat.append(r.toString());
			}
			else 
				if(minusB.isSelected()) {
					rezultat.setText(null);
					Polinom r=new Polinom();
					Operation op1=new Operation();
					r=op1.minusP(p1, p2);
					rezultat.append(r.toString());
				}
				else
				 if(mulB.isSelected()) {
					rezultat.setText(null);
					Polinom r=new Polinom();
					Operation op1=new Operation();
					r=op1.mulP(p1, p2);
					rezultat.append(r.toString());
				}
				else
				  if(pol1.isSelected()) {
					rezultat.setText(null);
					Polinom r=new Polinom();
					Operation op1=new Operation();
					r=op1.derivP(p1);
					rezultat.append(r.toString());
					}
				else
				  if(pol2.isSelected()) {
					rezultat.setText(null);
					Polinom r=new Polinom();
					Operation op1=new Operation();
					r=op1.derivP(p2);
					rezultat.append(r.toString());
				}
				  else
					  if(pol11.isSelected()) {
							rezultat.setText(null);
							Polinom r=new Polinom();
							Operation op1=new Operation();
							r=op1.integP(p1);
							rezultat.append(r.doString());
					}
						else
							if(pol22.isSelected()) {
								rezultat.setText(null);
								Polinom r=new Polinom();
								Operation op1=new Operation();
								r=op1.integP(p2);
								rezultat.append(r.doString());
							}
			} 	
		rezultat.setEditable(false);
	}
}
