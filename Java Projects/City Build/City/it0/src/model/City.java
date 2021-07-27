package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class City {
	private String nume;
	private int rating;
	private List<City> list = new ArrayList<City>();

	City() {

	}

	City(String nume, int rating) {
		this.nume = nume;
		this.rating = rating;

	}

	public void createTbCity() {
		try {
			DbConnection con = new DbConnection();
			Connection conn = con.getConection();
			PreparedStatement post = conn.prepareStatement(
					"CREATE TABLE IF NOT EXISTS City(id int NOT NULL AUTO_INCREMENT,nume varchar(50) ,rating int , PRIMARY KEY(id))");
			post.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void insertCity() {
		try {
			DbConnection con = new DbConnection();
			Connection conn = con.getConection();
			PreparedStatement post = conn.prepareStatement("INSERT INTO City(nume,rating) VALUES (?,?)");
			post.setString(1, this.nume);
			post.setInt(2, this.rating);
			post.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void selectAllCity() {
		ResultSet rs = null;
		try {
			DbConnection con = new DbConnection();
			Connection conn = con.getConection();
			PreparedStatement post = conn.prepareStatement("SELECT nume,rating FROM City WHERE 1");
			rs = post.executeQuery();

			while (rs.next()) {

				String nume1 = rs.getString("nume");
				int r1 = rs.getInt("rating");

				City d = new City(nume1, r1);
				list.add(d);
			}

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void deleteCity(int idd) {

		try {
			DbConnection con = new DbConnection();
			Connection conn = con.getConection();
			PreparedStatement post = conn.prepareStatement("DELETE FROM City WHERE id=?");
			post.setInt(1, idd);
			post.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
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
}
