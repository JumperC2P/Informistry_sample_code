package timer_example;

import java.util.Timer;

/**
 * @author Hank Lee
 * Shared in InformisTry -- https://jumperc2p.github.io/InformisTry/
 *
 */
public class TimerAndTimerTask {

	public static void main(String[] args) {
		
		// create a new timer and give it a name
		Timer timer = new Timer("PrintTaskTimer");
		
		// schedule the PrintTask class and setup two parameters: delay and period
		long delay = 0;
		long period = 2000;
		timer.scheduleAtFixedRate(new PrintTask(), delay, period);
		System.out.println("Timer has already started.");
		
		
		// record the current time
		Long startTime = System.currentTimeMillis();
		
		
		// use while loop to restrict the execution
		while (true) {
			
			Long endTime = System.currentTimeMillis();
			
			// when the running time comes to 20 seconds, cancel the timer and break the loop.
			if ((endTime-startTime)/(1000*20) == 1) {
				timer.cancel();
				System.out.println("Cancel timer. End the program.");
				break;
			}
			
		}
	}
}
