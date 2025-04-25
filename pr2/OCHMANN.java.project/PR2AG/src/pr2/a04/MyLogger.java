package pr2.a04;

import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


// Der Quellcode dieser Klasse stammt aus dem Foliensatz von Moodle
public final class MyLogger {
	public static Logger getLogger() {
		init();
		return Logger.getLogger(LOGGER_NAME);
	}
	
	private MyLogger(){
		
	}
	
	private final static String LOGGER_NAME = "log";
	private final static String LOG_FILE_NAME = "dateLogFile";
	
	private static Logger logger;
	private static boolean INIT_DONE = false;
	
	private static void init() {
		if (INIT_DONE) {
			return;
		}
		INIT_DONE = true;
		
		logger = Logger.getLogger(LOGGER_NAME);
		logger.setUseParentHandlers(false);
		logger.setLevel(Level.ALL); //Finest vs ALL???
		Handler consoleHandler = new ConsoleHandler();
		consoleHandler.setLevel(Level.ALL);
		
		FileHandler fileHandler = null;
		try {
			fileHandler = new FileHandler("./" + LOG_FILE_NAME + "_%u_%g.log"
										  , 10000, 3, true);
			fileHandler.setFormatter(new SimpleFormatter());
		} catch (Exception e) {
			e.printStackTrace();
		}
		fileHandler.setLevel(Level.ALL);
		
		logger.addHandler(consoleHandler);
		logger.addHandler(fileHandler);
	}
}
