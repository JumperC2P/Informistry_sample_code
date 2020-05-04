package logging_example_multithread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Hank Lee
 * Shared in InformisTry -- https://jumperc2p.github.io/InformisTry/
 *
 */
public class Server {
	
	private final int PORT = 61023;
	private Socket connection;
	private static Logger MyLogger = MyLoggerWithFileOutput.getLogger();
	
	public Server() {
		
		 ServerSocket server = null;
		 List<ClientThreadHandler> clients = new ArrayList<>();
		 
		 try{
			 
			// create a new socket
            server = new ServerSocket(PORT);
            MyLogger.log(Level.INFO, "Starts the server");
            System.out.println("Starts the server");

            while (true) {
                MyLogger.log(Level.INFO, "Waiting for new connections...");
                System.out.println("Waiting for new connections...");
                // accept connections from client
                connection = server.accept();
                clients.add(new ClientThreadHandler(connection));
                MyLogger.log(Level.INFO, "Connection accepted");
                MyLogger.log(Level.INFO, "Start the thread.");
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
