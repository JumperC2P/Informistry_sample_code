package timer_example.socket;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Timer;

/**
 * @author Hank Lee
 * Shared in InformisTry -- https://jumperc2p.github.io/InformisTry/
 *
 */
public class Server {
	
	private final int PORT = 61023;
	private Socket connection;
	
	private Scanner inputFromClient;
    private PrintWriter outputToClient;
	
	public Server() {
		
		 ServerSocket server = null;
		 
		 try{
			 
			// create a new socket
            server = new ServerSocket(PORT);
            System.out.println("Starts the server");

            while (true)
            {
                System.out.println("Waiting for new connections...");
                // accept connections from client
                connection = server.accept();
                System.out.println("Connection accepted");

                // get the OutputStream and InputStream from connection
                outputToClient = new PrintWriter(connection.getOutputStream(),true);
                inputFromClient = new Scanner(connection.getInputStream());
                
                try {
                	
                	while (true) {
                		// send messages to client.
                		outputToClient.println("Send messages to server. Or if you want to end the connection, please enter \"q\":");
                		
                		// create new Timer to schedule TimerTask and start its schedule.
                		Timer timer = new Timer("ServerTaskTimer");
                		// use delay to let the timer start after 3 seconds.
                		long delay = 3000;
                		long period = 3000;
                		timer.scheduleAtFixedRate(new ServerTask(outputToClient), delay, period);
                		// take input from client
                		String clientMessage = inputFromClient.nextLine();
                		// Once it receive the message from client, cancel the timer.
                		timer.cancel();
                		System.out.println("Client says: " + clientMessage);
                		// check if the client wants to end the connection.
                		if ("q".equals(clientMessage)) {
                			System.out.println("Client ends the connection.");
                			break;
                		}
                	}
                }catch (NoSuchElementException ne) {
                	System.out.println("Client quits the game.");
                }
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
