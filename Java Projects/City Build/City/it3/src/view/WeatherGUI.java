package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Client.Client;
import Request.Request;

public class WeatherGUI extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTextField nume = new JTextField();

	private JButton search = new JButton("Search");
	private JButton back = new JButton("Back");
	@SuppressWarnings("rawtypes")
	JComboBox cb;
	@SuppressWarnings("rawtypes")
	JComboBox cb1;

	private JTextArea text = new JTextArea();

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public WeatherGUI() {
		this.setTitle("Weather");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(700, 600);

		JPanel panel1 = new JPanel();
		panel1.setLayout(null);

		JLabel txt = new JLabel("Here you can add check the wether in a city!!");

		panel1.add(txt).setBounds(50, -20, 300, 100);

		JLabel l = new JLabel("Nume Oras");

		panel1.add(l).setBounds(50, 20, 70, 100);
		panel1.add(nume).setBounds(120, 60, 100, 30);

		JLabel txt1 = new JLabel("Please select what you want to search!!");

		panel1.add(txt1).setBounds(50, 150, 300, 50);

		String[] op = { "main", "sys", "coord", "wind" };

		cb = new JComboBox(op);

		panel1.add(cb).setBounds(80, 200, 100, 50);

		String[] opm = { "main.temp", "main.feels_like", "main.temp_min", "main.temp_max", "main.pressure",
				"main.humidity", "sys.country", "sys.sunrise", "sys.sunset", "coord.lon", "coord.lat", "wind.speed",
				"wind.deg" };

		cb1 = new JComboBox(opm);

		panel1.add(cb1).setBounds(200, 200, 120, 50);

		panel1.add(search).setBounds(40, 450, 100, 40);

		panel1.add(back).setBounds(170, 450, 100, 40);

		panel1.add(text).setBounds(350, 100, 300, 200);

		this.setContentPane(panel1);
		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String s = nume.getText();

				String s1 = String.valueOf(cb.getSelectedItem());
				String s2 = String.valueOf(cb1.getSelectedItem());
				String pars = s2.substring(s2.indexOf(".") + 1, s2.length());
				Request r = new Request("GET", "Weather");
				try {
					Client cl = new Client(r, s1, pars, s);
					text.append(cl.getMessageFromServer());
				} catch (ClassNotFoundException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new GUI().repaint();
				dispose();
			}
		});
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}
