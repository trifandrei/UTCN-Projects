package Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.Layer;
import model.Place;
import Request.Request;

public class Client {
	private String mess;
	private String[] arr;
	private ArrayList<Place> list = new ArrayList<Place>();
	private ArrayList<Layer> list2 = new ArrayList<Layer>();

	public Client(Request r, String data1, int data2, int data3, int data4, int data5, String data6, int data7)
			throws UnknownHostException, IOException, ClassNotFoundException {

		Socket soc = new Socket(r.getHost(), r.getPort());

		ObjectInputStream input = new ObjectInputStream(soc.getInputStream());

		ObjectOutputStream out = new ObjectOutputStream(soc.getOutputStream());

		Place p = new Place();

		p.setNume(data1);
		p.setX(data2);
		p.setY(data3);
		p.setWidth(data4);
		p.setHeight(data5);
		p.setInformation(data6);
		p.setRating(data7);

		ObjectMapper obj = new ObjectMapper();
		String json1 = null;
		String json2 = null;

		try {

			json1 = obj.writeValueAsString(p);

		}

		catch (IOException e) {
			e.printStackTrace();
		}
		r.setData(json1);
		try {

			json2 = obj.writeValueAsString(r);

		}

		catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println(json2);
		if (json1 == null || json2 == null) {
			System.out.println("Obj is null in client!!");
			System.exit(-1);
		} else {
			out.writeObject(json2);

			String ser = (String) input.readObject();

			this.mess = "From server:" + ser;
			out.close();
			soc.close();
		}
	}

	public Client(Request r, int data1, int data2, int data3, int data4)
			throws UnknownHostException, IOException, ClassNotFoundException {

		Socket soc = new Socket(r.getHost(), r.getPort());

		ObjectInputStream input = new ObjectInputStream(soc.getInputStream());
		ObjectOutputStream out = new ObjectOutputStream(soc.getOutputStream());

		Layer l = new Layer();
		l.setX(data1);
		l.setY(data2);
		l.setWidth(data3);
		l.setHeight(data4);

		ObjectMapper obj = new ObjectMapper();
		String json1 = null;

		try {

			json1 = obj.writeValueAsString(l);

		}

		catch (IOException e) {
			e.printStackTrace();
		}
		r.setData(json1);
		String json2 = null;
		try {

			json2 = obj.writeValueAsString(r);

		}

		catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println(json2);
		if (json1 == null || json2 == null) {
			System.out.println("Obj is null in client!!");
			System.exit(-1);
		} else {
			out.writeObject(json2);

			String ser = (String) input.readObject();

			this.mess = "From server:" + ser;
			out.close();
			soc.close();
		}
	}

	public Client(Request r, String s1, String s2, String s3) throws IOException, ClassNotFoundException {
		Socket soc = new Socket(r.getHost(), r.getPort());

		ObjectInputStream input = new ObjectInputStream(soc.getInputStream());
		ObjectOutputStream out = new ObjectOutputStream(soc.getOutputStream());

		String s = s1 + "." + s2 + "." + s3;

		ObjectMapper obj = new ObjectMapper();
		String json1 = null;
		r.setData(s);
		try {

			json1 = obj.writeValueAsString(r);

		}

		catch (IOException e) {
			e.printStackTrace();
		}
		out.writeObject(json1);

		String ser = (String) input.readObject();

		this.mess = "From server:" + ser + "\n";
		out.close();
		soc.close();
	}

	public Client(Request r, String s) throws IOException, ClassNotFoundException {
		@SuppressWarnings("resource")
		Socket soc = new Socket(r.getHost(), r.getPort());

		ObjectInputStream input = new ObjectInputStream(soc.getInputStream());
		ObjectOutputStream out = new ObjectOutputStream(soc.getOutputStream());

		ObjectMapper obj = new ObjectMapper();
		String json1 = null;
		r.setData(s);
		try {

			json1 = obj.writeValueAsString(r);

		}

		catch (IOException e) {
			e.printStackTrace();
		}
		out.writeObject(json1);

		if (r.getDataType().equals("Bug")) {
			String[] ser = (String[]) input.readObject();
			this.arr = ser;
		}
		if (r.getDataType().equals("Layer") || r.getDataType().equals("Place")) {
			String ser = (String) input.readObject();

			this.mess = ser;
		}

	}

	@SuppressWarnings("unchecked")
	public Client(Request r) throws ClassNotFoundException, IOException {
		Socket soc = new Socket(r.getHost(), r.getPort());

		ObjectInputStream input = new ObjectInputStream(soc.getInputStream());
		ObjectOutputStream out = new ObjectOutputStream(soc.getOutputStream());

		ObjectMapper obj = new ObjectMapper();
		String json1 = null;

		try {

			json1 = obj.writeValueAsString(r);

		}

		catch (IOException e) {
			e.printStackTrace();
		}
		out.writeObject(json1);

		if (r.getDataType().equals("District")) {
			String[] ser = (String[]) input.readObject();
			this.arr = ser;
		}
		if (r.getDataType().equals("Layer")) {

			this.list2 = (ArrayList<Layer>) input.readObject();
		}
		if (r.getDataType().equals("Place")) {

			this.list = (ArrayList<Place>) input.readObject();
		}
		if (r.getDataType().equals("Agent")) {

			this.mess = (String) input.readObject();
		}
		out.close();
		soc.close();

	}

	public String[] getArr() {
		return arr;
	}

	public String getMessageFromServer() {

		return this.mess;
	}

	public ArrayList<Place> getListPlace() {
		return list;
	}

	public ArrayList<Layer> getListLayer() {
		return list2;
	}

}
