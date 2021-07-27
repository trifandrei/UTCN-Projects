package AgentHydro;

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
import model.Layer;
import model.LayerType;
import rule.Rule;

public class HydroAgent {

	public HydroAgent() throws ClassNotFoundException, IOException {
		ServerSocket ss = new ServerSocket(2000);

		boolean okl = true;

		while (okl) {
			System.out.println("SERVER::Waiting for clients!!");
			Socket s = ss.accept();
			ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
			ObjectInputStream input = new ObjectInputStream(s.getInputStream());

			Gson gson = new Gson();

			String str = (String) input.readObject();
		
			Request r = gson.fromJson(str, Request.class);
			if (r!=null) {
				Random rand = new Random();
				int x = rand.nextInt(700);
				int y = rand.nextInt(600);
				int w = rand.nextInt(50);
				int h = rand.nextInt(50);
				int rt = rand.nextInt(10);
				Layer hydro = new Layer( x, y, w, h, rt, 0);

				LayerType lt = new LayerType();
				LayerType lt1 = null;
				LayerType lt2 = null;
				District d = new District();
				
				List<LayerType> l=lt.selectAllLayerType();
				Iterator<LayerType> iterator =l.iterator();
				while (iterator.hasNext()) {
					lt2=iterator.next();
					if(lt2.getType()==0) {
						lt1=lt2;
						break;
					}
				}
				List<District> dist=d.selectALLDistrict();
				int size2 = rand.nextInt(dist.size());
				District d1 = dist.get(size2);


				hydro.setLayerType(lt1);
				hydro.setDistrictId(d1);
				

			
				Rule rule = new Rule();

				

				if (rule.checkHidroUnderBuild(hydro)) {
					out.writeObject("The layer it will be build under a building!!\n");
				} else {
					hydro.insertLayer();
					out.writeObject("The Layer was added to data base!!!");
					ss.close();
				}
			}
		}
	}
}
