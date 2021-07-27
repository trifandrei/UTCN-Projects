package model;

import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

@Entity
public class PlaceType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String icon;
	private int type;
	@OneToMany(targetEntity = Place.class, mappedBy = "placeTypeId", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Layer> placelist;

	public PlaceType(String name, String icon, Type tp) {
		this.setName(name);
		this.setIcon(icon);
		if (Type.house0 == tp)
			this.type = 0;
		else if (Type.shop1 == tp)
			this.type = 1;
	}

	public PlaceType() {

	}

	public void insertPlaceType() {
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

	public List<PlaceType> selectAllPlaceType() {
		SessionFactory factor = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		Session session = factor.getCurrentSession();
		String HQL = "FROM PlaceType ";

		try {
			session.beginTransaction();

			Query<PlaceType> q = session.createQuery(HQL, PlaceType.class);
			List<PlaceType> list = q.getResultList();

			session.getTransaction().commit();
			return list;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		} finally {
			session.close();

		}
	}

	public void deletePlaceType(int idd) {
		SessionFactory factor = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

		Session session = factor.getCurrentSession();

		try {

			session.beginTransaction();
			this.setId(idd);
			session.delete(this);
			session.getTransaction().commit();
			System.out.println("Obiectul a fost sters!!");

		} finally {
			session.close();
			factor.close();
		}

	}

	public void updatePlaceType(int idl, String nume1, String icon1) {
		SessionFactory factor = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

		Session session = factor.getCurrentSession();

		try {

			session.beginTransaction();

			PlaceType placetype = session.get(PlaceType.class, idl);
			placetype.setName(nume1);
			placetype.setIcon(icon1);

			session.getTransaction().commit();
			System.out.println("Operatia a reusit!!");

		} finally {
			factor.close();
		}
	}

	public String[] getIdAndName() {
		String[] list = new String[20];
		;
		int i = 0;
		PlaceType pl = new PlaceType();

		Iterator<PlaceType> iterator = pl.selectAllPlaceType().iterator();
		while (iterator.hasNext()) {
			PlaceType p2 = iterator.next();

			list[i] = String.valueOf(p2.getId()) + "." + p2.getName();
			i++;

		}
		return list;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public List<Layer> getPlacelist() {
		return placelist;
	}

	public void setPlacelist(List<Layer> placelist) {
		this.placelist = placelist;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}
