package logging_example_multithread;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Hank Lee
 * Shared in InformisTry -- https://jumperc2p.github.io/InformisTry/
 *
 */
public class ClientThreadHandler extends Thread {
	
	private Scanner inputFromClient;
    private PrintWriter outputToClient;
    private static Logger MyLogger = MyLoggerWithFileOutput.getLogger();
	
	public ClientThreadHandler(Socket connection) {
		
		// get the OutputStream and InputStream from connection
        try {
			outputToClient = new PrintWriter(connection.getOutputStream(),true);
			inputFromClient = new Scanner(connection.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		
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
          		MyLogger.log(Level.INFO, ("Client says: " + clientMessage));
          		// check if the client wants to end the connection.
          		if ("q".equals(clientMessage)) {
          			MyLogger.log(Level.INFO, "Client ends the connection.");
          			break;
          		}
          	}
          }catch (NoSuchElementException ne) {
        	  MyLogger.log(Level.INFO, "Client quits the game.");
          }
	}
}
