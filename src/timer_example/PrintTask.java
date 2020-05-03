package timer_example;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.TimerTask;

/**
 * @author Hank Lee
 * Shared in InformisTry -- https://jumperc2p.github.io/InformisTry/
 *
 */
public class PrintTask extends TimerTask {

	@Override
	public void run() {
		
		// Gets the system default time-zone.
    	ZoneId zone = ZoneId.systemDefault();
    	
    	// Returns the scheduled execution time of the most recent actual execution of this task.
    	Long scheduledExecutionTime = this.scheduledExecutionTime();
    	
    	// Obtains an instance of LocalDateTime from an Instant and zone ID.
    	LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(scheduledExecutionTime), zone);
    	
        System.out.println("Print message at: "+ dateTime);

	}

}
