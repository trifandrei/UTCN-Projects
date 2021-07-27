package model;

import java.io.Serializable;

public class BaseProduct extends MenuItem implements Serializable {

private String nume;
 private double pret;
 
 public BaseProduct(String nume1,double pret1){
	 this.nume=nume1;
	 this.pret=pret1;
 }
 
 public void setNume(String nume) {
	 this.nume=nume;
 }
 public String getNume() {
	 return nume;
 }
 public void setPret(double pret) {
	 this.pret=pret;
 }
 public double getPret(){
	 return pret;
 }
 public void doString(){
	 System.out.println(nume+" "+pret);
 }

@Override
public void createMenuItem(String s, double p) {
	// TODO Auto-generated method stub
	
}

@Override
public void deleteMenuItem(String s) {
	// TODO Auto-generated method stub
	
}

@Override
public void editMenuItem(String s, double p, String s1, double p1) {
	// TODO Auto-generated method stub
	
}


}
