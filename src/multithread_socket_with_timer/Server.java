package multithread_socket_with_timer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Hank Lee
 * Shared in InformisTry -- https://jumperc2p.github.io/InformisTry/
 *
 */
public class Server {
	
	private final int PORT = 61023;
	private Socket connection;
	
	public Server() {
		
		 ServerSocket server = null;
		 List<ClientThreadHandler> clients = new ArrayList<>();
		 
		 try{
			 
			// create a new socket
            server = new ServerSocket(PORT);
            System.out.println("Starts the server");

            while (true)
            {
                System.out.println("Waiting for new connections...");
                // accept connections from client
                connection = server.accept();
                clients.add(new ClientThreadHandler(connection));
                System.out.println("Connection accepted");
                System.out.println("Start the thread.");
                clients.get(clients.size()-1).start();
               
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                server.close();
            } catch (IOException e) { }
        }
	}

	public static void main(String[] args) {
		new Server();
	}

}
