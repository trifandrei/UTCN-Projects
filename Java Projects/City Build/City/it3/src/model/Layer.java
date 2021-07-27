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
public class Layer implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7444123814000419675L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private int x;
	private int y;
	private int width;
	private int height;

	@ManyToOne
	@JoinColumn(name = "districtId")
	private District districtId;

	@ManyToOne
	@JoinColumn(name = "LayerTypeId")
	private LayerType layerType;

	public Layer(int x1, int y1, int w1, int h1, int dstid, int layerTypeId) {
		this.setX(x1);
		this.setY(y1);
		this.setWidth(w1);
		this.setHeight(h1);

	}

	public Layer() {

	}

	public void insertLayer() {
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

	public List<Layer> selectAllLayer() {
		SessionFactory factor = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		Session session = factor.getCurrentSession();
		String HQL = "FROM Layer ";

		try {
			session.beginTransaction();

			Query<Layer> q = session.createQuery(HQL, Layer.class);
			List<Layer> list = q.getResultList();

			session.getTransaction().commit();
			return list;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		} finally {
			session.close();

		}
	}

	public void deleteLayer(int id) {
		SessionFactory factor = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

		Session session = factor.getCurrentSession();

		try {

			session.beginTransaction();
			this.setId(id);
			session.delete(this);
			session.getTransaction().commit();
			System.out.println("Obiectul a fost sters!!");

		} finally {
			session.close();
			factor.close();
		}
	}

	public void updateLayer(int id, int x1, int y1, int w1, int h1, int dst1, int lt1) {

		SessionFactory factor = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

		Session session = factor.getCurrentSession();

		try {

			session.beginTransaction();

			Layer layer = session.get(Layer.class, id);
			layer.setX(x1);
			layer.setY(y1);
			layer.setWidth(w1);
			layer.setHeight(h1);
			layer.setDistrictId(session.get(District.class, dst1));
			layer.setLayerType(session.get(LayerType.class, dst1));
			session.getTransaction().commit();
			System.out.println("Operatia a reusit!!");

		} finally {
			factor.close();
		}
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

	// paint all the layer who are stored in database
	public void paint(Graphics g) throws ClassNotFoundException, IOException {
		Request r=new Request("POST","Layer");
		Client c = new Client(r);

		Iterator<Layer> iterator = c.getListLayer().iterator();

		while (iterator.hasNext()) {
			Layer l2 = iterator.next();

			if (l2.getLayerType().getType() == 1) {
				int x = l2.getX();
				int y = l2.getY();
				int w = l2.getWidth();
				int h = l2.getHeight();
				g.setColor(Color.GREEN);
				g.fillRect(x, y, w, h);
				g.drawRect(x, y, w, h);
			}
			if (l2.getLayerType().getType() == 0) {
				int x = l2.getX();
				int y = l2.getY();
				int w = l2.getWidth();
				int h = l2.getHeight();
				g.setColor(Color.BLUE);
				g.fillRect(x, y, w, h);
				g.drawRect(x, y, w, h);
			}
		}

	}

	public LayerType getLayerType() {
		return layerType;
	}

	public void setLayerType(LayerType layerType) {
		this.layerType = layerType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public District getDistrictId() {
		return districtId;
	}

	public void setDistrictId(District districtId) {
		this.districtId = districtId;
	}
}
