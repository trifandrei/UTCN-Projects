package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MonitoredData {

	String start_time;
	String end_time;
	String activity;
	 
	public MonitoredData(String t1,String t2,String act) {
	  
		this.start_time=t1;
		this.end_time=t2;
		this.activity=act;
		
	}
	
	public void setStime(String s) {
		this.start_time=s;
	}
	public String getStime() {
		return start_time;
	}
	
	public void setEtime(String s) {
		this.end_time=s;
	}
	public String getEtime() {
		return end_time;
	}
	
	
	public void setActivity(String s) {
		this.activity=s;
	}
	public String getActivity() {
		return activity;
	}
	@Override
	public String toString(){
		
		return " ST="+this.start_time+" ET="+this.end_time+" AC="+this.activity;
		
		
	}

	public long getTimes(String t1, String t2) {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date1 = null;
		try {
			date1 = format.parse(t1);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Date date2 = null;
		try {
			date2 = format.parse(t2);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long  difference = (date2.getTime() - date1.getTime())/1000;
		return difference;
		
	}
}
