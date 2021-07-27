package controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import model.District;
import model.Place;
import model.PlaceType;
import model.Type;
import view.BuildGUI;
import view.GUI;


public class PlaceController {

	private BuildGUI buildview;
	private Place place;
	private District district;

	@SuppressWarnings("unchecked")
	public PlaceController(BuildGUI buildview, Place place,District district) {
		this.place = place;
		this.buildview = buildview;
		this.district=district;

		buildview.backPlaceListener(new Listener());
		buildview.addPlaceListener(new Listener());
		
		buildview.getCb().addItem(inverseType(Type.house0.name()));
		buildview.getCb().addItem(inverseType(Type.shop1.name()));
		
		String []s=district.getIdAndName();
		for (int i = 0; i < s.length; i++) {
			buildview.getCb1().addItem(s[i]);
		}
		
	}
	public String inverseType(String s) {
		
		return s.substring(s.length() - 1, s.length()) + "." + s.substring(0, s.length() - 1);
	}
	class Listener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == buildview.getBack()) {
				new GUI().repaint();
				buildview.dispose();
			} else 
			if (e.getSource() == buildview.getAddPlace()) {
				SessionFactory factor = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

				Session session = factor.getCurrentSession();

				String a1 = buildview.getName1().getText();
				String a6 = buildview.getInformation1().getText();

				int a2 = 0, a3 = 0, a4 = 0, a5 = 0, a7 = 0, a8 = 0, a9 = 0;
			
				PlaceType pt = new PlaceType();

				boolean ok = false;

				String x = String.valueOf(buildview.getCb().getSelectedItem());
				String y = String.valueOf(buildview.getCb1().getSelectedItem());

				try {
					try {
						a2 = Integer.parseInt(buildview.getX1().getText());
						a3 = Integer.parseInt(buildview.getY1().getText());
						a4 = Integer.parseInt(buildview.getWidth1().getText());
						a5 = Integer.parseInt(buildview.getHeight1().getText());
						a7 = Integer.parseInt(buildview.getRating1().getText());
						a8 = Integer.parseInt(y.substring(0, 1));
						a9 = Integer.parseInt(x.substring(0, 1));
						
						
						for(PlaceType t :pt.selectAllPlaceType()) {
							if(t.getType()==a9) {
								pt=t;
							}
						}
					} catch (NumberFormatException e1) {
						e1.printStackTrace();
					}

					session.beginTransaction();
					district = session.get(District.class, a8);
					session.getTransaction().commit();

				} finally {
					factor.close();
				}

				

				place.setNume(a1);
				place.setX(a2);
				place.setY(a3);
				place.setWidth(a4);
				place.setHeight(a5);
				place.setInformation(a6);
				place.setRating(a7);
				place.setDistrictId(district);
				place.setPlaceTypeId(pt);

				Rule rule = new Rule();
				if (!rule.checkBuildOutOfMap(place)) {
					ok = true;
					buildview.getText().append("The building is out of the map!!\n");
				}
				if (rule.checkBuildOnBuild(place)) {
					ok = true;
					buildview.getText().append("The building it will be build over another build!!\n");
				}
				if (rule.checkBuildOnWater(place)) {
					ok = true;
					buildview.getText().append("The building is on water!!\n");
				}
				if (!rule.checkArea(place)) {
					ok = true;
					buildview.getName1().setText("");
					buildview.getX1().setText("");
					buildview.getY1().setText("");
					buildview.getWidth1().setText("");
					buildview.getHeight1().setText("");
					buildview.getInformation1().setText("");
					buildview.getRating1().setText("");
					buildview.getText().append("The building has a area to big!!\n");

				} else if (!ok) {
					place.insertPlace();
					buildview.getText().append("The building was inserted!!\n");
				}

			}

			}

		}

	}


