package pr2.a04;

import java.util.logging.Logger;

public class Date {
	
	public static final Logger log = MyLogger.getLogger();
	
	protected int day;
	protected int month;
	protected int year;
	protected final static int[] days = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	
	public Date(int day, int month, int year) throws IllegalDateException {
		this.day = day;
		this.month = month;
		this.year = year;
		if(checkDate().length() > 0) {
			throw new IllegalDateException(this, logMessage());
		}
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
		String a = "";
		String b = "";
		String c = "";
		StringBuilder sb = new StringBuilder();
		
		if(year < 0 || year > 3000) {
			a = "year is out of scope [0..3000]";
		}
		if(month < 1 || month > 12) {
			b = "month is out of scope [1..12]";
			if(day < 1 || day > 31) {
				c = "day is out of scope[1..31]";
			}
		} else {
			if(day < 1 || day > days[month]) {
				if(days[month] == 28) {
					c = "day is out of scope[1..28]";
				}
				if(days[month] == 30) {
					c = "day is out of scope[1..30]";
				}
				c = "day is out of scope[1..31]";
			}
		}
			
		sb.append(a);
		if (a.length() > 0 && (b.length() > 0 || c.length() > 0)) {
			sb.append(", ");
		}
		sb.append(b);
		if (b.length() > 0 && c.length() > 0) {
			sb.append(", ");
		}
		sb.append(c);
		
		return sb.toString();
	}
	
	public String logMessage() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("%-10s%s", "falsches ", "Datum: "));
		sb.append(toString());
		sb.append(": ");
		sb.append(checkDate());
		return sb.toString();
	}
	
	public String toString(){
		return String.format("%02d.%02d.%4d", day, month, year);
	}
}