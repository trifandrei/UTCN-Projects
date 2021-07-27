package Server;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.google.gson.Gson;

import rule.Rule;
import model.District;
import model.Layer;
import model.LayerType;
import model.Place;
import model.PlaceType;
import openWeather.WRequest;
import Request.Request;

public class ClientH implements Runnable {

	private Socket client;

	ObjectInputStream input;
	ObjectOutputStream out;

	public ClientH(Socket clientsoc) throws IOException {
		this.client = clientsoc;
		out = new ObjectOutputStream(client.getOutputStream());
		input = new ObjectInputStream(client.getInputStream());

	}

	@Override
	public void run() {

		Request r = null;
		Gson gson = new Gson();
		Place p;
		Layer l;
		while (true) {

			try {
				String str = (String) input.readObject();
				System.out.println(str);
				r = gson.fromJson(str, Request.class);
			} catch (IOException | ClassNotFoundException e1) {

				e1.printStackTrace();
			}
			if (r.getRequestType().equals("POST")) {
				if (r.getDataType().equals("Layer")) {
					l = new Layer();
					try {
						out.writeObject(l.selectAllLayer());
						break;
					} catch (IOException e) {
						e.printStackTrace();
					}

				}
				if (r.getDataType().equals("Place")) {
					Place pl = new Place();
					try {
						out.writeObject(pl.selectAllPlace());
						break;
					} catch (IOException e) {
						e.printStackTrace();
					}

				}

			}
			if (r.getRequestType().equals("PUT")) {
				if (r.getDataType().equals("Place")) {

					p = gson.fromJson(r.getData(), Place.class);
					putPlace(p, r.getDst(), r.getLt());
					break;
				}
				if (r.getDataType().equals("Layer")) {
					l = gson.fromJson(r.getData(), Layer.class);
					try {
						putLayer(l, r.getDst(), r.getLt());
						break;
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;
				}
				if (r.getDataType().equals("Bug")) {
					try {
						BufferedWriter out2 = new BufferedWriter(new FileWriter("BugReport.txt"));
						out2.write(r.getData() + "\n");
						out2.close();
						out.writeObject("Thank you for your report!!");
						break;
					} catch (IOException e) {
						e.printStackTrace();
					}

				}

			}
			if (r.getRequestType().equals("GET")) {
				if (r.getDataType().equals("District")) {

					District d1 = new District();
					try {
						out.writeObject(d1.getIdAndName());
						break;
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if (r.getDataType().equals("Weather")) {

					String[] s = r.getData().split("\\.");

					String s1 = s[0];
					String s2 = s[1];
					String s3 = s[2];
					WRequest r2 = new WRequest(s3);
					String rs = r2.getFromJsonMap(s1, s2);
					if (rs == null)
						try {
							out.writeObject("Request is malformed!!\n");
							break;
						} catch (IOException e) {
							e.printStackTrace();
						}
					else
						try {
							out.writeObject(s2 + " is : " + rs + "\n");
							break;
						} catch (IOException e) {
							e.printStackTrace();
						}
				}

			}

			if (r.getRequestType().equals("DELETE")) {
				if (r.getDataType().equals("Layer")) {
					try {
						l = new Layer();
						l.deleteLayer(Integer.valueOf(r.getData()));
						out.writeObject("Layer was deleted!!");
						break;
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if (r.getDataType().equals("Place")) {
					try {
						Place pl = new Place();
						pl.deletePlace(Integer.valueOf(r.getData()));
						out.writeObject("Place was deleted!!");
						break;
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}

		}

	}

	private void putLayer(Layer l, int dst, int lt1) throws IOException {

		District d = new District();

		LayerType pt = new LayerType();

		for (LayerType lt : pt.selectAllLayerType()) {
			if (lt.getType() == lt1) {
				pt = lt;
			}
		}
		SessionFactory factor = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

		Session session = factor.getCurrentSession();
		session.beginTransaction();
		d = session.get(District.class, dst);
		session.getTransaction().commit();

		l.setDistrictId(d);
		l.setLayerType(pt);

		Rule rule = new Rule();

		if (rule.checkHidroUnderBuild(l)) {
			out.writeObject("The layer it will be build under a building!!\n");
		} else {
			l.insertLayer();
			out.writeObject("The Layer was added to data base!!!");
		}
	}

	public void putPlace(Place p, int dst, int lt1) {

		PlaceType pt = new PlaceType();
		District d = new District();
		for (PlaceType lt : pt.selectAllPlaceType()) {
			if (lt.getType() == lt1) {
				pt = lt;
			}
		}
		SessionFactory factor = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

		Session session = factor.getCurrentSession();
		session.beginTransaction();
		d = session.get(District.class, dst);
		session.getTransaction().commit();

		p.setDistrictId(d);
		p.setPlaceTypeId(pt);

		boolean ok = false;
		Rule rule = new Rule();

		if (!rule.checkBuildOutOfMap(p)) {
			ok = true;
			try {
				out.writeObject("The building is out of the map!!\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (rule.checkBuildOnBuild(p)) {
			ok = true;
			try {
				out.writeObject("The building it will be build over another build!!\n");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (rule.checkBuildOnWater(p)) {
			ok = true;
			try {
				out.writeObject("The building is on water!!\n");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (!rule.checkArea(p)) {
			ok = true;
			try {
				out.writeObject("The building has a area to big!!\n");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (!ok) {
			p.insertPlace();
			try {
				out.writeObject("The building was inserted!!\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
