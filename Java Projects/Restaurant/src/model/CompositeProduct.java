package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class CompositeProduct extends MenuItem implements Serializable{
 
	 

	ArrayList<MenuItem> productComp = new ArrayList<MenuItem>();
	
	 String groupname;
	 double pret;
	 
	 public CompositeProduct  (String groupname) {
		 this.groupname=groupname;
		 this.pret=0;
	 }
	 public MenuItem getI(int i) {
		return productComp.get(i);
		}
	 
	 public String getNumeGrup() { 
		 return groupname; 
	}
	 public double getPrice() { 
		 return pret; 
	}
	 public void setPrice(double pret) { 
		this.pret=pret; 
	}

	public void addO(MenuItem item) {
		productComp.add(item);
	}

	public void removeO(MenuItem product) {
		productComp.remove(product);
	}
	public int size() {
		return productComp.size();
	}
	public double computePrice() {
		double aux = 0;
		for(MenuItem it:productComp) {
			aux +=it.getPret();
			this.pret =aux;
		}
		return pret;
	}
	public void doString() {
		 System.out.println(getNumeGrup() + " " +getPrice() + "\n");
		 
		 Iterator<MenuItem> it = productComp.iterator();
		
		         while(it.hasNext()) {
		
		            MenuItem s = (MenuItem) it.next();
		 
		            s.doString();
		
		         }

	}


	@Override
	public void createMenuItem(String s,double d) {
		 BaseProduct p=new BaseProduct(s,d);
		 productComp.add(p);
	}


	@Override
	public void deleteMenuItem(String t) {
		for(MenuItem it:productComp) {
		String	aux =it.getNume();
			if (aux.equals(t)) {
				productComp.remove(it);
				break;
			}

        }
		
	}


	@Override
	public void editMenuItem(String s ,double p,String s1 ,double p1) {
		for(int i=0; i<  productComp.size();i=i+1) {
			
			if( productComp.get(i).getNume().equals(s)) {
				productComp.get(i).setNume(s1);
				productComp.get(i).setPret(p1);
			}
     	}
		
	
	}
}
