package pr2.a03.Test;

import java.io.PrintWriter;
import java.util.Locale;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TriangleWithLoggingDemo {
	static Logger logger = Logger.getLogger("triangleLogger");
	
	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		
		logger.setUseParentHandlers(false);
//		logger.setLevel(Level.FINEST);
//		logger.setLevel(Level.FINER);
//		logger.setLevel(Level.FINE);
//		logger.setLevel(Level.CONFIG);
//		logger.setLevel(Level.INFO);
//		logger.setLevel(Level.WARNING);
//		logger.setLevel(Level.SEVERE);
		logger.setLevel(Level.ALL);
		
		ConsoleHandler consoleHandler = new ConsoleHandler();
		consoleHandler.setLevel(Level.FINEST);
//		consoleHandler.setLevel(Level.INFO);
		logger.addHandler((consoleHandler));
		
		FileHandler fileHandler = null;
		try {
			fileHandler = new FileHandler("./logFile_%u_%g.log", 10000, 3, true);
//			Standard Formatter: XMLFormatter
//			fileHandler.setFormatter(new SimpleFormatter());
		} catch (Exception e) {
			e.printStackTrace();
		}
		fileHandler.setLevel(Level.ALL);
		logger.addHandler(fileHandler);
		
		PrintWriter out = new PrintWriter(System.out, false);
		
		logger.info("START------------------- \n << testing triangles ");
		out.print(triangle(5, '*'));
		out.print(triangle(3, '*'));
		logger.info("finished triangles >> \n ------------------------ENDE");
		out.flush();
	}
	
	public static String triangle(int lineCount, char theChar) {
		logger.entering("Logger101", "triangle");
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < lineCount; i++) {
			logger.log ( Level.FINER, "appending LINE");
			sb.append(triangleLine(i+1, theChar));
		}
		logger.exiting("Logger101", "triangle");
		return sb.toString();
	}
	
	public static String triangleLine(int charCount, char theChar) {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < charCount; i++) {
			logger.log ( Level.FINEST, "appending " + theChar);
			sb.append(theChar);
		}
		sb.append('\n');
		return sb.toString();
	}
}
