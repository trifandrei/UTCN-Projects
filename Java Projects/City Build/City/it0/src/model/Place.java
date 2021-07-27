package model;

import java.awt.Color;
import java.awt.Graphics;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Place {
	private String nume;
	private int x;
	private int y;
	private int width;
	private int height;
	private String information;
	private int ratingPlace;
	private int districtId;

	private List<Place> l = new ArrayList<Place>();

	public Place(String nume, int x, int y, int width, int height, String information, int rating, int district) {

		this.setNume(nume);
		this.setX(x);
		this.setY(y);
		this.setWidth(width);
		this.setHeight(height);
		this.setInformation(information);
		this.setRating(rating);
		this.setDistrict(district);
	}

	public Place() {

	}

	public void createTbPlace() {
		try {
			DbConnection con = new DbConnection();
			Connection conn = con.getConection();
			PreparedStatement post = conn.prepareStatement(
					"CREATE TABLE IF NOT EXISTS Place(id int NOT NULL AUTO_INCREMENT, nume varchar(50) ,x int ,y int, width int,height int, information varchar(100),ratingPlace int ,districtId int ,PRIMARY KEY(id),FOREIGN KEY (districtId) REFERENCES district(id))");
			post.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void insertPlace() {
		try {
			DbConnection con = new DbConnection();
			Connection conn = con.getConection();
			PreparedStatement post = conn.prepareStatement(
					"INSERT INTO Place( nume,x,y,width,height,information,ratingPlace,districtId) VALUES (?,?,?,?,?,?,?,? )");
			post.setString(1, this.nume);
			post.setInt(2, this.x);
			post.setInt(3, this.y);
			post.setInt(4, this.width);
			post.setInt(5, this.height);
			post.setString(6, this.information);
			post.setInt(7, this.ratingPlace);
			post.setInt(8, this.districtId);
			post.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void selectAllPlace() {
		ResultSet rs = null;
		try {
			DbConnection con = new DbConnection();
			Connection conn = con.getConection();
			PreparedStatement post = conn.prepareStatement(
					"SELECT  nume,x,y,width,height,information,ratingPlace,districtId FROM Place WHERE 1");
			rs = post.executeQuery();

			while (rs.next()) {

				String name1 = rs.getString("nume");
				int x1 = rs.getInt("x");
				int y1 = rs.getInt("y");
				int width1 = rs.getInt("width");
				int height1 = rs.getInt("height");
				String info = rs.getString("information");
				int rat = rs.getInt("ratingPlace");
				int dis = rs.getInt("districtId");

				Place s = new Place(name1, x1, y1, width1, height1, info, rat, dis);
				l.add(s);
			}

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void deletePlace(int idPlace) {

		try {
			DbConnection con = new DbConnection();
			Connection conn = con.getConection();
			PreparedStatement post = conn.prepareStatement("DELETE FROM Place WHERE id=?");
			post.setInt(1, idPlace);
			post.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void updatePlace(int id, String nume, int x1, int y1, int w1, int h1, String inf1, int r1, int dst1) {
		try {
			DbConnection con = new DbConnection();
			Connection conn = con.getConection();
			PreparedStatement post = conn.prepareStatement(
					"UPDATE Place SET nume=?,x=?,y=?,width=?,height=?,information=?,ratingPlace=?,districtId=? WHERE id=?");
			post.setString(1, nume);
			post.setInt(2, x1);
			post.setInt(3, y1);
			post.setInt(4, w1);
			post.setInt(5, h1);
			post.setString(6, inf1);
			post.setInt(7, r1);
			post.setInt(8, dst1);
			post.setInt(9, id);
			post.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	public void paint(Graphics g) {
		
		
		Place p1 = new Place();
		p1.selectAllPlace();
		Iterator<Place> iterator = p1.getList().iterator();
		while (iterator.hasNext()) {
			Place p2 = iterator.next();
			int x = p2.getX();
			int y = p2.getY();
			int w = p2.getWidth();
			int h = p2.getHeight();
			g.setColor(new Color(153,102,0));
			g.fillRect(x, y, w, h);
			g.drawRect(x, y, w, h);
			
		}
		
	}
	public int getListSize() {
		return l.size();
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

	public int getDistrict() {
		return districtId;
	}

	public void setDistrict(int district) {
		this.districtId = district;
	}

	public List<Place> getList() {
		return l;
	}

}