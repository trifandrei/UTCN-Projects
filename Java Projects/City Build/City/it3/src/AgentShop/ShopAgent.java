package AgentShop;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import com.google.gson.Gson;

import Request.Request;
import model.District;
import model.Place;
import model.PlaceType;
import rule.Rule;

public class ShopAgent {

	private static final String intention = "shop";

	public ShopAgent() throws IOException, ClassNotFoundException {

		ServerSocket ss = new ServerSocket(4000);

		boolean ok1 = true;

		while (ok1) {
			System.out.println("SERVER::Waiting for clients!!");
			Socket s = ss.accept();
			ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
			ObjectInputStream input = new ObjectInputStream(s.getInputStream());

			Gson gson = new Gson();

			String str = (String) input.readObject();
			System.out.println(str);
			Request r = gson.fromJson(str, Request.class);
			if (r!=null) {
				Random rand = new Random();
				int x = rand.nextInt(700);
				int y = rand.nextInt(600);
				int w = rand.nextInt(50);
				int h = rand.nextInt(50);
				int rt = rand.nextInt(10);
				Place shop = new Place(intention, x, y, w, h, "", rt, 0);

				District d = new District();
				PlaceType pt = new PlaceType();
				
				PlaceType pt1 = null;
				PlaceType pt2 = null;
				
				List<PlaceType> l=pt.selectAllPlaceType();
				Iterator<PlaceType> iterator =l.iterator();
				while (iterator.hasNext()) {
					pt2=iterator.next();
					if(pt2.getType()==1) {
						pt1=pt2;
						break;
					}
				}
				List<District> dist=d.selectALLDistrict();
				int size2 = rand.nextInt(dist.size());
				District d1 = dist.get(size2);

				shop.setDistrictId(d1);
				shop.setPlaceTypeId(pt1);

				boolean ok = false;
				Rule rule = new Rule();

				if (!rule.checkBuildOutOfMap(shop)) {
					ok = true;
					try {
						out.writeObject("The building is out of the map!!\n");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if (rule.checkBuildOnBuild(shop)) {
					ok = true;
					try {
						out.writeObject("The building it will be build over another build!!\n");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (rule.checkBuildOnWater(shop)) {
					ok = true;
					try {
						out.writeObject("The building is on water!!\n");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (!rule.checkArea(shop)) {
					ok = true;
					try {
						out.writeObject("The building has a area to big!!\n");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}if (!rule.checkMinArea(shop)) {
					ok = true;
					try {
						out.writeObject("The building has a area to small!!\n");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else if (!ok) {
					shop.insertPlace();
					try {
						out.writeObject("The building was inserted!!\n");
						ss.close();
						break;
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	public String getIntention() {
		return intention;
	}
}