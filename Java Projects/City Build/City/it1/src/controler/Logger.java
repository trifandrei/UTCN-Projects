package controler;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;

import model.District;

public class Logger {

	public Logger() {

	}

	// find the district with the lower rating and return his id
	public int districtLogg() {
		float min = 90000;
		int minId = 0;
		District d = new District();
		

		Iterator<District> iterator = d.selectALLDistrict().iterator();
		while (iterator.hasNext()) {
			District d2 = iterator.next();

			if (d2.getDistrictRating(d2.getId()) <= min) {
				min = d2.getDistrictRating(d2.getId());
				minId = d2.getId();
			}
		}
		return minId;
	}

	// show some information about city
	public void show(String str) {
		DateFormat dateFormat = new SimpleDateFormat("MM/dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		System.out.println(dateFormat.format(cal.getTime())+": "+str);

	}

}
