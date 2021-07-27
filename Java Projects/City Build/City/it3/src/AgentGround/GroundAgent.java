package AgentGround;

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


public class GroundAgent {

	public GroundAgent() throws IOException, ClassNotFoundException {
		ServerSocket ss = new ServerSocket(1000);

		boolean okl = true;

		while (okl) {
			System.out.println("SERVER::Waiting for clients!!");
			Socket s = ss.accept();
			ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
			ObjectInputStream input = new ObjectInputStream(s.getInputStream());

			Gson gson = new Gson();

			String str = (String) input.readObject();

			Request r = gson.fromJson(str, Request.class);
			if (r != null) {
				Random rand = new Random();
				int x = rand.nextInt(700);
				int y = rand.nextInt(600);
				int w = rand.nextInt(50);
				int h = rand.nextInt(50);
				int rt = rand.nextInt(10);
				Layer ground = new Layer(x, y, w, h, rt, 0);

				LayerType lt = new LayerType();
				LayerType lt1 = null;
				LayerType lt2 = null;
				District d = new District();

				List<LayerType> l = lt.selectAllLayerType();
				Iterator<LayerType> iterator = l.iterator();
				while (iterator.hasNext()) {
					lt2 = iterator.next();
					if (lt2.getType() == 1) {
						lt1 = lt2;
					}
				}
				List<District> dist = d.selectALLDistrict();
				int size2 = rand.nextInt(dist.size());
				District d1 = dist.get(size2);

				ground.setLayerType(lt1);
				ground.setDistrictId(d1);

				ground.insertLayer();
				out.writeObject("The Layer was added to data base!!!");
				ss.close();
			}
		}
	}
}
