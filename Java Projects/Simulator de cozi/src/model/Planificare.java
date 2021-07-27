package model;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import view.window;


public class Planificare {
	
	int i;
	private Client client;
	ScheduledExecutorService e= Executors.newScheduledThreadPool(3);
	private Simulare Coada1 = new Simulare(1);
	private Simulare Coada2 = new Simulare(2);
	private Simulare Coada3 = new Simulare(3);
	private Generator generator;

	public  Planificare() {
		int ok=0;
		long start = System.currentTimeMillis();
		long end = start + window.getTimp();
		generator = new Generator();
		Coada1 = new Simulare(0);
		Coada2 = new Simulare(1);
		Coada3 = new Simulare(2);
		e.execute(Coada1);
		e.execute(Coada2);
		e.execute(Coada3);
		Coada1.addClient(generator.getClient(i));
		i++;
		Coada2.addClient(generator.getClient(i));
		i++;
		Coada3.addClient(generator.getClient(i));
		i++;

		while (ok== 0) {
			client = generator.getClient(i);
			int min = Min(Coada1.getClient(), Coada2.getClient(), Coada3.getClient());
			if (Coada1.getClient() == min) {

				if (Coada1.getCapacity() != 0) {
					Coada1.addClient(client);
					i++;
				}
			} else {
				if (Coada2.getClient() == min) {
					if (Coada2.getCapacity() != 0) {
						
						Coada2.addClient(client);
						i++;
					}
				} else {
					if (Coada3.getClient() == min) {

						if (Coada3.getCapacity() != 0) {
							
							Coada3.addClient(client);
							i++;
						}
					}
				}
			}
			if (Coada1.isReady() == 1 && Coada2.isReady()==1 && Coada3.isReady() == 1) {
				e.shutdown();
			}
			if (e.isTerminated() == true || start > end)
				ok = 1;
		}
		
		window.show(" Simulare incheiata!");
		
		window.show(" La coada: " +1+ " se asteapta in medie "  + Coada1.getAverageAsteptare());
		window.show(" La coada: " +1+ " media timpului de servire este " + Coada1.getAverageServire());
		
		window.show(" La coada: " +2+ " se asteapta in medie " + Coada2.getAverageAsteptare());
		window.show(" La coada: " + 2+ " media timpului de servire este " +  Coada2.getAverageServire());
		
		window.show(" La coada: " +3+ " se asteapta in medie " + Coada3.getAverageAsteptare());
		window.show(" La coada: " +3+ " media timpului de servire este " +  Coada3.getAverageServire());
		
		window.show(" La coada: "+1+" au fost "+Coada1.nrClienti()+" clienti!");
		window.show(" La coada: "+2+" au fost "+Coada2.nrClienti()+" clienti!");
		window.show(" La coada: "+3+" au fost "+Coada3.nrClienti()+" clienti!");
		
		window.show(" Gasiti informati referitoare la cozi in Log.txt ");
	}

	public  int Min(int x1, int x2, int x3) {
		int min = 99999;
		if (x1 <= x2)
			min = x1;
		else
			min = x2;
		if (x3 < min)
			min = x3;
		return min;
	}
}