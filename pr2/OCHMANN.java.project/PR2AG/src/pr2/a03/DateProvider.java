package pr2.a03;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class DateProvider {
	
	public static final Logger log = MyLogger.getLogger();
	
	public static List<Date> provideDates(){
		List<Date> dates = new ArrayList<Date>();
		List <Date> datesTemp = new ArrayList<Date>(List.of(
				new Date(21,10,2000),
				new Date(22,1,2001),
				new Date(7,7,3009),
				new Date(31,4,2009),
				new Date(30,2,2009),
				new Date(15,13,2009),
				new Date(0,1,2009)
		));
		for(Date date : datesTemp) {
			dates.add(date);
			log.warning(date + " wurde der Datumsliste hinzugefügt!");
		}
		return dates;
	}
}
