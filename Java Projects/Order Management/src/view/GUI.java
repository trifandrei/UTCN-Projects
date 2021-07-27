package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class GUI extends JFrame {
		
		private static final long serialVersionUID = 1L;
		

		
		private JButton adclient=new JButton("Adauga clienti");
		private JButton delclient=new JButton("Sterge clienti");
		private JButton lisclient=new JButton("Afisaza clienti");
		
		private JButton adprod=new JButton("Adauga produs");
		private JButton delprod=new JButton("Sterge produs");
		private JButton lisprod=new JButton("Afisaza produs");
		
		private JButton adcom=new JButton("Adauga comanda");
		private JButton delcom=new JButton("Sterge comanda");
		private JButton liscom=new JButton("Afisaza comanda");
		
		public GUI() {
			JFrame frame = new JFrame ("Magazin");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(640, 480);
			
			
			JPanel panel1 = new JPanel();
			
			panel1.setLayout(null);
			
		
			
			panel1.add(adclient).setBounds(30,30,115,50);
			panel1.add(delclient).setBounds(30,90,115,50);
			panel1.add(lisclient).setBounds(30,150,115,50);
			
			panel1.add(adprod).setBounds(230,30,120,50);
			panel1.add(delprod).setBounds(230,90,120,50);
			panel1.add(lisprod).setBounds(230,150,120,50);
			
			panel1.add(adcom).setBounds(430,30,135,50);
			panel1.add(delcom).setBounds(430,90,135,50);
			panel1.add(liscom).setBounds(430,150,135,50);
			
			frame.setContentPane(panel1);
			
			

			adclient.addActionListener(new ActionListener() {
				public  void actionPerformed(ActionEvent e) {
					
					new ADClienti();
					frame.dispose();
				}
			});
			
			adprod.addActionListener(new ActionListener() {
				public  void actionPerformed(ActionEvent e) {
					new ADProdus();
					frame.dispose();
				}
			});
			
			adcom.addActionListener(new ActionListener() {
				public  void actionPerformed(ActionEvent e) {
					new ADOrder();
					frame.dispose();
				}
			});
			
			delclient.addActionListener(new ActionListener() {
				public  void actionPerformed(ActionEvent e) {
					new DLClient();
					frame.dispose();
				}
			});
			
			delprod.addActionListener(new ActionListener() {
				public  void actionPerformed(ActionEvent e) {
					new DLProduse();
					frame.dispose();
				}
			});
			
			delcom.addActionListener(new ActionListener() {
				public  void actionPerformed(ActionEvent e) {
					new DLOrder();
					frame.dispose();
				}
			});
			
			lisclient.addActionListener(new ActionListener() {
				public  void actionPerformed(ActionEvent e) {
					new LSClienti();
					frame.dispose();
				}
			});
			
			lisprod.addActionListener(new ActionListener() {
				public  void actionPerformed(ActionEvent e) {
					new LSProduse();
					frame.dispose();
				}
			});
			liscom.addActionListener(new ActionListener() {
				public  void actionPerformed(ActionEvent e) {
					new LSOrder();
					frame.dispose();
				}
			});
		      frame.setLocationRelativeTo(null);
			frame.setVisible(true);
		}
		
	}

