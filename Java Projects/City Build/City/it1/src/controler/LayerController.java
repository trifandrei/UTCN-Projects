package controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


import model.District;
import model.Layer;
import model.LayerType;

import model.Type;
import view.GUI;
import view.LayerGUI;

public class LayerController {
	private LayerGUI view;
	private Layer model;
	private District model2;

	@SuppressWarnings("unchecked")
	public LayerController(LayerGUI view, Layer model,District model2) {
		this.view = view;
		this.model = model;
		this.model2=model2;

		view.backLayerListener(new Listener());
		view.addLayerListener(new Listener());

	
		view.getCb().addItem(inverseType(Type.hydro0.name()));
		view.getCb().addItem(inverseType(Type.ground1.name()));

		String[] s = model2.getIdAndName();
		for (int i = 0; i < s.length; i++) {
			view.getCb1().addItem(s[i]);
		}
	}

	public String inverseType(String s) {
		
		return s.substring(s.length() - 1, s.length()) + "." + s.substring(0, s.length() - 1);
	}
	
	class Listener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == view.getBack()) {
				new GUI().repaint();
				view.dispose();
			} else 
			if (e.getSource() == view.getAddLayer()) {
				SessionFactory factor = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

				Session session = factor.getCurrentSession();

				int a2 = 0, a3 = 0, a4 = 0, a5 = 0, a8 = 0, a9 = 0;
			
				LayerType pt = new LayerType();
				
				
				String x = String.valueOf(view.getCb().getSelectedItem());
				String y = String.valueOf(view.getCb1().getSelectedItem());

				try {
					try {
						a2 = Integer.parseInt(view.getX1().getText());
						a3 = Integer.parseInt(view.getY1().getText());
						a4 = Integer.parseInt(view.getWidth1().getText());
						a5 = Integer.parseInt(view.getHeight1().getText());

						a8 = Integer.parseInt(y.substring(0, 1));
						a9 = Integer.parseInt(x.substring(0, 1));
						
						for(LayerType t :pt.selectAllLayerType()) {
							if(t.getType()==a9) {
								pt=t;
							}
						}
					} catch (NumberFormatException e1) {
						e1.printStackTrace();
					}

					session.beginTransaction();
					model2 = session.get(District.class, a8);
					session.getTransaction().commit();

				} finally {
					factor.close();
				}

			
				model.setX(a2);
				model.setY(a3);
				model.setWidth(a4);
				model.setHeight(a5);
				model.setDistrictId(model2);
				model.setLayerType(pt);

				Rule rule = new Rule();

				if (rule.checkHidroUnderBuild(model)) {
					view.getText().append("The layer it will be build under a building!!\n");
				} else {
					model.insertLayer();
					view.getText().append("The layer was inserted !!\n");
				}
			}
		}

	}

}
