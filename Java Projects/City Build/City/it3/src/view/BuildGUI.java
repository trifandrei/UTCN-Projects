package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.JButton;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Client.Client;
import Request.Request;
import rule.Logger;

public class BuildGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField name1 = new JTextField();
	private JTextField x1 = new JTextField();
	private JTextField y1 = new JTextField();
	private JTextField width1 = new JTextField();
	private JTextField height1 = new JTextField();
	private JTextField information1 = new JTextField();
	private JTextField rating1 = new JTextField();

	@SuppressWarnings({ "rawtypes", "unused" })
	private JComboBox cb = new JComboBox();
	@SuppressWarnings({ "rawtypes", "unused" })
	private JComboBox cb1 = new JComboBox();
	private JButton addPlace = new JButton("Add place");
	private JButton back = new JButton("Back");

	private JTextArea text = new JTextArea();

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public BuildGUI() {

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(700, 600);

		JPanel panel1 = new JPanel();
		panel1.setLayout(null);

		JLabel txt = new JLabel("Here you can add a new place!!");

		panel1.add(txt).setBounds(60, -20, 250, 100);

		JLabel name = new JLabel("Name");

		panel1.add(name).setBounds(50, 50, 50, 50);
		panel1.add(name1).setBounds(100, 60, 170, 30);

		JLabel xcoord = new JLabel("X");

		panel1.add(xcoord).setBounds(50, 100, 70, 50);
		panel1.add(x1).setBounds(120, 110, 50, 30);

		JLabel ycoord = new JLabel("Y");

		panel1.add(ycoord).setBounds(180, 100, 70, 50);
		panel1.add(y1).setBounds(220, 110, 50, 30);

		JLabel width = new JLabel("Width");

		panel1.add(width).setBounds(50, 150, 70, 50);
		panel1.add(width1).setBounds(120, 160, 50, 30);

		JLabel height = new JLabel("Height");

		panel1.add(height).setBounds(180, 150, 70, 50);
		panel1.add(height1).setBounds(220, 160, 50, 30);

		JLabel information = new JLabel("Information");

		panel1.add(information).setBounds(50, 200, 70, 50);
		panel1.add(information1).setBounds(120, 210, 50, 30);

		JLabel rating = new JLabel("Rating");

		panel1.add(rating).setBounds(180, 200, 70, 50);
		panel1.add(rating1).setBounds(220, 210, 50, 30);

		JLabel txt1 = new JLabel("Please select the type of the place and the district!!");

		panel1.add(txt1).setBounds(50, 250, 300, 50);

		JComboBox cb = new JComboBox();
		cb.addItem(inverseType("house0"));
		cb.addItem(inverseType("shop1"));

		panel1.add(cb).setBounds(50, 300, 100, 50);
		
		Request r=new Request("GET","District");
		Client cl = null;
		try {
			cl = new Client(r);
		} catch (ClassNotFoundException e2) {

			e2.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		JComboBox cb1 = new JComboBox(cl.getArr());
		panel1.add(cb1).setBounds(200, 300, 100, 50);
		
		// panel1.add(checkBox2).setBounds(150, 350, 100, 50);

		panel1.add(addPlace).setBounds(40, 450, 100, 40);

		panel1.add(back).setBounds(170, 450, 100, 40);

		panel1.add(text).setBounds(350, 50, 310, 200);

		this.setContentPane(panel1);

		Logger lg = new Logger();

		int idd = lg.districtLogg();
		text.append("Ca recomandare va rugam sa construiti in Districtul " + idd + " !!\n");
		addPlace.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String a1 = name1.getText();
				String a6 = information1.getText();

				int a2 = 0, a3 = 0, a4 = 0, a5 = 0, a7 = 0, a8 = 0, a9 = 0;

				String x = String.valueOf(cb.getSelectedItem());
				String y = String.valueOf(cb1.getSelectedItem());

				try {
					a2 = Integer.parseInt(x1.getText());
					a3 = Integer.parseInt(y1.getText());
					a4 = Integer.parseInt(width1.getText());
					a5 = Integer.parseInt(height1.getText());
					a7 = Integer.parseInt(rating1.getText());
					a8 = Integer.parseInt(y.substring(0, 1));
					a9 = Integer.parseInt(x.substring(0, 1));

				} catch (NumberFormatException e1) {
					e1.printStackTrace();
				}
				Request r=new Request("PUT","Place");
				r.setDst(a8);
				r.setLt(a9);
				Client cl;
				try {
					cl = new Client(r,a1, a2, a3, a4, a5, a6, a7);
					text.append(cl.getMessageFromServer());
				} catch (UnknownHostException e1) {

					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}

			}
		});
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				new GUI();
				dispose();
			}
		});
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	public String inverseType(String s) {

		return s.substring(s.length() - 1, s.length()) + "." + s.substring(0, s.length() - 1);
	}
}
