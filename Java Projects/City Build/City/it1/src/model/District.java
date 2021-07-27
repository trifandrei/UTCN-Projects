package model;

import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

@Entity
public class District {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private float rating;

	@ManyToOne
	@JoinColumn(name = "cityId")
	private City cityId;

	@OneToMany(targetEntity = Place.class, mappedBy = "districtId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Layer> placer;

	@OneToMany(targetEntity = Layer.class, mappedBy = "districtId", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Layer> layerr;

	public District(String nume1, int rating, int cityid) {
		this.name = nume1;
		this.rating = rating;

	}

	public District(String name, int cityid) {
		this.setName(name);

	}

	public District() {

	}

	public void insertDistrict() {
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

	public List<District> selectALLDistrict() {
		SessionFactory factor = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		Session session = factor.getCurrentSession();
		String HQL = "FROM District ";

		try {
			session.beginTransaction();

			Query<District> q = session.createQuery(HQL, District.class);
			List<District> list = q.getResultList();

			session.getTransaction().commit();
			return list;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		} finally {
			session.close();

		}
	}

	public void deleteDistrict(int idd) {
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

	public void updateDistrict(int id, String nume, int rating, int cityid) {
		SessionFactory factor = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

		Session session = factor.getCurrentSession();

		try {

			session.beginTransaction();

			District dst = session.get(District.class, id);
			dst.setName(nume);
			dst.setRating(rating);
			dst.setCityId(session.get(City.class, cityid));
			session.getTransaction().commit();
			System.out.println("Operatia a reusit!!");

		} finally {
			factor.close();
		}
	}

	// compute the rating for district with the given id
	public float getDistrictRating(int idd) {

		int sum = 0;
		int c = 0;
		Place place = new Place();
		;

		Iterator<Place> iterator = place.selectAllPlace().iterator();
		while (iterator.hasNext()) {
			Place p2 = iterator.next();

			if (p2.getDistrictId().getId() == idd) {
				sum = sum + p2.getRating();
				c++;
			}
		}
		return (float) sum / c;
	}

	public String[] getIdAndName() {
		String[] list = new String[20];
		
		int i = 0;
		District d = new District();

		Iterator<District> iterator = d.selectALLDistrict().iterator();
		while (iterator.hasNext()) {
			District p2 = iterator.next();

			list[i] = String.valueOf(p2.getId()) + "." + p2.getName();
			i++;

		}
		return list;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public City getCityId() {
		return cityId;
	}

	public void setCityId(City cityId) {
		this.cityId = cityId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Layer> getLayerr() {
		return layerr;
	}

	public void setLayerr(List<Layer> layerr) {
		this.layerr = layerr;
	}
}
