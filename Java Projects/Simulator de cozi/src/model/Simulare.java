package model;



import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import view.window;

public class Simulare implements Runnable {
	
	
	private BlockingQueue<Client> coadaclienti = new ArrayBlockingQueue<Client>(7);
	private Client client;
	private int ok=0;
	private Thread T= new Thread();
	private int id = 1;
	private long finTime, currTime, initTime, avAsteptare, avServire;
	private int nrclienti;
	ScheduledExecutorService e = Executors.newScheduledThreadPool(1);

	public  Simulare(int a) {
		this.id = a;
		currTime = System.currentTimeMillis();
		this.finTime = currTime + window.getTimp();
	}
	
	
	
	private Runnable block= new Runnable (){
		public   void run(){
			try {
				Thread.sleep(client.getServireTime());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	};
	
	public void run() {
		
		 File f=new File("LOG.txt");
		try {
			f.createNewFile();
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}
		PrintWriter p = null;
		try {
			p = new PrintWriter(f);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		nrclienti = 0;
		initTime = System.currentTimeMillis();
		while (currTime <= finTime) {
			if (coadaclienti.isEmpty() == false) {
				

				try {
					
					client = coadaclienti.take();
					T= new Thread(block);
					
					String s="Clientul "+client.getID()+" a sosit la coada "+(id+1)+"\n";
					p.println(s);
					
					initTime = initTime + client.getSosireTime();
					nrclienti++;
					T.start();
			
					T.join();
					
					System.out.println("RUNING");
					
					String s1="Clientul "+client.getID()+" a plecat de la coada "+(id+1);
					p.println(s1);
					
					avServire =avServire + client.getServireTime();
					avAsteptare = avAsteptare + (System.currentTimeMillis() - initTime);
					
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				

			}
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			currTime = System.currentTimeMillis();
			
		}
		ok= 1;
		avAsteptare = avAsteptare / nrclienti;
		avServire =avServire / nrclienti;
		p.close();
	}

	public  void addClient(Client client) {
		try {
			coadaclienti.put(client);
			Thread.sleep(client.getSosireTime());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public  double getAverageAsteptare() {
		return avAsteptare;
	}

	public  double getAverageServire() {
		return avServire;
	}
	public  String nrClienti(){
		String s=" "+(nrclienti);
		return s;
	}


	public  int getClient() {
		return coadaclienti.size();
	}

	public  int isReady() {
		return ok;
	}

	public  int getCapacity() {
		return coadaclienti.remainingCapacity();
	}
	
}
