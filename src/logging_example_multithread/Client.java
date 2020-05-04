package logging_example_multithread;

import java.io.*;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Timer;

/**
 * @author Hank Lee
 * Shared in InformisTry -- https://jumperc2p.github.io/InformisTry/
 *
 */
public class Client {
	private final static String ADDRESS = "localhost";
	private final static int PORT = 61023;

	private Socket connection;

	private PrintWriter outputToServer;
	private Scanner inputFromServer;

	public Client() {
		try {
			// create a socket with the address and port
			connection = new Socket(ADDRESS, PORT);

			// get the OutputStream and InputStream from the connection
			outputToServer = new PrintWriter(connection.getOutputStream(), true);
			inputFromServer = new Scanner(connection.getInputStream());
			Scanner inputFromConsole;

			// use while loop to listen feedbacks from server.
			while (true) {

				try {
					// read the message from server
					String serverMessage = inputFromServer.nextLine();

					// print the message from server.
					System.out.println(serverMessage);

					// create a Timer to schedule ClientTask which use to receive the message from
					// ServerTask.
					Timer timer = new Timer("ClientTaskTimer");
					// use delay to let the timer start after 3 seconds.
					long delay = 3000;
					long period = 3000;
					timer.scheduleAtFixedRate(new ClientTask(new Scanner(connection.getInputStream())), delay, period);

					// get input from console and use PrintWriter to send the input to server.
					inputFromConsole = new Scanner(System.in);
					String messageToServer = inputFromConsole.nextLine();
					outputToServer.println(messageToServer);
					// After sending message to server, cancel the timer.
					timer.cancel();

					// check if client wants to end the connection
					if ("q".equals(messageToServer)) {
						System.out.println("End the connection.");
						inputFromConsole.close();
						break;
					}
					// If the server is shut down, end the while loop.
				} catch (NoSuchElementException nsee) {
					nsee.printStackTrace();
					System.out.println("The server is out of service.");
					break;
				}
			}
			System.exit(0);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new Client();
	}
}
