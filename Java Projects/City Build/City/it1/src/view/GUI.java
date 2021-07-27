package view;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controler.LayerController;
import controler.PlaceController;
import model.District;
import model.Layer;
import model.Place;

public class GUI extends JFrame implements MouseListener {

	private static final long serialVersionUID = 1L;
	private JButton addcladire = new JButton("add build");
	private JButton addlayer = new JButton("add layer");

	public GUI() {
		setSize(1100, 700);
		setTitle("City");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		panel.setLayout(null);

		panel.add(addcladire).setBounds(900, 50, 145, 50);
		panel.add(addlayer).setBounds(900, 150, 145, 50);

		

		panel.addMouseListener(new MouseAdapter() {

			public void mousePressed(MouseEvent e) {
				System.out.println(e.getX() + "," + e.getY());
			}
		});

		setContentPane(panel);
		addcladire.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				BuildGUI view=new BuildGUI();
				
				new PlaceController(view,new Place(),new District());
				view.setVisible(true);
				dispose();
			}
		});
		addlayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LayerGUI view=new LayerGUI();
				
				new LayerController(view,new Layer(),new District());
				view.setVisible(true);
				dispose();
			}
		});

		setVisible(true);
	}

	@Override
	public void paint(Graphics g) {

		Layer l1 = new Layer();
		l1.paint(g);

		Place p1 = new Place();
		p1.paint(g);

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
