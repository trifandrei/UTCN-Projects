package openWeather;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class WRequest {

	private final String apiKey = "76af71d0413cc6cbd4d15a02fda1932a";
	private String location;
	private String url;
	public WRequest() {

	}

	public WRequest(String location) {
		this.location = location;
		this.url = "http://api.openweathermap.org/data/2.5/weather?q=" + this.location + "&appid=" + apiKey
				+ "&units=metric";

	}

	public Map<String, Object> jsonToMap(String str) {
		Gson gson = new Gson();
		Map<String, Object> jsonmap1 = gson.fromJson(str, new TypeToken<HashMap<String, Object>>() {
		}.getType());

		return jsonmap1;

	}

	public String getFromJsonMap(String s1, String s2) {
		try {
			StringBuilder rs = new StringBuilder();
			URL urll = new URL(this.url);
			URLConnection conn = urll.openConnection();
			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = rd.readLine()) != null) {
				rs.append(line);

			}
			rd.close();
			Map<String, Object> jsonMap1 = jsonToMap(rs.toString());
			Map<String, Object> jsonMap2 = jsonToMap(jsonMap1.get(s1).toString());

			String str = jsonMap2.get(s2).toString();
			return str;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;

	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
}
