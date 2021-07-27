package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import Agent.Handler;
import Client.Client;

public class NLPGUI extends JFrame{
	private static final long serialVersionUID = 1L;
	private JButton report = new JButton("Run NLP");
	private JButton back = new JButton("Back");
	private JButton help= new JButton("Help");
	private JButton clear= new JButton("Clear");
	private JTextArea text = new JTextArea();

	String [] items = { "Shop", "House", "Ground", "Hydro" };
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private JComboBox cb = new JComboBox(items);
	public NLPGUI() {
		this.setSize(500, 550);
		this.setTitle(" NLP");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		JLabel txt = new JLabel("Here you can build with your NLP!!");

		JPanel panel = new JPanel();
		panel.setLayout(null);

		panel.add(txt).setBounds(30, -20, 250, 100);
		panel.add(text).setBounds(20, 60, 400, 150);
		panel.add(report).setBounds(30, 250, 100, 50);
		panel.add(clear).setBounds(30, 330, 100, 50);
		panel.add(back).setBounds(150, 250, 100, 50);
		panel.add(help).setBounds(270, 250, 100, 50);
		panel.add(cb).setBounds(380, 250, 100, 50);
		
		this.setContentPane(panel);
		this.setVisible(true);

		report.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(text.getText()==null) {
						JOptionPane.showMessageDialog(report, "Please add some text !!");
					}else {
						
						Handler handler;
						try {
							handler = new Handler(text.getText());
							Client c=new Client(handler.getRequest());
							text.append(c.getMessageFromServer());
						} catch (ClassNotFoundException e2) {
							e2.printStackTrace();
						} catch (IOException e2) {
						
							e2.printStackTrace();
						}
					}
					
				}
			});
		help.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String x = String.valueOf(cb.getSelectedItem());
				if(x.equals("Shop")) {
					text.append("build a shop "+"\n");
				}
				if(x.equals("House")) {
					text.append("build a house "+"\n");
				}
				if(x.equals("Ground")) {
					text.append("build a ground "+"\n");
				}
				if(x.equals("Hydro")) {
					text.append("build a hydro "+"\n");
				}
			}
		});
		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(text.getText()!=null) {
					text.setText("");
				}
			}
		});
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				new GUI();
				dispose();
			}
		});
	}

}
