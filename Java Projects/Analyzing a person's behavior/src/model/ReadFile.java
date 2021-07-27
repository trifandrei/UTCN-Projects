package model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReadFile {

	
	List<MonitoredData> list2=new ArrayList<MonitoredData>();
	
	public ReadFile() {
		List<String> list=new ArrayList<String>();
		String fileName = "activity.txt";

		//read file into stream, try-with-resources
		try (Stream<String> stream = Files.lines(Paths.get(fileName))) {

			list=stream.collect(Collectors.toList());
		list.forEach(n->{ 
			MonitoredData m=new MonitoredData(getSStime(n),getEStime(n),getAC(n));
			list2.add(m);
			}
		);
		   
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	
	public String getSStime(String s) {
		String s1=s.substring(0,19);
		
		return s1;
		
	}
	

	public String getEStime(String s) {
		String s1=s.substring(21,40);
		
		return s1;
		
	}

	public String getAC(String s) {
		String s1=s.substring(42,s.length()-1);
		
		return s1;
		
	}
	
	public List<MonitoredData> getList() {
		
		return list2;
		
	}

	 public int daysBetween(Date d1, Date d2){
         return (int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
 }
	
	 
	public Map<String, Long> apearActivity() {
		
		long count1= list2.stream().filter(s->s.getActivity().equals("Toileting")).count();
		long count2 = list2.stream().filter(s->s.getActivity().equals("Sleeping	")).count();
		long count3 = list2.stream().filter(s->s.getActivity().equals("Spare_Time/TV	")).count();
		long count4 = list2.stream().filter(s->s.getActivity().equals("Showering")).count();
		long count5 = list2.stream().filter(s->s.getActivity().equals("Snack")).count();
		long count6 = list2.stream().filter(s->s.getActivity().equals("Lunch	")).count();
		long count7 = list2.stream().filter(s->s.getActivity().equals("Grooming")).count();
		long count8 = list2.stream().filter(s->s.getActivity().equals("Breakfast")).count();
		long count9 = list2.stream().filter(s->s.getActivity().equals("Leaving")).count();
		
		Map<String,Long> map = new HashMap<>();
		map.put("Toileting", count1);
		map.put("Sleeping",count2);
		map.put("Spare_Time/TV",count3);
		map.put("Showering",count4);
		map.put("Snack",count5 );
		map.put("Lunch",count6 );
		map.put("Grooming",count7);
		map.put("Breakfast",count8);
		map.put("Leaving",count9);
		
		
		return map;
		
	}
	
	public void eachActivity() {
		
		ArrayList<String> a =new ArrayList<String>();
		a.add("28");
		a.add("29");
		a.add("30");
		a.add("01");
		a.add("02");
		a.add("03");
		a.add("04");
		a.add("05");
		a.add("06");
		a.add("07");
		a.add("08");
		a.add("09");
		a.add("10");
		a.add("11");

		for(int i=0;i<a.size();i++) {
		
		String t=a.get(i);
	
		Long c1=list2.stream().filter(s->s.start_time.substring(8, 10).equals(t) && s.getActivity().equals("Toileting")).collect(Collectors.counting()); 
		System.out.println(" ziua "+t+" "+c1+ " "+" Toileting ");
		
		 Long c2=list2.stream().filter(s-> s.start_time.substring(8, 10).equals(t) && s.getActivity().equals("Sleeping	")).collect(Collectors.counting()); 
		 System.out.println(" ziua "+t+" "+c2+ " "+" Sleeping ");
		 
		 Long  c3=list2.stream().filter(s-> s.start_time.substring(8, 10).equals(t) && s.getActivity().equals("Spare_Time/TV	")).collect(Collectors.counting()); 
		 System.out.println(" ziua "+t+" "+c3+ " "+" Spare_Time/TV ");
		
		
		 Long c4=list2.stream().filter(s->s.start_time.substring(8, 10).equals(t) && s.getActivity().equals("Showering")).collect(Collectors.counting()); 
		 System.out.println(" ziua "+t+" "+c4+ " "+" Showering ");
		 
		 Long  c5=list2.stream().filter(s->s.start_time.substring(8, 10).equals(t) && s.getActivity().equals("Snack")).collect(Collectors.counting()); 
		 System.out.println(" ziua "+t+" "+c5+ " "+" Snack ");
		 
		 Long  c6=list2.stream().filter(s->s.start_time.substring(8, 10).equals(t) && s.getActivity().equals("Lunch	")).collect(Collectors.counting()); 
		 System.out.println(" ziua "+t+" "+c6+ " "+" Lunch	 ");
		
		 Long c7=list2.stream().filter(s->s.start_time.substring(8, 10).equals(t) && s.getActivity().equals("Grooming")).collect(Collectors.counting()); 
		 System.out.println(" ziua "+t+" "+c7+ " "+" Grooming ");
		
		 Long c8=list2.stream().filter(s->s.start_time.substring(8, 10).equals(t) && s.getActivity().equals("Breakfast")).collect(Collectors.counting()); 
		 System.out.println(" ziua "+t+" "+c8+ " "+" Breakfast ");
		
		 Long  c9=list2.stream().filter(s->s.start_time.substring(8, 10).equals(t) && s.getActivity().equals("Leaving")).collect(Collectors.counting()); 
		 System.out.println(" ziua "+t+" "+c9+ " "+"Leaving");
		}
		
		}
		
	public void durationEachActivyty() {
		
		list2.forEach(s->System.out.println(s.activity+" "+s.getTimes(s.getStime(),s.getEtime())+" sec"));
		
		
		
	}
	public void durationActivity(){
		
		long s1=list2.stream().filter(s->s.getActivity().equals("Toileting")).collect(Collectors.summingLong(s->s.getTimes(s.getStime(),s.getEtime())));	
		 System.out.println("Toileting "+s1/60+" min ");
		 
		 s1=list2.stream().filter(s->s.getActivity().equals("Sleeping	")).collect(Collectors.summingLong(s->s.getTimes(s.getStime(),s.getEtime())));	
		 System.out.println("Sleeping "+s1/60+" min");
		 
		 s1=list2.stream().filter(s->s.getActivity().equals("Spare_Time/TV	")).collect(Collectors.summingLong(s->s.getTimes(s.getStime(),s.getEtime())));	
		 System.out.println("Spare_Time/TV "+s1/60 +" min");
		 
		 s1=list2.stream().filter(s->s.getActivity().equals("Showering")).collect(Collectors.summingLong(s->s.getTimes(s.getStime(),s.getEtime())));	
		 System.out.println("Showering "+s1/60 +" min");
		 
		 s1=list2.stream().filter(s->s.getActivity().equals("Snack")).collect(Collectors.summingLong(s->s.getTimes(s.getStime(),s.getEtime())));	
		 System.out.println("Snack "+s1+" min");
		 
		 s1=list2.stream().filter(s->s.getActivity().equals("Lunch	")).collect(Collectors.summingLong(s->s.getTimes(s.getStime(),s.getEtime())));	
		 System.out.println("Lunch "+s1/60 +" min");
		 
		 s1=list2.stream().filter(s->s.getActivity().equals("Grooming")).collect(Collectors.summingLong(s->s.getTimes(s.getStime(),s.getEtime())));	
		 System.out.println("Grooming "+s1/60 +" min");
		 
		 s1=list2.stream().filter(s->s.getActivity().equals("Breakfast")).collect(Collectors.summingLong(s->s.getTimes(s.getStime(),s.getEtime())));	
		 System.out.println("Breakfast "+s1/60 +" min");
		 
		 s1=list2.stream().filter(s->s.getActivity().equals("Leaving")).collect(Collectors.summingLong(s->s.getTimes(s.getStime(),s.getEtime())));	
		 System.out.println("Leaving "+s1/60 +" min");
	}


	public void lessThen() {
		ArrayList<String> map=new ArrayList<String>();
		map.add("Toileting");
		map.add("Sleeping	");
		map.add("Spare_Time/TV	");
		map.add("Showering");
		map.add("Snack");
		map.add("Lunch	");
		map.add("Grooming");
		map.add("Breakfast");
		map.add("Leaving");
		
		for(String a:map) {
			
		Double s1=list2.stream().filter(s->s.getActivity().equals(a)).collect(Collectors.averagingLong(s->s.getTimes(s.getStime(),s.getEtime())));	
		 
		if(s1<60*90/100)
			 System.out.println(a);
		}
	}
}