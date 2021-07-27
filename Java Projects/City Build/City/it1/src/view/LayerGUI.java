package view;


import java.awt.event.ActionListener;

import javax.swing.JButton;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;



public class LayerGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private  JTextField x1 = new JTextField();
	private  JTextField y1 = new JTextField();
	private  JTextField width1 = new JTextField();
	private  JTextField height1 = new JTextField();

	private JButton addLayer = new JButton("Add Layer");
	private JButton back = new JButton("Back");
	
	@SuppressWarnings("rawtypes")
	private JComboBox cb = new JComboBox();
	@SuppressWarnings("rawtypes")
	private JComboBox cb1 = new JComboBox();
	
	private JTextArea text = new JTextArea();

	
	public LayerGUI() {
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(700, 600);

		JPanel panel1 = new JPanel();
		panel1.setLayout(null);

		JLabel txt = new JLabel("Here you can add a new layer!!");

		panel1.add(txt).setBounds(100, -20, 250, 100);

		JLabel xcoord = new JLabel("X");

		panel1.add(xcoord).setBounds(50, 50, 70, 50);
		panel1.add(x1).setBounds(120, 60, 50, 30);

		JLabel ycoord = new JLabel("Y");

		panel1.add(ycoord).setBounds(180, 50, 70, 50);
		panel1.add(y1).setBounds(220, 60, 50, 30);

		JLabel width = new JLabel("Width");

		panel1.add(width).setBounds(50, 100, 70, 50);
		panel1.add(width1).setBounds(120, 110, 50, 30);

		JLabel height = new JLabel("Height");

		panel1.add(height).setBounds(180, 100, 70, 50);
		panel1.add(height1).setBounds(220, 110, 50, 30);

		JLabel txt1 = new JLabel("Please select the type of the layer and the district!!");

		panel1.add(txt1).setBounds(50, 150, 300, 50);
/*
		LayerType lt1 = new LayerType();
		JComboBox cb = new JComboBox(lt1.getIdAndName());*/
		panel1.add(cb).setBounds(50, 200, 100, 50);

		panel1.add(cb1).setBounds(200, 200, 100, 50);
		
		panel1.add(addLayer).setBounds(40, 450, 100, 40);

		panel1.add(back).setBounds(170, 450, 100, 40);

		panel1.add(text).setBounds(350, 50, 300, 200);

		this.setContentPane(panel1);
		
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	public void backLayerListener(ActionListener backListener) {
		back.addActionListener(backListener);
		this.dispose();

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
	public JTextArea getText() {
		return text;
	}
	public void addLayerListener(ActionListener backListener) {
		addLayer.addActionListener(backListener);
	

	}
	@SuppressWarnings("rawtypes")
	public JComboBox getCb() {
		return cb;
	}
	@SuppressWarnings("rawtypes")
	public JComboBox getCb1() {
		return cb1;
	}
	public JButton getAddLayer() {
		return addLayer;
	}
	public JButton getBack() {
		return back;
	}
}
