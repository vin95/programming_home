package pr2.a09;

import java.awt.geom.Point2D;
import java.util.Scanner;

public class SmileyPerson extends SmileyModel{
	protected String lastname;
	protected String firstname;
	protected int year;
	protected String personAsString;
	
	public SmileyPerson (Point2D.Double position, double radiusHead, boolean smile, String lastname, String firstname, int year) {
		super(position, radiusHead, smile);
		this.lastname = lastname;
		this.firstname = firstname;
		this.year = year;
		personAsString = toString();
	}
	
	public SmileyPerson (String lastname, String firstname, int year) {
		super();
		this.lastname = lastname;
		this.firstname = firstname;
		this.year = year;
		personAsString = toString();
	}
	
	public static SmileyPerson readFrom(String line) {
		Scanner in = new Scanner(line);
		
		String lastname = in.next();
		String firstname = in.next();
		int year = in.nextInt();
		int radiusHead = in.nextInt();
		double percentEye = in.nextDouble();
		double percentPupil = in.nextDouble();
		boolean smile = in.nextBoolean();
		int x = in.nextInt();
		int y = in.nextInt();
		
		in.close();
		
		Point2D.Double position = new Point2D.Double(x, y);
		
		SmileyPerson person = new SmileyPerson(
				position,
				radiusHead,
				smile,
				lastname,
				firstname,
				year
				);
		
		return person;
	}
	
	public String getLastname() {
		return lastname;
	}
	
	public String getFirstname() {
		return firstname;
	}
	
	public int getYear() {
		return year;
	}
	
	public int compareByName(SmileyPerson other) {
		return lastname.compareToIgnoreCase(other.lastname);
	}
	
	public int compareByFirstName(SmileyPerson other) {
		return firstname.compareToIgnoreCase(other.firstname);
	}
	
	public int compareByYear(SmileyPerson other) {
		return Integer.compare(year, other.year);
	}
	
	public int compareByHeadradius(SmileyPerson other) {
		return Double.compare(radiusHead, other.radiusHead);
	}
	
	public boolean hasTallHead() {
		return radiusHead < 100;
	}
	
	public boolean hasBigEyes() {
		return radiusEye / radiusHead * 100 > 0.25;
	}

	public boolean isYoung() {
		return 2025 - year < 20;
	}

	public boolean isOld() {
		return 2025 - year > 70;
	}
	
	@Override
	public String toString() {
	    StringBuilder sb = new StringBuilder();

	    sb.append(String.format("%-20s", lastname));
	    sb.append(String.format("%-20s", firstname));
	    sb.append(String.format("%8d", year));
	    sb.append(String.format("%8d", (int)radiusHead));
	    sb.append(String.format("%8.3f", radiusEye / radiusHead * 100));
	    sb.append(String.format("%8.3f", radiusPupil / radiusEye * 100));
	    sb.append(String.format("%8s", smile));
	    sb.append(String.format("%8d", (int)getPosition().getX()));
	    sb.append(String.format("%8d", (int)getPosition().getY()));

	    return sb.toString();
	}
	
//	public static void main(String[] args) {
//		SmileyPerson p = new SmileyPerson("Schmidt", "Harald", 1995);
//		System.out.println(p.toString());
//		String person ="Schmidt             Harald                  1995     100  25.000  30.000    true       0       0";
//		SmileyPerson p = readFrom(person);
//		System.out.println(p.getPosition());	
//	}
}
