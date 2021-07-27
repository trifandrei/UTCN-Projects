package model;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import view.window;

public class Generator {
		private ArrayList<Client> clienti = new ArrayList<Client>();
		Random r=new Random();
		private int timpSosire;
		private int timpServire;
		private int nrClientiGener=0;
		private int i=0;
		ScheduledExecutorService make = Executors.newScheduledThreadPool(1);
		private Runnable generator=new Runnable() {

			@Override
			public void run() {
				timpSosire =window.getMinSosire() + r.nextInt(window.getMaxSosire() - window.getMinSosire());
				timpServire = window.getMinServire() + r.nextInt(window.getMaxServire() - window.getMinServire());
				i++;
				Client client=new Client(i,timpSosire,timpServire);
				clienti.add(client);
				nrClientiGener++;
				
			}
			
		};
		public Generator() {
			for(int j=0;j<100;j++) {
				make.execute(generator);
			}
		}
		public int getNrClienti() {
			return nrClientiGener;
		}
		public Client getClient(int i) {
		 return clienti.get(i);
		}
}
