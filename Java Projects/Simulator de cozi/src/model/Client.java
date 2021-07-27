package model;

public class Client {
	private int timpSosire;
	private int timpServire;
	private int id;

	public  Client(int x, int aTime, int sTime) {
		this.id = x;
		this.timpServire = sTime;
		this.timpSosire = aTime;
	}

	public  int getServireTime() {
		return timpServire;
	}

	public  int getSosireTime() {
		return timpSosire;
	}
	
	public  void setSosireTime(int arrTime) {
		this.timpSosire = arrTime;
	}

	public  int getID() {
		return id;
	}

	public  void setServireTime(int service) {
		this.timpServire=service;
		
	}
}
