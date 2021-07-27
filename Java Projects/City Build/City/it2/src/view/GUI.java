package view;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Layer;
import model.Place;

public class GUI extends JFrame implements MouseListener {

	private static final long serialVersionUID = 1L;
	private static JButton addcladire = new JButton("Add build");
	private static JButton addlayer = new JButton("Add layer");
	private static JButton check = new JButton("Check Wether");
	private static JButton listplace = new JButton("Place");
	private static JButton listLayer = new JButton("Layer");
	private static JButton bug = new JButton(new ImageIcon("bug.png"));

	public GUI() {
		this.setSize(1100, 700);
		this.setTitle("City");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		panel.setLayout(null);

		panel.add(addcladire).setBounds(900, 50, 145, 50);
		panel.add(addlayer).setBounds(900, 150, 145, 50);
		panel.add(check).setBounds(900, 250, 145, 50);
		panel.add(listplace).setBounds(900, 350, 145, 50);
		panel.add(listLayer).setBounds(900, 450, 145, 50);
		panel.add(bug).setBounds(1033, 612, 50, 50);

		panel.addMouseListener(new MouseAdapter() {

			public void mousePressed(MouseEvent e) {
				System.out.println(e.getX() + "," + e.getY());
			}
		});

		this.setContentPane(panel);
		addcladire.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				new BuildGUI();

				dispose();
			}
		});
		addlayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				new LayerGUI();

				dispose();
			}
		});
		check.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				new WeatherGUI();
				dispose();
			}
		});
		listplace.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				new PlaceGUI();
				dispose();
			}
		});
		listLayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				new LayerLsGUI();
				dispose();
			}
		});
		bug.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				new BugGUI();
				dispose();
			}
		});
		this.setVisible(true);
	}

	@Override
	public void paint(Graphics g) {

		Layer l1 = new Layer();
		try {
			l1.paint(g);
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}

		Place p1 = new Place();
		try {
			p1.paint(g);
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
