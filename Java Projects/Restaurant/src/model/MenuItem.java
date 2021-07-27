package model;

import java.io.Serializable;

public abstract class MenuItem implements RestaurantProcessing ,Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 4602018641249748219L;

	public double computePrice() {
		  throw new UnsupportedOperationException();
	}
	
	public double getPret() {
		  throw new UnsupportedOperationException();
	}
	public String getNume() {
		throw new UnsupportedOperationException();
	 }
	public void setPret(double d) {
		  throw new UnsupportedOperationException();
	}
	public void setNume(String s) {
		throw new UnsupportedOperationException();
	 }
	public String getNumeGrup() {
		throw new UnsupportedOperationException();
	 }
	public double getPrice() { 
		  throw new UnsupportedOperationException();
	}
	
	public void addO(MenuItem product) {
		  throw new UnsupportedOperationException();
	}

	public void removeO(MenuItem product) {
		  throw new UnsupportedOperationException();
	}
	public void doString() {
		  throw new UnsupportedOperationException();
	}

	public MenuItem getI(int i) {
		throw new UnsupportedOperationException();
	}

	public  int size() {
		throw new UnsupportedOperationException();
	}
}
