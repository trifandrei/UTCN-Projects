package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LayerType {
	private String name;
	private List<LayerType> list = new ArrayList<LayerType>();
	private List<Integer> list2 = new ArrayList<Integer>();

	public LayerType(String name) {
		this.setName(name);
	}

	public LayerType() {

	}

	public void createTbLayerTp() {
		try {
			DbConnection con = new DbConnection();
			Connection conn = con.getConection();
			PreparedStatement post = conn.prepareStatement(
					"CREATE TABLE IF NOT EXISTS LayerType(layerTypeId int NOT NULL AUTO_INCREMENT,nume varchar(200) ,PRIMARY KEY(layerTypeId),FOREIGN KEY (layerTypeId) REFERENCES layer(id))");
			post.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void insertLayerTp() {
		try {
			DbConnection con = new DbConnection();
			Connection conn = con.getConection();
			PreparedStatement post = conn.prepareStatement("INSERT INTO LayerType(nume) VALUES (?)");
			post.setString(1, this.getName());
			post.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void selectAllLayerTp() {
		ResultSet rs = null;
		try {
			DbConnection con = new DbConnection();
			Connection conn = con.getConection();
			PreparedStatement post = conn.prepareStatement("SELECT nume FROM LayerType WHERE 1");
			rs = post.executeQuery();

			while (rs.next()) {

				String nume1 = rs.getString("nume");
				LayerType lt = new LayerType(nume1);
				list.add(lt);
			}

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void deleteLayerTp(int idd) {

		try {
			DbConnection con = new DbConnection();
			Connection conn = con.getConection();
			PreparedStatement post = conn.prepareStatement("DELETE FROM LayerType WHERE id=?");
			post.setInt(1, idd);
			post.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void updateLayerTp(int idl, String nume1) {
		try {
			DbConnection con = new DbConnection();
			Connection conn = con.getConection();
			PreparedStatement post = conn.prepareStatement("UPDATE LayerType SET nume=? WHERE id=?");
			post.setString(1, nume1);
			post.setInt(2, idl);
			post.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public List<Integer> getId() {

		ResultSet rs = null;
		try {
			DbConnection con = new DbConnection();
			Connection conn = con.getConection();
			PreparedStatement post = conn.prepareStatement("SELECT layerTypeId FROM LayerType WHERE 1");
			rs = post.executeQuery();

			while (rs.next()) {

				int id1 = rs.getInt("layerTypeId");
				list2.add(id1);
			}

		} catch (Exception e) {
			System.out.println(e);
		}

		return list2;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<LayerType> getList() {
		return list;
	}
}
