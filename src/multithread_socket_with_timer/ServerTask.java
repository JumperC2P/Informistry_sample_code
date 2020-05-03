package multithread_socket_with_timer;

import java.io.PrintWriter;
import java.util.TimerTask;

/**
 * @author Hank Lee
 * Shared in InformisTry -- https://jumperc2p.github.io/InformisTry/
 *
 */
public class ServerTask extends TimerTask {
	
	private PrintWriter outputToClient;
	
	public ServerTask (PrintWriter outputToClient) {
		this.outputToClient = outputToClient;
	}

	@Override
	public void run() {
		
		try {
			// Send the message to Client.
			outputToClient.println("Are you still there?");
		} catch (Exception e) {}

	}

}
