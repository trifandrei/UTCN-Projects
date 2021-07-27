package model;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class District {
	private String name;
	private int rating;
	private int cityId;
	private List<District> list = new ArrayList<District>();

	public District(String nume1, int rating, int cityid) {
		this.name = nume1;
		this.rating = rating;
		this.setCityId(cityid);
	}

	public District(String name, int cityid) {
		this.setName(name);
		this.setCityId(cityid);
	}

	public District() {

	}

	public void createTbDistrict() {
		try {
			DbConnection con = new DbConnection();
			Connection conn = con.getConection();
			PreparedStatement post = conn.prepareStatement(
					"CREATE TABLE IF NOT EXISTS District(id int NOT NULL AUTO_INCREMENT,nume varchar(50) ,rating int ,cityId int ,PRIMARY KEY(id),FOREIGN KEY (cityId) REFERENCES city(id))");
			post.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void insertDistrict() {
		try {
			DbConnection con = new DbConnection();
			Connection conn = con.getConection();
			PreparedStatement post = conn.prepareStatement("INSERT INTO District(nume,cityId) VALUES ( ?,?)");
			post.setString(1, this.name);
			;
			post.setInt(2, this.cityId);
			post.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void selectALLDistrict() {
		ResultSet rs = null;
		try {
			DbConnection con = new DbConnection();
			Connection conn = con.getConection();
			PreparedStatement post = conn.prepareStatement("SELECT nume ,rating,cityId FROM District WHERE 1");
			rs = post.executeQuery();

			while (rs.next()) {

				String nume1 = rs.getString("nume");
				int r1 = rs.getInt("rating");
				int r2 = rs.getInt("cityId");

				District d = new District(nume1, r1, r2);
				list.add(d);
			}

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void deleteDistrict(int idd) {

		try {
			DbConnection con = new DbConnection();
			Connection conn = con.getConection();
			PreparedStatement post = conn.prepareStatement("DELETE FROM Distirct WHERE id=?");
			post.setInt(1, idd);
			post.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public float getDistrictRating(int idd) {

		ResultSet rs = null;
		int sum = 0;
		int c = 0;
		try {

			DbConnection con = new DbConnection();
			Connection conn = con.getConection();
			PreparedStatement post = conn.prepareStatement(
					"SELECT place.ratingPlace FROM place INNER JOIN district ON place.districtId=? ");
			post.setInt(1, idd);
			rs = post.executeQuery();

			while (rs.next()) {

				int r = rs.getInt("ratingPlace");
				

				sum = sum + r ;
				c = c + 1;
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		return (float) sum / c;
	}

	public float getALLDistrictRating() {

		ResultSet rs = null;
		float sum = 0;
		int c = 0;
		District d = new District();
		try {

			DbConnection con = new DbConnection();
			Connection conn = con.getConection();
			PreparedStatement post = conn.prepareStatement("SELECT district.id FROM district ");
			rs = post.executeQuery();

			while (rs.next()) {

				int r = rs.getInt("id");

				float r1 = (float) d.getDistrictRating(r);

				sum = sum + r1;
				c = c + 1;
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		return (float) sum / c;
	}

	public void distictLogger(int id) throws IOException {

		File f = new File("out_place.txt");
		f.createNewFile();
		PrintWriter p = new PrintWriter(f);
		p.write("name x y information rating districtId \n");
		ResultSet rs = null;
		try {

			DbConnection con = new DbConnection();
			Connection conn = con.getConection();
			PreparedStatement post = conn.prepareStatement(
					"SELECT place.nume ,x ,y,information,ratingPlace,districtId FROM place INNER JOIN district ON place.districtId=? ");
			post.setInt(1, id);
			rs = post.executeQuery();

			while (rs.next()) {

				String r = rs.getString("nume");
				String r1 = rs.getString("x");
				String r2 = rs.getString("y");
				String r3 = rs.getString("information");
				String r4 = rs.getString("ratingPlace");
				String r5 = rs.getString("districtId");
				p.write(r+" ");
				p.write(r1 + " ");
				p.write(r2 + " ");
				p.write(r3 + " ");
				p.write(r4 + " ");
				p.write(r5 + "\n");
				p.write("\n");
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		p.close();
	}

	



	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<District> getList() {
		return list;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}
}
