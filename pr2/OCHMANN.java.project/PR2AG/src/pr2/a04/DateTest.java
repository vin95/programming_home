package pr2.a04;

import java.io.PrintWriter;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DateTest {
	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		
		final Logger log = MyLogger.getLogger(); // public static is not permitted???
		
		log.setLevel(Level.SEVERE);
//		log.finest("Message with level finest");
//		log.finer("Message with level finer");
//		log.fine("Message with level fine");
//		log.config("Message with level config");
//		log.info("Message with level info");
//		log.warning("Message with level warning");
//		log.severe("Message with level severe");
		
		PrintWriter out = new PrintWriter(System.out);
		List<Date> dateList = DateProvider.provideDates();
		out.println("Date objects provided by DateProvider:");
		for(Date date : dateList) {
			out.println(date);
		}	
		out.flush();
	}
}
