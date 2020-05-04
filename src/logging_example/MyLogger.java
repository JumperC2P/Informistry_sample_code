package logging_example;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Hank Lee
 * Shared in InformisTry -- https://jumperc2p.github.io/InformisTry/
 *
 */
public class MyLogger {

	public static void main(String[] args) {
		
		// use the method "getLogger(String name)" in Logger class to get a Logger object
		Logger logger = Logger.getLogger("MyLogger"); 
		
		// set the level of the logger as INFO
		logger.setLevel(Level.INFO);
		
		// use java.util.Random to receive a random number
		Random random = new Random();
		
		//setup a range to random number
		int range = 50;
		
		while (true) {
			// get a random number
			int number = random.nextInt(range);
			
			logger.log(Level.INFO, "The random number is :" + number);
			
			// when the remainder of random number divided 7 is 0 , stop the while loop.
			if (number % 7 == 0) {
				break;
			}
		}
	}
}
