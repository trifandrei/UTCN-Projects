package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;



public class ConnectionDB {

	public static void insertClienti(String a1,String a2,String a3){
		 try {
			 
			 Connection conn=getConection();
			 PreparedStatement post=conn.prepareStatement("INSERT INTO clienti (nume, prenume, adresa) VALUES ('"+a1+"','"+a2+"','"+a3+"')");
			 post.executeUpdate();
		 }catch(Exception e){
			 System.out.println(e);
		 }
		}
	
	public static void createTClienti(){
	 try {
		 
		 Connection conn=getConection();
		 PreparedStatement post=conn.prepareStatement("CREATE TABLE IF NOT EXISTS clienti(id int NOT NULL AUTO_INCREMENT,nume varchar(50) ,prenume varchar(50), adresa varchar(50),PRIMARY KEY(id))");
		 post.executeUpdate();
	 }catch(Exception e){
		 System.out.println(e);
	 }
	}
	
	public static void createTProduse(){
		 try {
			 
			 Connection conn=getConection();
			 PreparedStatement post=conn.prepareStatement("CREATE TABLE IF NOT EXISTS produse(id int NOT NULL AUTO_INCREMENT,nume varchar(50) ,pret int(50), cantitate int(50),PRIMARY KEY(id))");
			 post.executeUpdate();
		 }catch(Exception e){
			 System.out.println(e);
		 }
		}
	
	public static void createTOrder(){
		 try {
			 
			 Connection conn=getConection();
			 PreparedStatement post=conn.prepareStatement("CREATE TABLE IF NOT EXISTS comanda(id int NOT NULL AUTO_INCREMENT,nume varchar(50) ,prenume varchar(50), adresa varchar(50),cantitate int(50),produs varchar(50),PRIMARY KEY(id))");
			 post.executeUpdate();
		 }catch(Exception e){
			 System.out.println(e);
		 }
		}
	
	public static void deleteClienti(String a1,String a2){
		 try {
			 
			 Connection conn=getConection();
			 PreparedStatement post=conn.prepareStatement("DELETE FROM clienti WHERE nume='"+a1+"' and prenume='"+a2+"'");
			 post.executeUpdate();
		 }catch(Exception e){
			 System.out.println(e);
		 }
		}
	
	public static void insertProduse(String a11,String a2,String a3){
		 try {
			
			 int a12=Integer.parseInt(a2);
			 int a13=Integer.parseInt(a3);
			 Connection conn=getConection();
			 PreparedStatement post=conn.prepareStatement("INSERT INTO produse (nume, pret, cantitate) VALUES ('"+a11+"','"+a12+"','"+a13+"')");
			 post.executeUpdate();
		 }catch(Exception e){
			 System.out.println(e);
		 }
		}
	
	public static void deleteProduse(String a1){
		 try {
			 
			 Connection conn=getConection();
			 PreparedStatement post=conn.prepareStatement("DELETE FROM produse WHERE nume='"+a1+"'");
			 post.executeUpdate();
		 }catch(Exception e){
			 System.out.println(e);
		 }
		}
	
	public static void updateCantitate(int a1,String a2){
		 try {
			 
			 Connection conn=getConection();
			 PreparedStatement post=conn.prepareStatement("UPDATE `produse` SET `cantitate`="+a1+" WHERE nume='"+a2+"'");
			 post.executeUpdate();
		 }catch(Exception e){
			 System.out.println(e);
		 }
		}
	
	public static  String getCantitate(String a) throws Exception {
		 ResultSet rs=null;
		 ArrayList<String> arr=new ArrayList<String>();
		try {
			
			 Connection conn=getConection();
			 PreparedStatement post=conn.prepareStatement("SELECT produse.cantitate FROM produse WHERE produse.nume='"+a+"'");
			 rs=post.executeQuery();
			 
			
			 while (rs.next()) {
				 
				
				 arr.add(rs.getString("cantitate"));
			 }
			 String s=arr.get(0);
			
			 return s;
		 }catch(Exception e){
			 System.out.println(e);
		 }
		
		
		return null ;
		
	}
	
	public static  String getPret(String a) throws Exception {
		 ResultSet rs=null;
		 ArrayList<String> arr=new ArrayList<String>();
		try {
			
			 Connection conn=getConection();
			 PreparedStatement post=conn.prepareStatement("SELECT produse.pret FROM produse WHERE produse.nume='"+a+"'");
			 rs=post.executeQuery();
			 
			
			 while (rs.next()) {
				 
				
				 arr.add(rs.getString("pret"));
			 }
			 String s=arr.get(0);
			
			 return s;
		 }catch(Exception e){
			 System.out.println(e);
		 }
		
		
		return null ;
		
	}

	public static void insertOrder(String a11,String a2,String a3,String a4,String a5){
		 try {
			
			
			 int a14=Integer.parseInt(a4);
			 Connection conn=getConection();
			 PreparedStatement post=conn.prepareStatement("INSERT INTO comanda (nume, prenume ,adresa, cantitate,produs) VALUES ('"+a11+"','"+a2+"','"+a3+"','"+a14+"','"+a5+"')");
			 post.executeUpdate();
		 }catch(Exception e){
			 System.out.println(e);
		 }
		}
	public static void deleteOrder(String a1 ,String a2){
		 try {
			 
			 Connection conn=getConection();
			 PreparedStatement post=conn.prepareStatement("DELETE FROM comanda WHERE nume='"+a1+"' and prenume='"+a2+"'");
			 post.executeUpdate();
		 }catch(Exception e){
			 System.out.println(e);
		 }
		}
	
	public static Connection getConection() {
		
	 try {
		 String driver = "com.mysql.cj.jdbc.Driver";  
		 String url = "jdbc:mysql://localhost/store";
		 String user = "root";
		 String pass = "omegaacer1";
		
		Class.forName(driver);
		
		Connection conn=DriverManager.getConnection(url,user,pass);
		return conn;
	 }catch(Exception e) {
		 System.out.println(e);
	 }
	 
		return null;	
	}
	
	public static  ArrayList<String> getAllC() throws Exception {
		 ResultSet rs=null;
		 ArrayList<String> arr=new ArrayList<String>();
		try {
			
			 Connection conn=getConection();
			 PreparedStatement post=conn.prepareStatement("SELECT * FROM clienti");
			 rs=post.executeQuery();
			 
			
			 while (rs.next()) {
				 
				 arr.add(rs.getString("id"));
				 arr.add(rs.getString("nume"));
				 arr.add(rs.getString("prenume"));
				 arr.add(rs.getString("adresa"));
				
			 }
			
			
			 return arr;
		 }catch(Exception e){
			 System.out.println(e);
		 }
		
		
		return null ;
		
	}
	public static  ArrayList<String> getAllP() throws Exception {
		 ResultSet rs=null;
		 ArrayList<String> arr=new ArrayList<String>();
		try {
			
			 Connection conn=getConection();
			 PreparedStatement post=conn.prepareStatement("SELECT * FROM produse");
			 rs=post.executeQuery();
			 
			
			 while (rs.next()) {
				 
				 arr.add(rs.getString("id"));
				 arr.add(rs.getString("nume"));
				 arr.add(rs.getString("pret"));
				 arr.add(rs.getString("cantitate"));
				
			 }
			
			
			 return arr;
		 }catch(Exception e){
			 System.out.println(e);
		 }
		
		
		return null ;
		
	}
	public static  ArrayList<String> getAllO() throws Exception {
		 ResultSet rs=null;
		 ArrayList<String> arr=new ArrayList<String>();
		try {
			
			 Connection conn=getConection();
			 PreparedStatement post=conn.prepareStatement("SELECT * FROM comanda");
			 rs=post.executeQuery();
			 
			
			 while (rs.next()) {
				 
				 arr.add(rs.getString("id"));
				 arr.add(rs.getString("nume"));
				 arr.add(rs.getString("prenume"));
				 arr.add(rs.getString("adresa"));
				 arr.add(rs.getString("cantitate"));
				 arr.add(rs.getString("produs"));
				
			 }
			 return arr;
		 }catch(Exception e){
			 System.out.println(e);
		 }
		
		
		return null ;
		
	}
}
