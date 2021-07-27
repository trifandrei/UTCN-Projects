package view;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import model.Planificare;



public class window extends JFrame {

	

	private static final long serialVersionUID = 1L;
	
	private  static JTextField minatime=new JTextField();
	private  static JTextField maxatime=new JTextField();
	private  static JTextField minstime=new JTextField();
	private  static JTextField maxstime=new JTextField();
	private  static JTextField timptf=new JTextField();

	private static JTextArea text = new JTextArea();

	
	private JButton start=new JButton("Start");
	private JButton clear=new JButton("Curata!");
	
	ScheduledExecutorService sim = Executors.newScheduledThreadPool(1);
	private Runnable simulator= new Runnable(){
		public  void run(){
			new Planificare();
		}
	};

	public window() {
		JFrame frame = new JFrame ("Simulator");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(640, 480);
		
		
		JPanel panel1 = new JPanel();
		
		panel1.setLayout(null);
		
		JLabel minAtime=new JLabel("Introduceti timpul minim de sosire:");
		JLabel maxAtime=new JLabel("Introduceti timpul maxim de sosire:");
		
		
		panel1.add(minAtime).setBounds(20, 10, 200, 20);
		panel1.add(minatime).setBounds(250, 10, 160, 30);
		panel1.add(maxAtime).setBounds(20, 55, 200, 20);
		panel1.add(maxatime).setBounds(250, 50, 160, 30);
		
		JLabel minStime=new JLabel("Introduceti timpul minim de servire:");
		JLabel maxStime=new JLabel("Introduceti timpul maxim de servire:");
		
		panel1.add(minStime).setBounds(20, 90, 200, 30);
		panel1.add(minstime).setBounds(250, 90, 160, 30);
		
		panel1.add(maxStime).setBounds(20, 130, 210, 30);
		panel1.add(maxstime).setBounds(250, 130, 160, 30);
		
		JLabel timp=new JLabel("Introduceti timpul de simulare:");
		
		panel1.add(timp).setBounds(20, 175, 200, 20);
		panel1.add(timptf).setBounds(250, 170, 160, 30);
		
		panel1.add(start).setBounds(450,90,90,50);
		panel1.add(clear).setBounds(450,160,90,50);
		
		panel1.add(text).setBounds(60, 250, 500, 180);
		frame.setContentPane(panel1);
		
		

		start.addActionListener(new ActionListener() {
			public  void actionPerformed(ActionEvent e) {
				sim.execute(simulator);
				
			}
		});
		
		clear.addActionListener(new ActionListener() {
			public  void actionPerformed(ActionEvent e) {
				
				text.setText(null);
			}
		});
		frame.setVisible(true);
	}
	
	public static void show(String text1) {
		text.append(text1+ "\n");
		text.setEditable(false);
	}
	public static int getMinSosire() {
		String s1=minatime.getText();
		int minSosire=Integer.parseInt(s1);
		return minSosire;
	}
	public static int getMaxSosire() {
		String s2=maxatime.getText();
		int maxSosire=Integer.parseInt(s2);
		return maxSosire;
	}
	public static int getMinServire() {
		String s3=minstime.getText();
		int minServire=Integer.parseInt(s3);
		return minServire;
	}
	public static int getMaxServire() {
		String s4=maxstime.getText();
		int maxServire=Integer.parseInt(s4);
		return maxServire;
	}
	public static int getTimp(){
		String s5=timptf.getText();
		int timp=Integer.parseInt(s5);
		return timp;
	}
}