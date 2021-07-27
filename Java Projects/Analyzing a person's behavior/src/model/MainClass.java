package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class MainClass {
	
	public static int Q2() throws ParseException {
		
		int i=0;
		ReadFile r=new ReadFile();
		String s1=r.list2.get(0).getStime().substring(0, 10);
		String s2=r.getList().get(r.list2.size()-1).getStime().substring(0, 10);
		
		Calendar cal1 = new GregorianCalendar();
	     Calendar cal2 = new GregorianCalendar();

	     SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");

	     Date date=f.parse(s1);
	     cal1.setTime(date);
	     date=f.parse(s2);
	     cal2.setTime(date);
		
	     i=r.daysBetween(cal1.getTime(), cal2.getTime());

		 
		return  i;
	}

	public static void Q3() {

		
		ReadFile r=new ReadFile();
		Map<String,Long> map = new HashMap<>();
		
		map=r.apearActivity();
		
		for (Entry<String, Long> entry : map.entrySet()) {
			System.out.println("Activitatea "+entry.getKey()+" apare de "+entry.getValue()+" ori!!");
			}
	}
	
	public static void Q4(){
		ReadFile r=new ReadFile();
		r.eachActivity();
	}
	public static void Q5(){
		ReadFile r=new ReadFile();
		r.durationEachActivyty();
	}
	public static void Q6(){
		ReadFile r=new ReadFile();
		r.durationActivity();
	}
	
	public static void Q7(){
		ReadFile r=new ReadFile();
		r.lessThen();
	}
	public static void main(String[] args) throws ParseException {
	 
		
		Lamda obj=(i)->{System.out.println("In monitor apar "+i+" zile!!");};

		int i=Q2();		
		obj.show(i);
		System.out.println("---------------------------------------------------------------------");
		Q3();
		System.out.println("---------------------------------------------------------------------");
		Q4();
		System.out.println("---------------------------------------------------------------------");
		Q5();
		System.out.println("---------------------------------------------------------------------");
		Q6();
		System.out.println("---------------------------------------------------------------------");
		Q7();
	}

}
