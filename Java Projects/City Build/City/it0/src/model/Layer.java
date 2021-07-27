package model;

import java.awt.Color;
import java.awt.Graphics;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Layer {
	private int x;
	private int y;
	private int width;
	private int height;
	private int districtId;
	private int layerTypeId;

	private List<Layer> l = new ArrayList<Layer>();

	public Layer(int x1, int y1, int w1, int h1, int dstid, int layerId) {
		this.setX(x1);
		this.setY(y1);
		this.setWidth(w1);
		this.setHeight(h1);
		this.districtId = dstid;
		this.setLayerTypeId(layerId);
	}

	public Layer() {

	}

	public void createTbLayer() {
		try {
			DbConnection con = new DbConnection();
			Connection conn = con.getConection();
			PreparedStatement post = conn.prepareStatement(
					"CREATE TABLE IF NOT EXISTS Layer(id int NOT NULL AUTO_INCREMENT,x int ,y int, width int,height int,district_id int ,layerTypeid int ,PRIMARY KEY(id),FOREIGN KEY (district_id) REFERENCES district(id))");
			post.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void insertLayer() {
		try {
			DbConnection con = new DbConnection();
			Connection conn = con.getConection();
			PreparedStatement post = conn.prepareStatement(
					"INSERT INTO Layer(x,y,width,height,district_id,layerTypeid) VALUES (?,?,?,?,?,?)");
			post.setInt(1, this.getX());
			post.setInt(2, this.getY());
			post.setInt(3, this.getWidth());
			post.setInt(4, this.getHeight());
			post.setInt(5, this.getDistrictID());
			post.setInt(6, this.getLayerTypeId());

			post.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void selectAllLayer() {
		ResultSet rs = null;
		try {
			DbConnection con = new DbConnection();
			Connection conn = con.getConection();
			PreparedStatement post = conn
					.prepareStatement("SELECT x,y,width,height,district_id ,layerTypeid FROM layer WHERE 1");
			rs = post.executeQuery();

			while (rs.next()) {

				int x1 = rs.getInt("x");
				int y1 = rs.getInt("y");
				int width1 = rs.getInt("width");
				int height1 = rs.getInt("height");
				int dis = rs.getInt("district_id");
				int ltype = rs.getInt("layerTypeid");
				Layer l1 = new Layer(x1, y1, width1, height1, dis, ltype);
				l.add(l1);
			}

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void deleteLayer(int id) {

		try {
			DbConnection con = new DbConnection();
			Connection conn = con.getConection();
			PreparedStatement post = conn.prepareStatement("DELETE FROM layer WHERE id=?");
			post.setInt(1, id);

			post.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void updateLayer(int id, int x1, int y1, int w1, int h1, int dst1, int lt1) {
		try {
			DbConnection con = new DbConnection();
			Connection conn = con.getConection();
			PreparedStatement post = conn.prepareStatement(
					"UPDATE layer SET x=?,y=?,width=?,height=?, district_id=?, layerTypeid=? WHERE id=?");
			post.setInt(1, x1);
			post.setInt(2, y1);
			post.setInt(3, w1);
			post.setInt(4, h1);
			post.setInt(5, dst1);
			post.setInt(6, lt1);
			post.setInt(7, id);
			post.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public List<Layer> getList() {
		return l;
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

	public int getDistrictID() {
		return districtId;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getLayerTypeId() {
		return layerTypeId;
	}

	public void setLayerTypeId(int layerTypeId) {
		this.layerTypeId = layerTypeId;
	}

	public void paint(Graphics g) {

		Layer l1 = new Layer();
		l1.selectAllLayer();
		Iterator<Layer> iterator = l1.getList().iterator();

		while (iterator.hasNext()) {
			Layer p2 = iterator.next();

			LayerType tp = new LayerType();
			tp.getId();
			Iterator<Integer> iterator2 = tp.getId().iterator();
			while (iterator2.hasNext()) {
				Integer tp2 = iterator2.next();

				if (tp2 == p2.getLayerTypeId() && tp2 == 1) {
					int x = p2.getX();
					int y = p2.getY();
					int w = p2.getWidth();
					int h = p2.getHeight();
					g.setColor(Color.GREEN);
					g.fillRect(x, y, w, h);
					g.drawRect(x, y, w, h);
				}
				if (tp2 == p2.getLayerTypeId() && tp2 == 2) {
					int x = p2.getX();
					int y = p2.getY();
					int w = p2.getWidth();
					int h = p2.getHeight();
					g.setColor(Color.BLUE);
					g.fillRect(x, y, w, h);
					g.drawRect(x, y, w, h);
				}
			}

		}

	}
}
