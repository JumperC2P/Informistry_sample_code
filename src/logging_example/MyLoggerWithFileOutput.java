package logging_example;

import java.io.IOException;
import java.util.Random;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * @author Hank Lee
 * Shared in InformisTry -- https://jumperc2p.github.io/InformisTry/
 *
 */
public class MyLoggerWithFileOutput {

	public static void main(String[] args) {
		
		// use the method "getLogger(String name)" in Logger class to get a Logger object
		Logger logger = Logger.getLogger("MyLogger"); 
		
		// Declare a FileHandler variable.
		FileHandler fh;
		
		// set the level of the logger as INFO
		logger.setLevel(Level.INFO);
		
		try {
			// create a new FileHandler object with the log file path
			fh = new FileHandler(System.getProperty("user.dir")+"/informistry.log");
			
			// create a SimpleFormatter object to setup the format of log
			SimpleFormatter formatterFH = new SimpleFormatter();  
			// set the SimpleFormatter to the FileHandler
			fh.setFormatter(formatterFH); 
			
			// add the FileHandler to the logger
			logger.addHandler(fh);
			
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
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}  
	}
}
