package view;



import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Layer;
import model.Place;


public class GUI extends JFrame implements MouseListener {

	int x;
	int y;
	private static final long serialVersionUID = 1L;
	private JPanel panel=new JPanel();
	private JButton adclient=new JButton("Adauga clienti");
	
	public GUI() {
		setSize(900, 700);
		setTitle("City");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		/*
		panel.setLayout(null);
		panel.add(adclient).setBounds(900,50,115,50);
		adclient.setVisible(true);
		
		
		
		setContentPane(panel);
		adclient.addActionListener(new ActionListener() {
			public  void actionPerformed(ActionEvent e) {
				
				
				dispose();
			}
		});
		*/
		setVisible(true);
	}

	
@Override
	public void paint(Graphics g) {
	
	Layer l1=new Layer();
	l1.paint(g);
	
	Place p1=new Place();
	p1.paint(g);
	

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
