package logging_example_multithread;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * @author Hank Lee
 * Shared in InformisTry -- https://jumperc2p.github.io/InformisTry/
 * Singleton Object
 */
public class MyLoggerWithFileOutput {
	
	private static  Logger logger;
	
	public static  Logger getLogger() {
		
		if(logger == null) {
			
			logger = Logger.getLogger("MyLoggerWithFileOutput"); 
			
			// use the method "getLogger(String name)" in Logger class to get a Logger object
			logger = Logger.getLogger("MyLogger"); 
			
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
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}  
		}
		 
		 return logger;
	}
}
