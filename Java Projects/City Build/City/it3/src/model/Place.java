package model;

import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import Client.Client;
import Request.Request;

@Entity
public class Place implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String nume;
	private int x;
	private int y;
	private int width;
	private int height;
	private String information;
	private int ratingPlace;

	@ManyToOne
	@JoinColumn(name = "districtId")
	private District districtId;

	@ManyToOne
	@JoinColumn(name = "placeTypeId")
	private PlaceType placeTypeId;

	public Place(String nume, int x, int y, int width, int height, String information, int rating, int district) {

		this.setNume(nume);
		this.setX(x);
		this.setY(y);
		this.setWidth(width);
		this.setHeight(height);
		this.setInformation(information);
		this.setRating(rating);

	}

	public Place() {

	}

	public void insertPlace() {
		SessionFactory factor = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

		Session session = factor.getCurrentSession();

		try {

			session.beginTransaction();
			session.save(this);
			session.getTransaction().commit();
			System.out.println("Obiectul a fost inserat!!");

		} finally {
			factor.close();
		}
	}

	public List<Place> selectAllPlace() {
		SessionFactory factor = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		Session session = factor.getCurrentSession();
		String HQL = "FROM Place ";

		try {
			session.beginTransaction();

			Query<Place> q = session.createQuery(HQL, Place.class);
			List<Place> list = q.getResultList();

			session.getTransaction().commit();
			return list;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		} finally {
			session.close();

		}
	}

	public void deletePlace(int idPlace) {
		SessionFactory factor = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

		Session session = factor.getCurrentSession();

		try {

			session.beginTransaction();
			this.setId(idPlace);
			session.delete(this);
			session.getTransaction().commit();
			System.out.println("Obiectul a fost sters!!");

		} finally {
			session.close();
			factor.close();
		}
	}

	public void updatePlace(int id, String nume, int x1, int y1, int w1, int h1, String inf1, int r1, int dst1) {
		SessionFactory factor = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

		Session session = factor.getCurrentSession();

		try {

			session.beginTransaction();

			Place place = session.get(Place.class, id);
			place.setNume(nume);
			place.setX(x1);
			place.setY(y1);
			place.setWidth(w1);
			place.setHeight(h1);
			place.setInformation(inf1);
			place.setRating(r1);
			place.setDistrictId(session.get(District.class, dst1));
			session.getTransaction().commit();
			System.out.println("Operatia de update a reusit!!");

		} finally {
			factor.close();
		}
	}

	public void paint(Graphics g) throws ClassNotFoundException, IOException {
		Request r=new Request("POST","Place");
		Client c = new Client(r);
		Iterator<Place> iterator = c.getListPlace().iterator();
		while (iterator.hasNext()) {
			Place p2 = iterator.next();

			if (p2.getPlaceTypeId().getType() == 0) {
				int x = p2.getX();
				int y = p2.getY();
				int w = p2.getWidth();
				int h = p2.getHeight();
				g.setColor(new Color(153, 102, 0));// maro
				g.fillRect(x, y, w, h);
				g.drawRect(x, y, w, h);
			} else if (p2.getPlaceTypeId().getType() == 1) {
				int x = p2.getX();
				int y = p2.getY();
				int w = p2.getWidth();
				int h = p2.getHeight();
				g.setColor(Color.PINK);
				g.fillRect(x, y, w, h);
				g.drawRect(x, y, w, h);
			}
		}

	}

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getNume() {
		return nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getRating() {
		return ratingPlace;
	}

	public void setRating(int rating) {
		this.ratingPlace = rating;
	}

	public District getDistrictId() {
		return districtId;
	}

	public void setDistrictId(District districtId) {
		this.districtId = districtId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public PlaceType getPlaceTypeId() {
		return placeTypeId;
	}

	public void setPlaceTypeId(PlaceType placeTypeId) {
		this.placeTypeId = placeTypeId;
	}

}