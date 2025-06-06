package pr2.a03;

import java.util.logging.Logger;

public class Date {
	
	public static final Logger log = MyLogger.getLogger();
	
	protected int day;
	protected int month;
	protected int year;
	protected final static int[] days = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	
	public Date(int day, int month, int year) {
		this.day = day;
		this.month = month;
		this.year = year;
		logMessage();
	}
	
	public int getDay() {
		return day;
	}
	
	public int getMonth() {
		return month;
	}
	
	public int getYear() {
		return year;
	}
	
	public String checkDate() {
		if(year < 0 || year > 3000) {
			return "year is out of scope [0..3000]";
		}
		if(month < 1 || month > 12) {
			return "month is out of scope [1..12]";
		}
		if(day < 1 || day > days[month]) {
			if(days[month] == 28) {
				return "day is out of scope[1..28]";
			}
			if(days[month] == 30) {
				return "day is out of scope[1..30]";
			}
			return "day is out of scope[1..31]";
		}
		return "";
	}
	
	public void logMessage() {

		StringBuilder sb = new StringBuilder();

		if (checkDate().length() == 0) {
			sb.append("korrektes Datum: ");
			sb.append(toString());
			log.finer(sb.toString());
		} else {
			sb.append(String.format("%-10s%s", "falsches ", "Datum: "));
			sb.append(toString());
			sb.append(": ");
			sb.append(checkDate());
			log.severe(sb.toString());
		}
		return;
	}
	
	public String toString(){
		return String.format("%02d.%02d.%4d", day, month, year);
	}
}