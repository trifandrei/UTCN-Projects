package model;

import java.io.Serializable;
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
public class LayerType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	private int type;

	@OneToMany(targetEntity = Layer.class, mappedBy = "layerType", cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REMOVE }, fetch = FetchType.LAZY)
	private List<Layer> layerr;

	public LayerType(String name, Type tp) {
		this.setName(name);
		if (tp == Type.ground1)
			this.type = 1;
		else
			this.type = 0;
	}

	public LayerType() {

	}

	public void insertLayerType() {

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

	public List<LayerType> selectAllLayerType() {
		SessionFactory factor = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		Session session = factor.getCurrentSession();
		String HQL = "FROM LayerType ";

		try {
			session.beginTransaction();

			Query<LayerType> q = session.createQuery(HQL, LayerType.class);
			List<LayerType> list = q.getResultList();

			session.getTransaction().commit();
			return list;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		} finally {
			session.close();

		}

	}

	public void deleteLayerType(int id1) {

		SessionFactory factor = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

		Session session = factor.getCurrentSession();
		// String HQL1 = "set FOREIGN_KEY_CHECKS=0";
		// String HQL2 = "set FOREIGN_KEY_CHECKS=1";
		try {

			session.beginTransaction();
			// Query<LayerType> q = session.createQuery(HQL1, LayerType.class);

			this.setId(id1);
			session.delete(this);

			// Query<LayerType> q1 = session.createQuery(HQL2, LayerType.class);

			session.getTransaction().commit();
			System.out.println("Obiectul a fost sters!!");

		} finally {
			factor.close();
		}
	}

	public void updateLayerType(int idl, String nume1) {
		SessionFactory factor = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

		Session session = factor.getCurrentSession();

		try {

			session.beginTransaction();

			LayerType lt = session.get(LayerType.class, idl);
			lt.setName(nume1);
			session.getTransaction().commit();
			System.out.println("Operatia a reusit!!");

		} finally {
			factor.close();
		}

	}

	public String[] getIdAndName() {
		String[] list = new String[20];

		int i = 0;
		LayerType d = new LayerType();

		Iterator<LayerType> iterator = d.selectAllLayerType().iterator();
		while (iterator.hasNext()) {
			LayerType p2 = iterator.next();

			list[i] = String.valueOf(p2.getId()) + "." + p2.getName();
			i++;

		}
		return list;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(int id1) {
		this.id = id1;
	}

	public int getId() {
		return id;
	}

	public List<Layer> getLayerr() {
		return layerr;
	}

	public void setLayerr(List<Layer> layerr) {
		this.layerr = layerr;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
