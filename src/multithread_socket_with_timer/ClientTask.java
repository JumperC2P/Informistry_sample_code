package multithread_socket_with_timer;

import java.util.Scanner;
import java.util.TimerTask;

/**
 * @author Hank Lee
 * Shared in InformisTry -- https://jumperc2p.github.io/InformisTry/
 *
 */
public class ClientTask extends TimerTask {
	
	private Scanner inputFromServer;
	
	public ClientTask(Scanner inputFromServer) {
		this.inputFromServer = inputFromServer;
	}

	@Override
	public void run() {
		
		try {
			// Print the message from server.
			System.out.println("ClientTask: " + inputFromServer.nextLine());
		} catch (Exception e) {}

	}

}
