package Server;

import java.io.IOException;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

	private ArrayList<ClientH> clients = new ArrayList<>();

	private ExecutorService pool = Executors.newFixedThreadPool(100);

	@SuppressWarnings("resource")
	public Server() throws IOException, ClassNotFoundException {

		ServerSocket ss = new ServerSocket(9000);
		while (true) {
			System.out.println("SERVER::Waiting for clients!!");
			Socket s = ss.accept();
			System.out.println("Server::I accepted client  no." + clients.size() + "!!");
			ClientH cl = new ClientH(s);
			clients.add(cl);
			pool.execute(cl);
		
		}

	}

}
