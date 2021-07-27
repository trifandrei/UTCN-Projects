package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Client.Client;
import Request.Request;

public class DeleteLayer extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txt1 = new JTextField();
	private JButton back = new JButton("Back");
	private JButton dell = new JButton("Delete");
	private JTextArea text = new JTextArea();

	public DeleteLayer() {

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(600, 300);

		JPanel panel1 = new JPanel();
		panel1.setLayout(null);

		JLabel txt = new JLabel("Here you can delete a Layer!!");

		panel1.add(txt).setBounds(60, -20, 250, 100);

		JLabel label = new JLabel("Layer id:");

		panel1.add(label).setBounds(50, 50, 50, 50);
		panel1.add(txt1).setBounds(100, 60, 170, 30);

		panel1.add(text).setBounds(320, 10, 250, 200);
		panel1.add(dell).setBounds(200, 150, 100, 40);
		panel1.add(back).setBounds(50, 150, 100, 40);

		this.setContentPane(panel1);

		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				new LayerLsGUI();
				dispose();
			}
		});
		dell.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Request r = new Request("DELETE", "Layer");
				try {
					Client cl = new Client(r, txt1.getText());
					text.append(cl.getMessageFromServer());
				} catch (ClassNotFoundException e1) {

					e1.printStackTrace();
				} catch (IOException e1) {

					e1.printStackTrace();
				}

			}

		});
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}
