package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PlaceType {
	private String name;
	private String icon;
	private List<PlaceType> list = new ArrayList<PlaceType>();
	
	
	
	public PlaceType(String name, String icon) {
		this.setName(name);
		this.setIcon(icon);
	}

	public PlaceType() {

	}

	public void createTbPlaceTp() {
		try {
			DbConnection con = new DbConnection();
			Connection conn = con.getConection();
			PreparedStatement post = conn.prepareStatement(
					"CREATE TABLE IF NOT EXISTS PlaceType(placeTypeId int NOT NULL AUTO_INCREMENT,nume varchar(200) ,icon text,PRIMARY KEY(placeTypeId),FOREIGN KEY (placeTypeId) REFERENCES place(id))");
			post.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void insertPlaceTp() {
		try {
			DbConnection con = new DbConnection();
			Connection conn = con.getConection();
			PreparedStatement post = conn.prepareStatement("INSERT INTO PlaceType(nume,icon) VALUES (?,?)");
			post.setString(1, this.getName());
			post.setString(2, this.getIcon());
			post.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void selectAllPlaceTp() {
		ResultSet rs = null;
		try {
			DbConnection con = new DbConnection();
			Connection conn = con.getConection();
			PreparedStatement post = conn.prepareStatement("SELECT nume,icon FROM PlaceType WHERE 1");
			rs = post.executeQuery();

			while (rs.next()) {

				String nume1 = rs.getString("nume");
				String icon1 = rs.getString("icon");
				PlaceType lt = new PlaceType(nume1, icon1);
				list.add(lt);
			}

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void deletePlaceTp(int idd) {

		try {
			DbConnection con = new DbConnection();
			Connection conn = con.getConection();
			PreparedStatement post = conn.prepareStatement("DELETE FROM PlaceType WHERE placeTypeId=?");
			post.setInt(1, idd);
			post.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void updatePlaceTp(int idl, String nume1, String icon1) {
		try {
			DbConnection con = new DbConnection();
			Connection conn = con.getConection();
			PreparedStatement post = conn.prepareStatement("UPDATE PlaceType SET nume=?,icon=? WHERE placeTypeId=?");
			post.setString(1, nume1);
			post.setString(2, icon1);
			post.setInt(3, idl);
			post.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<PlaceType> getList() {
		return list;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
}
