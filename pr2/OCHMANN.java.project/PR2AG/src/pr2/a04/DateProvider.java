package pr2.a04;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class DateProvider {
	public static final Logger log = MyLogger.getLogger();
	
	protected static int errorCount = 0;
	protected static String logfile = MyLogger.getLogfile();
	protected static String logfilepath = "./" + logfile;

	protected static List<Date> provideDates(){
		int[][] daten = {
				{41, 10, 3001},
				{22, 1, 2001},
				{7, 7, 3009},
				{32, 14, 2009},
				{30, 2, 2009},
				{15, 13, 2009},
				{15, 7, 2009},
				{100, 100, 2009},
				{0, 1, 2009}
		};
		List<Date> dates = new ArrayList<Date>();
		
		for(int[] datum : daten) {
			try {
				dates.add(new Date(datum[0], datum[1], datum[2]));
			} catch (IllegalDateException e) {
				errorCount ++;
				log.warning(e.toString());
			}
		}
		return dates;
	}
	
	public static String getLogfile() {
		return logfile;
	}
	
	public static String getLogfilepath() {
		return logfilepath;
	}
	
	public static int getErrorcount() {
		return errorCount;
	}
}
