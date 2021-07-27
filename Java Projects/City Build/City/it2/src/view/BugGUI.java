package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import Client.Client;
import Request.Request;

public class BugGUI extends JFrame {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
	private JButton report = new JButton("Report");
	private JButton back = new JButton("Back");
	private JTextArea text = new JTextArea();

	public BugGUI() {
		this.setSize(400, 400);
		this.setTitle(" Bug report!!");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		JLabel txt = new JLabel("Here you can report a bug!!");

		JPanel panel = new JPanel();
		panel.setLayout(null);

		panel.add(txt).setBounds(30, -20, 250, 100);
		panel.add(text).setBounds(20, 60, 280, 150);
		panel.add(report).setBounds(30, 250, 100, 50);
		panel.add(back).setBounds(150, 250, 100, 50);

		this.setContentPane(panel);
		this.setVisible(true);

		report.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(text.getText());
				if (text.getText().compareTo("") == 0) {
					JOptionPane.showMessageDialog(report, "Please add some bug information !!");

				} else {
					Request r = new Request("PUT", "Bug");
					try {
						Client cl = new Client(r, text.getText());
						JOptionPane.showMessageDialog(report, cl.getMessageFromServer());
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
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
