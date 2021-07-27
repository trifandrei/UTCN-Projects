package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Client.Client;
import model.Place;
import Request.Request;

public class PlaceGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PlaceGUI() {

		this.setSize(900, 400);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);

		JTable table = new JTable();
		JButton back = new JButton("Back");
		JButton dell = new JButton("Delete Place");

		Object[] columns = { "Id", " Nume", "X", "Y", "Width", "Height", "Information", "Rating", "District_Id",
				"Place_Type" };
		DefaultTableModel model = new DefaultTableModel();
		model.setColumnIdentifiers(columns);

		table.setModel(model);

		table.setRowHeight(30);

		JScrollPane pane = new JScrollPane(table);
		pane.setBounds(0, 0, 880, 200);

		this.add(back).setBounds(50, 250, 90, 50);
		this.add(dell).setBounds(150, 250, 140, 50);
		this.setLayout(null);

		this.add(pane);

		Object[] row = new Object[10];

		ArrayList<Place> arr = new ArrayList<Place>();
		Request r = new Request("POST", "Place");
		try {
			Client c = new Client(r);
			arr = c.getListPlace();
		} catch (Exception e1) {
			System.out.println(e1);
		}

		for (int i = 0; i < arr.size(); i = i + 1) {
			row[0] = arr.get(i).getId();
			row[1] = arr.get(i).getNume();
			row[2] = arr.get(i).getX();
			row[3] = arr.get(i).getY();
			row[4] = arr.get(i).getWidth();
			row[5] = arr.get(i).getHeight();
			row[6] = arr.get(i).getInformation();
			row[7] = arr.get(i).getRating();
			row[8] = arr.get(i).getDistrictId().getId();
			row[9] = arr.get(i).getPlaceTypeId().getId();

			model.addRow(row);
		}
		dell.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				new DeletePlace();
				dispose();
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
