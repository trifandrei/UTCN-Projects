package view;

import java.awt.event.ActionListener;

import javax.swing.JButton;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controler.Logger;

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

	@SuppressWarnings("rawtypes")
	private JComboBox cb = new JComboBox();
	@SuppressWarnings("rawtypes")
	private JComboBox cb1 = new JComboBox();
	private JButton addPlace = new JButton("Add place");
	private JButton back = new JButton("Back");

	private JTextArea text = new JTextArea();

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

		// PlaceType pt1 = new PlaceType();
		// JComboBox cb = new JComboBox(Type.values());

		panel1.add(cb).setBounds(50, 300, 100, 50);

		// District d = new District();
		// JComboBox cb1 = new JComboBox(d.getIdAndName());
		panel1.add(cb1).setBounds(200, 300, 100, 50);

		// panel1.add(checkBox2).setBounds(150, 350, 100, 50);

		panel1.add(addPlace).setBounds(40, 450, 100, 40);

		panel1.add(back).setBounds(170, 450, 100, 40);

		panel1.add(text).setBounds(350, 50, 310, 200);

		this.setContentPane(panel1);

		Logger lg = new Logger();

		int idd = lg.districtLogg();
		text.append("Ca recomandare va rugam sa construiti in Districtul " + idd + " !!\n");

		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	public JTextField getName1() {
		return name1;
	}

	public JTextField getX1() {
		return x1;
	}

	public JTextField getY1() {
		return y1;
	}

	public JTextField getWidth1() {
		return width1;
	}

	public JTextField getHeight1() {
		return height1;
	}

	public JTextField getInformation1() {
		return information1;
	}

	public JTextField getRating1() {
		return rating1;
	}

	public JTextArea getText() {
		return text;
	}

	public JButton getAddPlace() {
		return addPlace;
	}

	public JButton getBack() {
		return back;
	}

	public void backPlaceListener(ActionListener backListener) {
		back.addActionListener(backListener);
		this.dispose();

	}

	public void addPlaceListener(ActionListener addlistener) {
		addPlace.addActionListener(addlistener);
	}

	@SuppressWarnings("rawtypes")
	public JComboBox getCb() {
		return cb;
	}

	@SuppressWarnings("rawtypes")
	public JComboBox getCb1() {
		return cb1;
	}

}
