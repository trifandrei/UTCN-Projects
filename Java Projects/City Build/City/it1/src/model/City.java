package model;

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
public class City {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nume;
	private int rating;

	@OneToMany(targetEntity = District.class, mappedBy = "cityId", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<District> districtList;

	City() {

	}

	public City(String nume, int rating) {
		this.nume = nume;
		this.rating = rating;

	}

	public void insertCity() {
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

	public List<City> selectAllCity() {
		SessionFactory factor = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		Session session = factor.getCurrentSession();
		String HQL = "FROM City ";

		try {
			session.beginTransaction();

			Query<City> q = session.createQuery(HQL, City.class);
			List<City> list = q.getResultList();

			session.getTransaction().commit();
			return list;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		} finally {
			session.close();

		}
	}

	public void deleteCity(int idd) {
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

	public void updateCity(int id, String nume, int rating) {
		SessionFactory factor = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

		Session session = factor.getCurrentSession();

		try {

			session.beginTransaction();

			City cty = session.get(City.class, id);
			cty.setNume(nume);
			cty.setRating(rating);

			session.getTransaction().commit();
			System.out.println("Operatia a reusit!!");

		} finally {
			factor.close();
		}
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getNume() {
		return nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
