package model;

public class Order {
	
	int id=0;
	String adress;
	String nume;
	
	public Order(String nume,String adress) {
		this.id++;
		this.nume=nume;
		this.adress=adress;
	}
	public String getName() {
		return nume;
	}
	public String getAdress() {
		return adress;
	}

}
