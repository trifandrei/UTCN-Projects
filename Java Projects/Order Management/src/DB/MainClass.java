package DB;

import java.lang.reflect.Method;


import view.GUI;
public class MainClass {

	
	public static void main(String[] args) {
		try {
			Method method=ConnectionDB.class.getDeclaredMethod("getConection");
			method.invoke(null);
		}catch(Exception e1) {
			System.out.println(e1);
		}
		try {
			Method method1=ConnectionDB.class.getDeclaredMethod("createTClienti");
			method1.invoke(null);
			
	 	}catch(Exception e1) {
	 		System.out.println(e1);
	}
		try {
			Method method1=ConnectionDB.class.getDeclaredMethod("createTProduse");
			method1.invoke(null);
			
	 	}catch(Exception e1) {
	 		System.out.println(e1);
	}
		try {
			Method method1=ConnectionDB.class.getDeclaredMethod("createTOrder");
			method1.invoke(null);
			
	 	}catch(Exception e1) {
	 		System.out.println(e1);
	}
	new GUI();
	}
	
}
