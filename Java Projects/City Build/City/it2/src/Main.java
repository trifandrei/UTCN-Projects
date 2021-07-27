
import java.io.IOException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import model.City;
import model.District;
import model.Layer;
import model.LayerType;
import model.Place;
import model.PlaceType;
import model.Type;

import view.GUI;


public class Main {

	public static void populateDataBase() {

		SessionFactory factor = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(LayerType.class)
				.addAnnotatedClass(Layer.class).buildSessionFactory();

		Session session = factor.getCurrentSession();

		try {
			City c1 = new City("alba", 20);

			District d1 = new District("District_1", 1);
			District d2 = new District("District_2", 1);
			District d3 = new District("District_3", 1);
			District d4 = new District("District_4", 1);

			d1.setCityId(c1);
			d2.setCityId(c1);
			d3.setCityId(c1);
			d4.setCityId(c1);

			PlaceType pt1 = new PlaceType("shop", "img.png", Type.shop1);
			PlaceType pt2 = new PlaceType("casa", "imgcasa.png", Type.house0);

			Place p1 = new Place("shop", 100, 250, 100, 100, "open", 10, 1);
			Place p2 = new Place("shop", 400, 200, 100, 100, "close", 10, 2);
			Place p3 = new Place("shop", 20, 470, 100, 100, "open", 10, 3);
			Place p4 = new Place("shop", 750, 550, 100, 100, "close", 10, 4);
			Place p5 = new Place("shop", 50, 50, 50, 50, "Irish pub", 7, 1);
			Place p6 = new Place("shop", 600, 50, 50, 50, "alimentar", 6, 2);
			Place p7 = new Place("shop", 50, 600, 50, 50, "Str.Horia", 9, 3);
			Place p8 = new Place("shop", 600, 600, 50, 50, "piese_auto", 4, 4);

			Place p9 = new Place("casa", 150, 50, 50, 50, "nr.12", 5, 1);
			Place p10 = new Place("casa", 150, 150, 50, 50, "nr.44", 9, 1);
			Place p11 = new Place("casa", 350, 50, 50, 50, "nr.134", 8, 2);
			Place p12 = new Place("casa", 550, 250, 50, 50, "nr.33", 7, 2);
			Place p13 = new Place("casa", 150, 550, 50, 50, "nr.54", 4, 3);
			Place p14 = new Place("casa", 300, 550, 50, 50, "nr.40", 5, 3);
			Place p15 = new Place("casa", 550, 550, 50, 50, "nr.57", 8, 4);
			Place p16 = new Place("casa", 650, 500, 50, 50, "nr.21", 9, 4);

			p1.setDistrictId(d1);
			p2.setDistrictId(d2);
			p3.setDistrictId(d3);
			p4.setDistrictId(d4);
			p5.setDistrictId(d1);
			p6.setDistrictId(d2);
			p7.setDistrictId(d3);
			p8.setDistrictId(d4);

			p1.setPlaceTypeId(pt1);
			p2.setPlaceTypeId(pt1);
			p3.setPlaceTypeId(pt1);
			p4.setPlaceTypeId(pt1);
			p5.setPlaceTypeId(pt1);
			p6.setPlaceTypeId(pt1);
			p7.setPlaceTypeId(pt1);
			p8.setPlaceTypeId(pt1);

			p9.setDistrictId(d1);
			p10.setDistrictId(d1);
			p11.setDistrictId(d2);
			p12.setDistrictId(d2);
			p13.setDistrictId(d3);
			p14.setDistrictId(d3);
			p15.setDistrictId(d4);
			p16.setDistrictId(d4);

			p9.setPlaceTypeId(pt2);
			p10.setPlaceTypeId(pt2);
			p11.setPlaceTypeId(pt2);
			p12.setPlaceTypeId(pt2);
			p13.setPlaceTypeId(pt2);
			p14.setPlaceTypeId(pt2);
			p15.setPlaceTypeId(pt2);
			p16.setPlaceTypeId(pt2);

			LayerType lt1 = new LayerType("ground", Type.ground1);
			LayerType lt2 = new LayerType("hydro", Type.hydro0);

			Layer l1 = new Layer(0, 0, 450, 400, 1, 1);
			Layer l2 = new Layer(450, 0, 450, 400, 2, 1);
			Layer l3 = new Layer(450, 450, 450, 400, 3, 1);
			Layer l4 = new Layer(0, 450, 450, 400, 4, 1);

			Layer h1 = new Layer(250, 0, 30, 400, 1, 2);
			Layer h2 = new Layer(600, 50, 50, 50, 2, 2);
			Layer h3 = new Layer(0, 400, 900, 50, 3, 2);
			Layer h4 = new Layer(600, 700, 50, 50, 4, 2);

			l1.setLayerType(lt1);
			l2.setLayerType(lt1);
			l3.setLayerType(lt1);
			l4.setLayerType(lt1);

			h1.setLayerType(lt2);
			h2.setLayerType(lt2);
			h3.setLayerType(lt2);
			h4.setLayerType(lt2);

			l1.setDistrictId(d1);
			l2.setDistrictId(d2);
			l3.setDistrictId(d3);
			l4.setDistrictId(d4);

			h1.setDistrictId(d1);
			h2.setDistrictId(d2);
			h3.setDistrictId(d3);
			h4.setDistrictId(d4);

			d1.setCityId(c1);

			session.beginTransaction();

			session.save(c1);

			session.save(d1);
			session.save(d2);
			session.save(d3);
			session.save(d4);

			session.save(pt1);
			session.save(pt2);

			session.save(p1);
			session.save(p2);
			session.save(p3);
			session.save(p4);
			session.save(p5);
			session.save(p6);
			session.save(p7);
			session.save(p8);

			session.save(p9);
			session.save(p10);
			session.save(p11);
			session.save(p12);
			session.save(p13);
			session.save(p14);
			session.save(p15);
			session.save(p16);

			session.save(lt1);
			session.save(lt2);

			session.save(l1);
			session.save(l2);
			session.save(l3);
			session.save(l4);

			session.save(h1);
			session.save(h2);
			session.save(h3);
			session.save(h4);

			session.getTransaction().commit();
			System.out.println("Popularea bazei de date a reusit!!!");

		} finally {
			factor.close();
		}

	}

	public static void main(String[] args) throws IOException {

		// populateDataBase();

		// Logger lg = new Logger();
		// District d1 = new District();

		//// lg.show("Ratingul districtului 1=" + d1.getDistrictRating(1) + "\n");
		// lg.show("Ratingul districtului 2=" + d1.getDistrictRating(2) + "\n");
		// lg.show("Ratingul districtului 3=" + d1.getDistrictRating(3) + "\n");
		// lg.show("Ratingul districtului 4=" + d1.getDistrictRating(4) + "\n");
		// lg.show("Construiti in Districtul " + lg.districtLogg() + "\n");

		new GUI();

	}

}
